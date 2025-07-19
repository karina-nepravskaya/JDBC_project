package JDBC.DAO;

import java.util.List;
import JDBC.model.Customer;

public interface CustomerDao {
    void create(Customer customer);
    Customer findById(int Id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(int id);
}
