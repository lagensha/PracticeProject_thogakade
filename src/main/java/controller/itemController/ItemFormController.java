package controller.itemController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.ItemDTO;
import service.ItemServiceImpl;
import service.ItemService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    ItemService itemService = new ItemServiceImpl();

    ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colitemCode;

    @FXML
    private TableView<ItemDTO> tblItemView;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    //--------------------------------------Initialize method------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    //load table  columns
    colitemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
    colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
    colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        try {
            loadAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //set selected row to the fields
    tblItemView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValues) -> {
        if (newValues!= null) {
            setSelectedValue(newValues);
        }
    });

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        itemService.addItem(new ItemDTO(itemCode, description, packSize, unitPrice, qtyOnHand));
        clearFields();
        loadAllItems();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        itemService.deleteItem(txtItemCode.getText());
        clearFields();
        loadAllItems();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        itemService.updateItem(new ItemDTO(itemCode, description, packSize, unitPrice, qtyOnHand));
        clearFields();
        loadAllItems();

    }


    //--------------------------------------All methods------------------------------------
    private void loadAllItems() throws SQLException, ClassNotFoundException {

        itemDTOS.clear();
        List<ItemDTO> items = itemService.getAllItems();
        tblItemView.setItems(FXCollections.observableArrayList(items));
    }
    //set selected row data to the fields
    private void setSelectedValue(ItemDTO selectedValue){
        if(selectedValue == null){
            clearFields();
            return;
        }
        txtItemCode.setText(selectedValue.getItemCode());
        txtDescription.setText(selectedValue.getDescription());
        txtPackSize.setText(selectedValue.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedValue.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(selectedValue.getQtyOnHand()));
    }
    //clear all fields method
    private void clearFields(){
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

}
