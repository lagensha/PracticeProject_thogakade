package service;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.ItemDTO;
import repository.ItemRepository;
import repository.ItemRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemRepository.save(itemDTO);
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemRepository.delete(itemCode);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemRepository.update(itemDTO);
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = itemRepository.getAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        while (resultSet.next()) {
            itemDTOList.add(new ItemDTO(
                    resultSet.getString("itemCode"),
                    resultSet.getString("description"),
                    resultSet.getString("packSize"),
                    resultSet.getDouble("unitPrice"),
                    resultSet.getInt("qtyOnHand")
            ));
        }
        return itemDTOList;
    }
   public ResultSet searchItem(String itemCode, String description){
        return null;
    }
}
