import Tables.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingManagement {
    private Goods goods;

    public Object[][] getRowData() {
        return rowData;
    }

    private Object rowData[][];

    public String[] getColumnName() {
        return columnName;
    }

    private String columnName[]={"商品名称","类型","价格","库存"};
    public ListingManagement(){
        Connection conn=null;
        String url="jdbc:oracle:thin:@43.136.119.185:1521:orcl";
        String sql="SELECT * FROM GOODS";
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           conn= DriverManager.getConnection(url,"lyz","Grcl1234LyZ");
           if(conn!=null){
               System.out.println("连接成功！");
               pstmt= conn.prepareStatement(sql);
               rs= pstmt.executeQuery();
               List<Goods> goodsList=new ArrayList<>();
               while (rs.next()){
                goods=new Goods();
                goods.setGoodName(rs.getNString("GOODNAME"));
                goods.setCategory(rs.getString("CATEGROY"));
                goods.setPrice(rs.getInt("PRICE"));
                goods.setCount(rs.getInt("AMOUNRT"));
                goodsList.add(goods);
               }
                rowData=new Object[goodsList.size()][columnName.length];
               for(int i=0;i<goodsList.size();i++){
                   Goods good=goodsList.get(i);
                   rowData[i][0]=good.getGoodName();
                   rowData[i][1]=good.getCategory();
                   rowData[i][2]=good.getPrice();
                   rowData[i][3]=good.getCount();

               }

           }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
