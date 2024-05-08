package lk.ijse.shop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shop.Repository.CustomerRepo;
import lk.ijse.shop.Repository.ItemRepo;
import lk.ijse.shop.Repository.OrderRepo;
import lk.ijse.shop.Repository.PlaceOrderRepo;
import lk.ijse.shop.model.*;
import lk.ijse.shop.model.ItemTm.CartTm;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFormController {

    @FXML
    private TableColumn<?, ?> ColQtyOnHand;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<String> cmbCustomerID;

    @FXML
    private JFXComboBox<String> cmbItemID;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDetail;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;


    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDetails;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane rootNode;


    @FXML
    private TableView<CartTm> tblOrderCart;


    @FXML
    private TextField txtQty;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerId();
        getItemId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDetail.setCellValueFactory(new PropertyValueFactory<>("details"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void getItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ItemRepo.findAllItemId();
            for (String id : idList) {
                obList.add(id);
            }
            cmbItemID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getIds();

            for (String id : idList) {
                obList.add(id);
            }
            cmbCustomerID.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentID = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentID);
            lblOrderID.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("");
            int idNum = Integer.parseInt(split[1]);
            return "0" + ++idNum;
        }
        return "01";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddCartToOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();
        String name = lblItemName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String details = lblDetails.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e)  -> {
            ButtonType yes = new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure to remove",yes,no).showAndWait();

            if(type.orElse(no)==yes){
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colItemCode.getCellData(i))){

                CartTm tm = obList.get(i);
                qty += tm.getQtyOnHand();
                total = qty * unitPrice;

                tm.setQtyOnHand(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();

                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code,name,qty,details,unitPrice,total,btnRemove);
        obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

    private void  calculateNetTotal(){
        int netTotal = 0;
        for (int i=0; i<tblOrderCart.getItems().size(); i++){
            netTotal += (double)colTotal.getCellData(i);
        }
        lblTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        String orderID = lblOrderID.getText();
        String  orderDetails = lblDetails.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        String cusId = cmbCustomerID.getValue();

        var order = new Order(orderID,orderDetails,date,cusId);

        List<OrderDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm tm = obList.get(i);

            OrderDetail od = new OrderDetail(
                orderID,
                tm.getItemCode(),
                tm.getQtyOnHand(),
                tm.getUnitPrice()
            );
            odList.add(od);
        }

        PlaceOrder po = new PlaceOrder(order,odList);

        try {
            boolean isPlaced = PlaceOrderRepo.placeOrder(po);
            if (isPlaced){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Placed");
            }else {
                new Alert(Alert.AlertType.WARNING,"Order Not Placed");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerID.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);

            lblCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemID.getValue();

        try {
            Item item = ItemRepo.searchById(code);
            if (item != null){
                lblItemName.setText(item.getItemName());
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                lblDetails.setText(item.getDetails());
                lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            }
            txtQty.requestFocus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddCartToOnAction(event);
    }

}
