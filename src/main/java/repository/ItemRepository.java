package repository;

import model.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemRepository {
    boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    ResultSet getAll() throws SQLException, ClassNotFoundException;
    ResultSet searchItem(String itemCode, String description) throws SQLException;

}
