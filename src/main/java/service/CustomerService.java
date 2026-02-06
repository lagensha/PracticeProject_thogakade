package service;

import model.dto.CustomerDto;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    void addCustomer(String id, String title, String name, LocalDate dob, double salary, String address, String city, String province, String postalCode);
    void deleteCustomer(String id);
    void updateCustomer(String id, String title, String name, LocalDate dob, double salary, String address, String city, String province, String postalCode);
    List<CustomerDto> getAllCustomers();
}
