package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeFormController {
    public AnchorPane rootNode;
    public AnchorPane mainRootNod;
    public Label lblItemCount;
    public Label lblCustomerCount;
    private int customerCount;
    private int itemCount;

    public void initialize() {
        try {
            customerCount = getCustomerCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            itemCount = getItemCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(customerCount);
        setItemCount(itemCount);
    }

    private void setItemCount(int itemCount) {
        lblItemCount.setText(String.valueOf(itemCount));
    }

    private int getItemCount() throws SQLException {
        String sql = "select count(*) as item_count from item";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("item_count");
        }
        return 0;
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "select count(*) as customer_count from customer";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt("customer_count");
        }
        return 0;
    }

    public void btnOnBackToLogin(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.centerOnScreen();

    }

    public void btnItemManage(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(rootNode);
    }

    public void btnCustomerManage(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form .fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);

    }

    public void btnDashBoard(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnHome(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(anchorPane);
    }

    public void btnSupplierManage(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnPayement(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payement_form.fxml"));
        this.mainRootNod.getChildren().clear();
        this.mainRootNod.getChildren().add(anchorPane);
    }

    public void btnOrders(ActionEvent actionEvent) {

    }
}
