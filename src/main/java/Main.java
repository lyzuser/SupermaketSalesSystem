import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Main {
    private JFrame jFrame;
    private JMenu jMenu,jMenu01;
    private JMenuBar jMenuBar;
    private Object rowData[][];
    String column[];
    JTable table;
    JScrollPane jScrollPane=new JScrollPane();
    private JPanel jPanel;
    public Main(){
        jFrame=new JFrame("超市销售管理系统");
        jFrame.setSize(2160,1370);
        //jFrame.setLayout(null);
        jMenuBar=new JMenuBar();
        jPanel= (JPanel) jFrame.getContentPane();
        jPanel.setLayout(null);
        ImageIcon mainIma=new ImageIcon("main.png");

        JPanel jPanel= (JPanel) jFrame.getContentPane();//获得一个容器
        jPanel.setOpaque(false);//将内容面板设为透明

        JLabel jLabel=new JLabel(mainIma);
        jLabel.setBounds(0,0,1000,600);
        mainIma.setImage(mainIma.getImage().getScaledInstance(jLabel.getWidth(), jLabel.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(jLabel, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板


        JMenuItem item01,item02,item03;
        jMenu=new JMenu("菜单");
        //jMenu.setBounds(0,0,100,30);

        item01=new JMenuItem("商品信息管理");
        ImageIcon item01Pic=new ImageIcon("a.png");
        item01.setIcon(item01Pic);



        item02=new JMenuItem("商品入库管理");
        ImageIcon item02Pic=new ImageIcon("b.png");
        item02.setIcon(item02Pic);

        item03=new JMenuItem("商品出库管理");
        ImageIcon item03Pic=new ImageIcon("c.png");
        item03.setIcon(item03Pic);
        jMenu01=new JMenu("商品管理");
        ImageIcon item04Pic=new ImageIcon("d.png");
        jMenu01.setIcon(item04Pic);

        jMenuBar.add(jMenu);
        jMenu.add(item01);
        jMenu.add(jMenu01);

        jMenu01.add(item02);
        jMenu01.add(item03);
        jMenuBar.setBounds(0,0,100,30);

        jPanel.add (jMenuBar);
        jFrame.setVisible(true);

        item01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListingManagement listingManagement=new ListingManagement();
                rowData=listingManagement.getRowData();
                column=listingManagement.getColumnName();

                table=new JTable(rowData,column);
                jScrollPane.setViewportView(table);//滑动面板绑定表格
                jScrollPane.setBounds(0, 31, 700, 370);

                table.setRowHeight(35);
                jPanel.add(jScrollPane);

            }
        });

        item03.addActionListener(e -> {
            SalesManage salesManage = new SalesManage();
            salesManage.init(jFrame);
        });





    }

    public static void main(String[] args) {
        new Main();
    }
}
