package lk.ijse.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shop.Repository.PaymentRepo;
import lk.ijse.shop.model.ItemTm.PaymentTm;
import lk.ijse.shop.model.Order;
import lk.ijse.shop.model.Payement;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PaymentFromController {

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPrice;

    public void initialize() {
        setCellValuerFactory();
        loadAllPayments();
    }

    private void setCellValuerFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("o_id"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<Payement> payementList = PaymentRepo.findAll();
            for (Payement payement: payementList) {
                PaymentTm paymentTm = new PaymentTm(
                        payement.getId(),
                        payement.getPrice(),
                        payement.getDate(),
                        payement.getO_id()
                );
                obList.add(paymentTm);
            }
            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddPayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        double price = Double.parseDouble(txtPrice.getText());
        Date date = Date.valueOf(txtDate.getText());
        String o_id = txtOrderId.getText();

        Payement payement = new Payement(paymentId, price, date, o_id);

        try {
            boolean isSaved = PaymentRepo.save(payement);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully Added").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearFields(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtDate.clear();
        txtOrderId.clear();
        txtPaymentId.clear();
        txtPrice.clear();
    }

    @FXML
    void btnDeletePayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            boolean isDeleted = PaymentRepo.delete(paymentId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdatePayment(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        double price = Double.parseDouble(txtPrice.getText());
        Date date = Date.valueOf(txtDate.getText());
        String o_id = txtOrderId.getText();

        if (paymentId.isEmpty() || o_id.isEmpty() ) {
            new Alert(Alert.AlertType.INFORMATION,"Payment Id or Order Id Empty").show();
            return;
        }
        if(Double.isNaN(price)) {
            new Alert(Alert.AlertType.INFORMATION,"Payment Price enter").show();
            return;
        }

        Payement payement = new Payement(paymentId, price, date, o_id);

        try {
            boolean isUpdated = PaymentRepo.update(payement);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Successfully Updated").show();
            }
        } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void getAllDetails(MouseEvent event){
        tblPayment.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                txtPaymentId.setText(newSelection.getId());
                txtPrice.setText(String.valueOf(newSelection.getPrice()));
                txtDate.setText(String.valueOf(newSelection.getDate()));
                txtOrderId.setText(newSelection.getO_id());
            }
        });
    }

    @FXML
    void itemSearchOnAction(ActionEvent event) {

    }

}