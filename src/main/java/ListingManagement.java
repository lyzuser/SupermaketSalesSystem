import Tables.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingManagement {

    private Goods goods;
    private Object rowData[][];
    private String columnName[]={"商品ID","商品名称","类型","价格","库存","图片"};

    public Object[][] getRowData() {
        return rowData;
    }

    public String[] getColumnName() {
        return columnName;
    }


    public ListingManagement(){

        Connection conn=null;
        String url="jdbc:oracle:thin:@43.136.119.185:1521:orcl";
        String sql="SELECT * FROM GOODS";
        //PreparedStatement是Statement的子接口
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,"ffl","Grcl1234FfL");
            if(conn!=null){
               System.out.println("连接成功！");
               //预编译的 SQL 语句的对象，SQL 语句被预编译并存储在PreparedStatement对象中
               pstmt= conn.prepareStatement(sql);
               rs= pstmt.executeQuery();
               List<Goods> goodsList = new ArrayList<>();

               while (rs.next()){
                goods=new Goods();
                goods.setId(rs.getInt("ID"));
                goods.setGoodName(rs.getString("GOODNAME"));
                goods.setCategory(rs.getString("CATEGORY"));
//                goods.setPrice(rs.getInt("PRICE"));
                goods.setPrice(rs.getFloat("PRICE"));
                goods.setCount(rs.getInt("COUNT"));
                goods.setPhotoURL(rs.getString("PHOTO_URL"));

                goodsList.add(goods);
               }
                rowData = new Object[goodsList.size()][columnName.length];
                System.out.println(rowData.length);
                System.out.println(rowData[0].length);
                for(int i=0;i<goodsList.size();i++){
                   rowData[i][0]=goodsList.get(i).getId();
                   rowData[i][1]=goodsList.get(i).getGoodName();
                   rowData[i][2]=goodsList.get(i).getCategory();
                   rowData[i][3]=goodsList.get(i).getPrice();
                   rowData[i][4]=goodsList.get(i).getCount();
                   rowData[i][5]=goodsList.get(i).getPhotoURL();
               }

           }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
