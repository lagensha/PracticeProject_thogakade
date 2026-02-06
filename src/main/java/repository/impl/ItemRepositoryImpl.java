package repository.impl;

import db.DBConnection;
import model.dto.ItemDTO;
import repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="INSERT INTO item VALUES (?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDTO.getItemCode());
        statement.setString(2, itemDTO.getDescription());
        statement.setString(3, itemDTO.getPackSize());
        statement.setDouble(4, itemDTO.getUnitPrice());
        statement.setInt(5, itemDTO.getQtyOnHand());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE item SET description=?, PackSize=?, unitPrice=?, qtyOnHand=? WHERE itemCode=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDTO.getDescription());
        statement.setString(2, itemDTO.getPackSize());
        statement.setDouble(3, itemDTO.getUnitPrice());
        statement.setInt(4, itemDTO.getQtyOnHand());
        statement.setString(5, itemDTO.getItemCode());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM item WHERE itemCode=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
        }
    public ResultSet searchItem(String itemCode, String description) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
       PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM item WHERE ItemCode=? OR Description=?");
       preparedStatement.setObject(1,itemCode);
       preparedStatement.setObject(2,description);
       ResultSet resultSet=preparedStatement.executeQuery();
       return resultSet;
    }
    }

