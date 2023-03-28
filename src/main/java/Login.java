/*
* 登录界面功能实现
* 2023-3-23
*/

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class Login {
    public Login(){

    }
    public static void main(String[] args){
        Login login =new Login();//实例化Login对象，调用初始化界面方法
        login.initUI();

    }

    //初始化界面方法
    public void initUI() {
        JFrame frame = new JFrame();//实例化JFrame类对象，作为容器组件
        //设置窗体对象属性
        frame.setTitle("超市管理系统登录页面");//标题
        frame.setSize(390,500);//窗体大小，单位为像素
        frame.setDefaultCloseOperation(3);//关闭窗体，退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对另一个组件居中
        frame.setResizable(false);//禁止调整窗体大小

        //实例化FloFlowlt流式布局类对象
        FlowLayout fl=new FlowLayout(FlowLayout.CENTER,10,10);//居中对齐，组件与组件之间的水平间隙为10，垂直间隙为10
        frame.setLayout(fl);

        //实例化元素组件对象，将元素对象添加到窗体上
        ImageIcon icon=new ImageIcon("超市登录界面图片.jpg");
        JLabel labIcon=new JLabel(icon);//用标签接受图片，实例化JLable标签对象，该对象显示icon图标
        Dimension dim = new Dimension(400,300);//设置组件大小
        labIcon.setPreferredSize((dim));
        frame.add(labIcon);//将labIcon添加到窗体上

        //实例化JLable对象，显示"账号"
        JLabel username=new JLabel("账号");
        frame.add(username);

        //实例化JTextField标签对象,作为账号的文本输入框
        JTextField textName = new JTextField();
        Dimension dim1=new Dimension(300,30);
        textName.setPreferredSize(dim1);
        frame.add(textName);

        //显示密码
        JLabel password=new JLabel("密码");
        frame.add(password);

        //实例化JTextField标签对象,作为密码的文本输入框
        JPasswordField textPassword = new JPasswordField();
        textPassword.setPreferredSize(dim1);
        frame.add(textPassword);

         //实例化JButton组件
        JButton button=new JButton();
        Dimension dim2=new Dimension(100,30);
        button.setText("登录");//显示按钮内容
        button.setPreferredSize(dim2);
        frame.add(button);//在窗体上添加按钮

        frame.setVisible(true);//使窗体可见

        //实例化登录按钮监听类对象，并把登录界面的账号和密码框输入的信息传给它
        LoginListenner listenner=new LoginListenner(frame,textName,textPassword);
        //对当前窗体添加监听方法
        button.addActionListener(listenner);
    }



}
