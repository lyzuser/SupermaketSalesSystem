import Tables.Goods;
import cn.edu.guet.pay.WXPay;
import cn.edu.guet.pay.WXPay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSale {
    JTable table;
    private  JFrame jFrame;
    private JPanel jPanel,jPanel1;
    private  String column[]={"商品名称","类型","价格"};
    private String dataArray[][]=new String[10][3];
    public ProductSale(){

        int pictureStartlocationX=800;
        JButton pay=new JButton();
        pay.setText("结账");
       pay.setBounds(400,450,80,40);

        String url="jdbc:oracle:thin:@43.136.119.185:1521:orcl";
        PreparedStatement pstmt;
        ResultSet rs;
        String sql="SELECT * FROM GOODS";
        Connection conn=null;
        jFrame=new JFrame("商品选购");
        jFrame.setSize(1600,1000);
        jFrame.setLayout(null);
//        jPanel= (JPanel) jFrame.getContentPane();
        jPanel = new JPanel();
        jPanel1 = new JPanel();
//        jPanel.setLayout(null);
//        jFrame.setContentPane(jPanel);
//        jPanel.setSize(600,600);
        jPanel.setBounds(600,0,900,900);
        jPanel1.setBounds(0,0,600,900);
      jPanel1.setLayout(null);
      jPanel1.add(pay);
        jPanel.setBackground(Color.gray);
        jFrame.add(jPanel);
        jFrame.add(jPanel1);

        jFrame.setVisible(true);

        table = new JTable(dataArray, column);

//        final JTable[] table = new JTable[1];
//        JScrollPane jScrollPane=new JScrollPane();
        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(20,20,500,400);
//        table.setBounds(20,20,400,400);
        jscrollpane.setViewportView(table);
        jPanel1.add(jscrollpane);

//        jScrollPane.setSize(600,600);
//        jPanel.add(jScrollPane);

        List<Goods> goodsList1=new ArrayList<>();
        //List goodsList1=new List();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,"lyz","Grcl1234LyZ");
            if(conn!=null){
                System.out.println("连接成功！");
                pstmt= conn.prepareStatement(sql);
                rs= pstmt.executeQuery();
                while (rs.next()){
                   // int i=1;//标签编号
                    JLabel product01 = new JLabel();
                    //headPhoto.setVerticalTextPosition(JLabel.CENTER);
//                    product01.setHorizontalTextPosition(JLabel.RIGHT);
                    product01.setBounds(pictureStartlocationX, 50, 100, 150);
                    pictureStartlocationX+=150;
                    String strMsg1 = rs.getNString("GOODNAME");
                    int strMsg2 = rs.getInt("PRICE");
                    String urlPic = rs.getNString("PICTRUE");
                    String strMsg3= rs.getNString("CATEGROY");

                    String strMsg = "<html><body bgcolor='#fae070' color='black'>" +
                            "<img width='100' height='100' align='left' " +
                            "src=" + urlPic + "/>"
                            + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ¥" + strMsg1 + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + strMsg2 + "<body></html>";
                    product01.setText(strMsg);
                    jPanel.add(product01);
//                    jScrollPane.add(product01);

                    product01.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Goods good=new Goods();
                            good.setPrice(strMsg2);
//                            System.out.println(strMsg1);
                            good.setGoodName(strMsg1);
                            good.setCategory(strMsg3);
                            goodsList1.add(good);
                            for(int i=0;i< goodsList1.size();i++){
                                Goods good1=goodsList1.get(i);
                                dataArray[i][0]=good1.getGoodName();
                                dataArray[i][1]=good1.getCategory();
                                dataArray[i][2]= String.valueOf(good1.getPrice());
                            }
                            jscrollpane.repaint();
//                           for(int i=0;i< dataArray.length;i++){
//                               for(int j=0;j< dataArray[0].length;j++){
//                                   System.out.println(dataArray[i][j]);
//                               }
//                           }
//                            jscrollpane.setBounds(0, 30, jPanel.getWidth(), 35 * 7 + 5);
//                            jscrollpane.setViewportView(table[0]);
//                            jPanel.add(jscrollpane);
//                            System.out.println(good.toString());
//                            goodsList1.add(good);
//                            dataArray= (String[][]) goodsList1.toArray();


                        }
                    });


                }
                pay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int price = 0;
                        String name="";
                        int n = 0; //保存元素个数的变量
                        ee:for(int i = 0; i < dataArray.length; i++)
                        {
                            if(dataArray[i][0]!=null){
                                n++;}
                            else{break ee;}
                        }
                        for(int k=0;k< dataArray.length;k++){
                            for(int j=0;j< dataArray[0].length;j++){
                                System.out.println(dataArray[k][j]); }
                        }
                        for (int i = 0; i < n; i++) {
                            name+=dataArray[i][0]+";";
                            price +=  Integer.parseInt(dataArray[i][2]);
                        }
                        WXPay.unifiedOrder(name,price*100);

            /*
            显示二维码
             */
                            JFrame jFrame=new JFrame("结账页面");
                            jFrame.setSize(300,330);

                            JLabel pay=new JLabel();
                            ImageIcon icon=new ImageIcon("pay.jpg");    icon.setImage(icon.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT));

                            pay.setVerticalTextPosition(JLabel.TOP);
                            pay.setBounds(0,0,300,300);

                            String strMsg1 = name;
                            String strMsg = "<html><body bgcolor='green' color='red'>" +strMsg1+ "<br>" +price+ "<body></html>";
                            pay.setText(strMsg);
                            pay.setIcon(icon);

                            JPanel jPanel= (JPanel) jFrame.getContentPane();
                            jPanel.setLayout(null);
                            jPanel.add(pay);
                            jFrame.setVisible(true);

                    }
                });
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new ProductSale();
    }

}
