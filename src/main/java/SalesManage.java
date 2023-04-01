import Tables.Goods;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pay.WXPay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: fanfengli
 * @Date: 2023/03/28/11:13
 * @Description:
 */
public class SalesManage extends JFrame{

    private Object rowData[][] = {{"","","","","",""}};
    private Vector<Vector<Object>> Data = new Vector<Vector<Object>>();
    private Vector<String> name = new Vector();
    private JTable table;
    private JScrollPane jScrollPane_Left, jScrollPane_Right;
    private JPanel jPanel_Left, jPanel_Right;
    private static float totalprices;
    private static int sum;

    public void init(JFrame jFrame) {
        jFrame.setLayout(null);

        name.add("商品ID");
        name.add("商品名称");
        name.add("类型");
        name.add("价格");
        name.add("数量");

        DefaultTableModel model = new DefaultTableModel(Data, name); //新建一个默认数据模型
        table = new JTable();
        table.setModel(model);
        table.setRowHeight(30);
        table.setFont(new Font("微软雅黑", Font.BOLD, 15));
        table.getTableHeader().setFont(new Font("微软雅黑", 0, 14));

        jScrollPane_Left = new JScrollPane();
        jScrollPane_Left.setViewportView(table);//将表格组件子级添加到滚动面板
        jScrollPane_Left.setBounds(0,0,650,600);

        jScrollPane_Right = new JScrollPane();
        jScrollPane_Right.setBounds(750,50,650,700);
        jScrollPane_Right.setBorder(BorderFactory.createLineBorder(Color.black));
        jScrollPane_Right.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane_Right.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JButton pay = new JButton("下单支付");
        pay.setBounds(200,610,90,30);
        JButton delete = new JButton("删除");
        delete.setBounds(450,610,90,30);

        JLabel jtotalPrices = new JLabel("总价：0.0¥");
        jtotalPrices.setBounds(0,650,100,30);
        jtotalPrices.setFont(new Font("微软雅黑", Font.BOLD, 15));
        JLabel jsum = new JLabel("总数：0个");
        jsum.setBounds(0,600,80,30);
        jsum.setFont(new Font("微软雅黑", Font.BOLD, 15));

        jPanel_Left = new JPanel();
        jPanel_Left.setLayout(null);
        jPanel_Left.setBounds(50,50,650,700);
        jPanel_Left.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel_Left.add(jScrollPane_Left);
        jPanel_Left.add(pay);
        jPanel_Left.add(delete);
        jPanel_Left.add(jtotalPrices);
        jPanel_Left.add(jsum);



        jPanel_Right = new JPanel();
        jPanel_Right.setLayout(new FlowLayout(FlowLayout.LEFT,6,6));
        jPanel_Right.setPreferredSize(new Dimension(650, 650));

//        jPanel_Right.setBorder(BorderFactory.createLineBorder(Color.black));

        jFrame.getContentPane().add(jPanel_Left);
        jFrame.getContentPane().add(jScrollPane_Right);


        //去数据库查询商品，并以JLabel的方式显示
        ListingManagement goodsList = new ListingManagement();
        rowData = goodsList.getRowData();
        for (int i = 0; i < rowData.length; i++) {

            JLabel product = new JLabel();
            int id = (int) rowData[i][0];
            String goodName = (String) rowData[i][1];
            String category = (String) rowData[i][2];
            float price = (float) rowData[i][3];
            String photoURL = (String) rowData[i][5];
            String strMsg = "<html><body bgcolor='#366ccb' color='yellow'>" +
                    "<img width = '100' height = '100'" +
                    "src = " + photoURL + "/>" + "<br>" + goodName + "<br>" + price + "<body></html>";
            product.setSize(120,150);
            product.setText(strMsg);
            jPanel_Right.add(product);
//            jPanel_Right1.repaint();
            product.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int flag = 0;
                    sum = 0;
                    totalprices = 0;
                    if (Data.size() > 0) {
                        for (int i = 0; i < Data.size(); i++) {
                            if (Data.get(i).contains(goodName)) {
                                int num = (int)Data.get(i).get(4) + 1;
                                Data.get(i).set(4, num);
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.println("新增");
                        Vector vRow1 = new Vector();
                        vRow1.add(id);
                        vRow1.add(goodName);
                        vRow1.add(category);
                        vRow1.add(price);
                        vRow1.add(1); //要添加的行的内容
                        Data.add(vRow1); //将新行向量（其中有若干行）添加到行向量集
                    }

                    //计算总价
                    for (int i = 0; i < Data.size(); i++) {

                        totalprices += (float)Data.get(i).get(3) * (int)Data.get(i).get(4);
                        totalprices = Math.round(totalprices * 100) / 100f; //保留两位小数
                        sum += (int)Data.get(i).get(4);
                    }

                    jtotalPrices.setText("总价："+ totalprices + "¥");
                    jsum.setText("总数：" + sum + "个");
                    DefaultTableModel model1 = new DefaultTableModel(Data, name); //添加后生成新模型
                    table.setModel(model1); //根据新模型设置表格
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }


        jScrollPane_Right.setViewportView(jPanel_Right);
        jScrollPane_Right.repaint();
        jPanel_Left.repaint();


        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                JSONArray objects = new JSONArray();
                for (int i = 0; i <Data.size(); i++) {
                    name += Data.get(i).get(1) + " ";
                    JSONObject object = new JSONObject();
                    object.put("goods_id",Data.get(i).get(0));
                    object.put("quantity",Data.get(i).get(4));
                    objects.add(object);
                }
                String detail = objects.toJSONString(objects);
                String out_trade_no = WXPay.unifiedOrder(name, totalprices * 100, detail);

                //显示二维码

                JFrame jFrame1 = new JFrame("结账页面");
                jFrame1.setSize(320,340);
                JLabel payView = new JLabel();
                ImageIcon icon = new ImageIcon("new.jpg");
                icon.setImage(icon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));

                payView.setVerticalTextPosition(JLabel.TOP);
                payView.setBounds(0,0,300,300);
                String strMsg1 = name;
//                String strMsg = "<html><body bgcolor='green' color='red'>"+strMsg1 + "<br>" + totalprices + "<body></html>";
//                payView.setText(strMsg);
                payView.setIcon(icon);

                JPanel jPanel  = (JPanel)jFrame1.getContentPane();
                jPanel.setLayout(null);
                jPanel.add(payView);
                jFrame1.setVisible(true);

                //判断关闭二维码
                CloseWX closeWX = null;
                try {
                    closeWX = new CloseWX(out_trade_no, jFrame1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Thread thread = new Thread(closeWX);
                thread.start();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table.getSelectedRow();
                totalprices -= (float)Data.get(count).get(3) * (int)Data.get(count).get(4);
                totalprices = Math.round(totalprices * 100) / 100f; //保留两位小数
                sum -= (int)Data.get(count).get(4);

                Data.remove(count);

                jtotalPrices.setText("总价："+ totalprices + "¥");
                jsum.setText("总数：" + sum + "个");
                DefaultTableModel model1 = new DefaultTableModel(Data, name); //添加后生成新模型
                table.setModel(model1); //根据新模型设置表格

            }
        });
    }
}
