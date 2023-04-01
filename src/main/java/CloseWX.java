import util.ConnectionHandler;

import javax.swing.*;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: fanFengLi
 * @Date: 2023/04/01/21:33
 * @Description:
 */
public class CloseWX implements Runnable {
    public String out_trade_no;
    public ResultSet rs;
    public  PreparedStatement pstmt;
    public JFrame jFrame;
    public CloseWX(String out_trade_no, JFrame jFrame1) throws SQLException {
        this.out_trade_no = out_trade_no;
        this.jFrame = jFrame1;
        Connection conn= ConnectionHandler.getConnection();
        try {
            String sql = "SELECT * FROM ORDER_INFO WHERE OUT_TRADE_NO = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, this.out_trade_no);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
//            throw new SQLException("库存更新失败");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                rs= pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("查到该订单啦！");
                    jFrame.setVisible(false);
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
