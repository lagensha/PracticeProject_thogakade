package repository;

import model.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    void addCustomer(String id, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) throws SQLException;

    void deleteCustomer(String id) throws SQLException;

    void updateCustomer(String id, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) throws SQLException;

    ResultSet getAllCustomers() throws SQLException;
}

