package lk.ijse.shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SupplierFormControll {

    @FXML
    private TableColumn<?, ?> ColID;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColNumber;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtSupNumber;

    @FXML
    void btnAddSupplier(ActionEvent event) {

    }

    @FXML
    void btnClearFields(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplier(ActionEvent event) {

    }

    @FXML
    void getAllDetails(MouseEvent event) {

    }

}
