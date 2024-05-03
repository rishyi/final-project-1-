package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {
    public AnchorPane rootNode;
    public AnchorPane mainRootNod;

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

    public void btnOrderManage(ActionEvent actionEvent) {

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
}
