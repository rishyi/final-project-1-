package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT o_id FROM orders ORDER BY o_id DESC LIMIT 1";
        PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String OrderId = rs.getString(1);
            return OrderId;
        }
        return null;
    }
     public static boolean Save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstmt.setString(1, order.getId());
        pstmt.setString(2,order.getDetails());
        pstmt.setDate(3, Date.valueOf(order.getDate()));
         pstmt.setString(4,order.getC_id());

         return pstmt.executeUpdate() > 0;
     }
}