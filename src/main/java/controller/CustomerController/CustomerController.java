package controller.CustomerController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDto;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    CustomerService customerService = new CustomerServiceImpl();
    ObservableList<CustomerDto>customerDtos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<CustomerDto> tblCustomerInfo;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtCustTitle;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerService.addCustomer(
                txtCustId.getText(),
                txtCustTitle.getText(),
                txtName.getText(),
                java.time.LocalDate.parse(txtDob.getText()),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        clearFields();
        loadAllCustomers();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerService.deleteCustomer(txtCustId.getText());
        clearFields();
        loadAllCustomers();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerService.updateCustomer(
                txtCustId.getText(),
                txtCustTitle.getText(),
                txtName.getText(),
                java.time.LocalDate.parse(txtDob.getText()),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        clearFields();
        loadAllCustomers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllCustomers();

        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomerInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValues) -> {
            if (newValues!= null) {
                setSelectedValue(newValues);
            }
        });
    }

    private void loadAllCustomers(){
        customerDtos.clear();
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        tblCustomerInfo.setItems(FXCollections.observableArrayList(allCustomers));

    }

    private void setSelectedValue(CustomerDto customerDto){

        if(customerDto==null){
            clearFields();
            return;
        }

        txtCustId.setText(customerDto.getId());
        txtCustTitle.setText(customerDto.getTitle());
        txtName.setText(customerDto.getName());
        txtDob.setText(String.valueOf(customerDto.getDob()));
        txtSalary.setText(String.valueOf(customerDto.getSalary()));
        txtAddress.setText(customerDto.getAddress());
        txtCity.setText(customerDto.getCity());
        txtProvince.setText(customerDto.getProvince());
        txtPostalCode.setText(customerDto.getPostalCode());
    }

    private void clearFields(){
        txtCustId.clear();
        txtCustTitle.clear();
        txtName.clear();
        txtDob.clear();
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }

}
