package service.impl;

import model.dto.CustomerDto;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    @Override
    public void addCustomer(String id, String title, String name, LocalDate dob, double salary, String address, String city, String province, String postalCode) {
        try {
            customerRepository.addCustomer(id, title, name, dob.toString(), salary, address, city, province, postalCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            customerRepository.deleteCustomer(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String id, String title, String name, LocalDate dob, double salary, String address, String city, String province, String postalCode) {
        try {
            customerRepository.updateCustomer(id, title, name, dob.toString(), salary, address, city, province, postalCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = new java.util.ArrayList<>();
        try {
            ResultSet resultSet = customerRepository.getAllCustomers();

            while (resultSet.next()) {
                CustomerDto customer = new CustomerDto(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
   public CustomerDto searchId(String id, String name){
       try {
           ResultSet resultSet=customerRepository.searchId(id,name);
           resultSet.next();
           return  new CustomerDto(
                   resultSet.getString("CustID"),
                   resultSet.getString("CustTitle"),
                   resultSet.getString("CustName"),
                   resultSet.getString("DOB"),
                   resultSet.getDouble("salary"),
                   resultSet.getString("CustAddress"),
                   resultSet.getString("City"),
                   resultSet.getString("Province"),
                   resultSet.getString("PostalCode")
           );
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
}
