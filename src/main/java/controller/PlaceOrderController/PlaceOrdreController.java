package controller.PlaceOrderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dto.CustomerDto;
import model.dto.ItemDTO;
import service.impl.CustomerServiceImpl;
import service.impl.ItemServiceImpl;

public class PlaceOrdreController {
       ItemServiceImpl itemService= new ItemServiceImpl();
       CustomerServiceImpl customerService = new CustomerServiceImpl();
    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<?> tblPlaceOrderInfo;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddCardOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        CustomerDto customerDto = customerService.searchId(txtCustomerId.getText(),txtName.getText());
        txtName.setText(customerDto.getName());
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        ItemDTO itemDTO=itemService.searchItem(txtItemCode.getText(),txtDescription.getText());
        txtDescription.setText(itemDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

}
