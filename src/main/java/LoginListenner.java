import cn.edu.guet.util.PasswordEncoder;
import cn.edu.guet.util.PasswordEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

/*
* 2023-3-23
* 实现登录按钮的监听功能
* 2023-3-26
* 补充连接数据库验证账号和密码功能
* 2023-3-28
* 补充密码加密登录
*/
public class LoginListenner implements ActionListener {

    private JTextField jt;//账号输入框对象
    private JPasswordField jp;//密码输入框对象,使用这个可以使密码显示为*
    private JFrame login;//定义一个窗体对象

    //监听方法
    public LoginListenner(JFrame login, JTextField jt, JPasswordField jp) {
        this.login = login;//获取登录页面
        this.jt = jt;//获取登录界面的账号输入框对象
        this.jp = jp;//获取登录界面的密码输入框对象
    }

    public void actionPerformed(ActionEvent e) {
        String username = jt.getText();
        String password = new String(jp.getPassword());

        //对密码进行加密
        PasswordEncoder encoderMd5 = new PasswordEncoder("xy132345ujbbgvvv");//加密用的盐
        password= encoderMd5.encode(password);


        //连接数据库,1、加载驱动,2、创建连接,3、检索记录
        Connection conn;
        String url = "jdbc:oracle:thin:@43.136.119.185:1521:orcl";//罗云之数据库地址
        String sql = "SELECT * FROM 用户表 WHERE 账号=? AND 密码=?";
        PreparedStatement ps;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//1
            conn = DriverManager.getConnection(url, "LyZ", "Grcl1234LyZ");//使用的是罗云之这个用户

            if (conn != null)//判断连接是否成功
            {
                System.out.println("连接成功");
                ps = conn.prepareStatement(sql);//在数据库中执行sql查询语句
                ps.setString(1, username);//向第一个占位符?传值，查询
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();//执行查询,查询结果放在结果集里
                if (rs.next()) {
                    System.out.println("登录成功");
                    Main main=new Main();
                    //满足条件，则生成一个新的界面
//                    JFrame jf = new JFrame();
//                    jf.setTitle("超市管理系统界面");
//                    jf.setSize(700, 600);//只对顶级容器有效
//                    jf.setDefaultCloseOperation(3);//窗体关闭时结束程序
//                    jf.setLocationRelativeTo(null);//居中
//                    jf.setResizable(false);
//                    jf.setVisible(true);

                    // 通过我们获取的登录界面对象，用dispose方法关闭它
                    login.dispose();
                }

                //不正确则弹出提示不正确的消息窗口
                else {
                    ImageIcon icon1 = new ImageIcon("登入错误提示框图片.jpg");
                    JOptionPane.showMessageDialog(null, "账号或密码输入错误!\n请重新输入", "Eorr", JOptionPane.INFORMATION_MESSAGE, icon1);
                    //图片自适应窗口大小
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }





}