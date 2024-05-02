package lk.ijse.shop.Repository;

import lk.ijse.shop.db.DbConnection;
import lk.ijse.shop.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,item.getId());
        preparedStatement.setObject(2,item.getItemName());
        preparedStatement.setObject(3,item.getQtyOnHand());
        preparedStatement.setObject(4,item.getDetails());

        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE item SET item_name = ?, qty_on_hand = ?, details = ? WHERE i_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,item.getItemName());
        preparedStatement.setObject(2,item.getQtyOnHand());
        preparedStatement.setObject(3,item.getDetails());
        preparedStatement.setObject(4,item.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    public static Item searchById(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE i_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String itemId = resultSet.getNString(1);
            String name = resultSet.getNString(2);
            String qty = resultSet.getNString(3);
            String description = resultSet.getNString(4);

            Item item = new Item(itemId,name,qty,description);
            return item;
        }

        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE i_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    public static List<Item> findAll() throws SQLException {
        String sql = "SELECT * FROM item";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Item> items = new ArrayList<>();

        while (resultSet.next()) {
            String i_id = resultSet.getString(1);
            String item_name = resultSet.getString(2);
            String qty_on_hand = resultSet.getString(3);
            String description = resultSet.getString(4);

            Item item = new Item(i_id,item_name,qty_on_hand,description);
            items.add(item);
        }
        return items;
    }

    public static List<String> findAllItemId() throws SQLException {
        String sql = "SELECT i_id FROM item";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<String> itemIds = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String itemId = resultSet.getNString(1);
            itemIds.add(itemId);
        }
        return itemIds;
    }

}
