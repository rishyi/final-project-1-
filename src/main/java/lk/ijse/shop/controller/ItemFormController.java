package lk.ijse.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shop.Repository.ItemRepo;
import lk.ijse.shop.model.Item;
import lk.ijse.shop.model.ItemTm.ItemTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colDetails;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    public void initialize(){
        setCellValuerFactory();
        loadAllItems();
    }

    private void setCellValuerFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<Item> itemList = ItemRepo.findAll();
            for (Item item : itemList) {
                ItemTm itemTm = new ItemTm(
                        item.getId(),
                        item.getItemName(),
                        item.getQtyOnHand(),
                        item.getDetails()
                );
                obList.add(itemTm);
            }
            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddItem(ActionEvent event) {
        String itemCode = txtCode.getText();
        String itemName = txtName.getText();
        String itemQty = txtQty.getText();
        String itemDescription = txtDescription.getText();

        if (itemCode.isEmpty() || itemName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Item code and name are required").show();
            return;
        }
        if (itemQty.isEmpty() || itemDescription.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Quantity and description are required").show();
            return;
        }

        Item item = new Item(itemCode, itemName, itemQty, itemDescription);

        try {
            boolean isSaved = ItemRepo.save(item);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Item added successfully").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnBackToHome(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/home_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Form");
        stage.centerOnScreen();
    }

    @FXML
    void itemSearchOnAction(ActionEvent event) throws SQLException {
        String itemCode = txtCode.getText();

        Item item = ItemRepo.searchById(itemCode);
        if (item != null) {
            txtCode.setText(item.getId());
            txtName.setText(item.getItemName());
            txtQty.setText(item.getQtyOnHand());
            txtDescription.setText(item.getDetails());
        }else {
                new Alert(Alert.AlertType.INFORMATION, "Item not found").show();
        }

    }

    @FXML
    void btnUpdateItem(ActionEvent event) {
        String itemCode = txtCode.getText();
        String itemName = txtName.getText();
        String itemQty = txtQty.getText();
        String itemDescription = txtDescription.getText();

        if (itemCode.isEmpty() || itemName.isEmpty() || itemQty.isEmpty() || itemDescription.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Pleas Fill The Blanks").show();
            return;
        }

        Item item = new Item(itemCode, itemName, itemQty, itemDescription);

        try {
            boolean isUpdated = ItemRepo.update(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
            }        
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteItem(ActionEvent event) {
        String itemCode =txtCode.getText();

        try {
            boolean isDeleted = ItemRepo.delete(itemCode);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields(){
        txtCode.clear();
        txtName.clear();
        txtQty.clear();
        txtDescription.clear();
    }

    public void btnClearFields(ActionEvent actionEvent) {
        clearFields();
    }

    public void getAllDetails(MouseEvent mouseEvent) {
        tblItem.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtCode.setText(newSelection.getId());
                txtName.setText(newSelection.getItemName());
                txtQty.setText(newSelection.getQtyOnHand());
                txtDescription.setText(newSelection.getDetails());
            }
        });
    }
}
