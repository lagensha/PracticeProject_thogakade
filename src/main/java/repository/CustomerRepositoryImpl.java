package repository;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public void addCustomer(String id, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, title);
        statement.setString(3, name);
        statement.setString(4, dob);
        statement.setDouble(5, salary);
        statement.setString(6, address);
        statement.setString(7, city);
        statement.setString(8, province);
        statement.setString(9, postalCode);

        statement.executeUpdate();
    }

    @Override
    public void deleteCustomer(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Customer WHERE CustID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.executeUpdate();

    }

    @Override
    public void updateCustomer(String id, String title, String name, String dob, double salary, String address, String city, String province, String postalCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, name);
        statement.setString(3, dob);
        statement.setDouble(4, salary);
        statement.setString(5, address);
        statement.setString(6, city);
        statement.setString(7, province);
        statement.setString(8, postalCode);
        statement.setString(9, id);
        statement.executeUpdate();
    }

    @Override
    public ResultSet getAllCustomers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }
}
