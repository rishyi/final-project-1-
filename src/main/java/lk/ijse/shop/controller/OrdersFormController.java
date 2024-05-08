package lk.ijse.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shop.Repository.OrderRepo;
import lk.ijse.shop.model.ItemTm.OrderTm;
import lk.ijse.shop.model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrdersFormController {

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderDetail;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    void getAllDetails(MouseEvent event) {

    }

    public void initialize() {
        setCellValuerFactory();
        loadAllOrders();

    }

    private void setCellValuerFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderDetail.setCellValueFactory(new PropertyValueFactory<>("details"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("c_id"));
    }

    private void loadAllOrders() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<Order> orderList = OrderRepo.GetAll();
            for (Order order : orderList) {
                OrderTm orderTm = new OrderTm(
                        order.getId(),
                        order.getDetails(),
                        order.getDate(),
                        order.getC_id()
                );
                obList.add(orderTm);
            }
            tblOrders.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
