import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    private JFrame jFrame;
    private JMenu jMenu,jMenu01;
    private JMenuBar jMenuBar;
    public Main(){
        jFrame=new JFrame("超市销售管理系统");
        jFrame.setSize(1000,600);
        jFrame.setVisible(true);
        jMenuBar=new JMenuBar();
        ImageIcon mainIma=new ImageIcon("main.png");
        JPanel jPanel= (JPanel) jFrame.getContentPane();//获得一个容器
        jPanel.setOpaque(false);//将内容面板设为透明
        JLabel jLabel=new JLabel(mainIma);
        jLabel.setBounds(0,0,1000,600);
        mainIma.setImage(mainIma.getImage().getScaledInstance(jLabel.getWidth(), jLabel.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(jLabel, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
        JMenuItem item01,item02,item03;


        jMenu=new JMenu("菜单");
        item01=new JMenuItem("商品信息管理");
        ImageIcon item01Pic=new ImageIcon("a.png");
        item01.setIcon(item01Pic);
       /* item01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pstmt;
                ResultSet rs;
                Connection conn=null;
                String url="jdbc:oracle:thin:@43.136.119.185:1521:orcl";
                String sql="SELECT * "+
                        "from worker";
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"lyz","Grcl1234LyZ");
                   pstmt=conn.prepareStatement(sql);
                   rs= pstmt.executeQuery();


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });*/


        item02=new JMenuItem("商品入库管理");
        ImageIcon item02Pic=new ImageIcon("b.png");
        item02.setIcon(item02Pic);
        item03=new JMenuItem("商品出库管理");
        ImageIcon item03Pic=new ImageIcon("c.png");
        item03.setIcon(item03Pic);


        jMenu01=new JMenu("商品管理");
        ImageIcon item04Pic=new ImageIcon("d.png");
        jMenu01.setIcon(item04Pic);

        jMenu.add(jMenu01);
        jMenu01.add(item02);
        jMenu01.add(item03);
        jMenuBar.add(jMenu);
        jMenu.add(item01);
        jFrame.setJMenuBar(jMenuBar);




    }

    public static void main(String[] args) {
        new Main();
    }
}
