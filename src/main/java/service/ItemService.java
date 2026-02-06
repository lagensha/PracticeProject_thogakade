package service;

import model.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemService {

    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    // Return ArrayList, not ObservableList. The Controller will convert it to ObservableList.
    List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String itemCode, String description);
}
