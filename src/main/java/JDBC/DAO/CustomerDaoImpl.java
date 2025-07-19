package JDBC.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
    
    private final Connection conn;

    public CustomerDaoImpl(Connection conn) { this.conn = conn; }

    @Override
    public void create(Customer customer) {
        String query = "INSERT INTO customer (first_name, last_name, email, store_id, address_id, active, create_date)" + 
        "VALUES (?, ?, ?, 1, 1, true, now())";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error while creating customer: " + e.getMessage());
        }
    }

    @Override
    public Customer findById(int Id){
        String query = "SELECT customer_id, first_name, last_name, email FROM customer WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Customer (
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );
                
            } 
        } catch(SQLException e){
            System.out.println("Error while searching for customer with id = " + Id + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> result_list = new ArrayList<>();
        String query = "SELECT customer_id, first_name, last_name, email FROM customer";
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);){
                while(rs.next()) {
                    result_list.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                    ));
                }
            } catch(SQLException e){
            System.out.println("Error while selecting: " + e.getMessage());
        }
        return result_list;
    }

    @Override
    public void update(Customer customer) {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            Customer prev_customer = findById(customer.getId());
            if (!customer.getFirstName().equals("null")){
                ps.setString(1, customer.getFirstName());
            } else {
                ps.setString(1, prev_customer.getFirstName());
            }

            if (!customer.getLastName().equals("null")){
                ps.setString(2, customer.getLastName());
            } else {
                ps.setString(2, prev_customer.getLastName());
            }

            if (!customer.getEmail().equals("null")){
                ps.setString(3, customer.getEmail());
            } else {
                ps.setString(3, prev_customer.getEmail());
            }
            ps.setInt(4, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id){
        String query = "DELETE FROM customer WHERE customer_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

}
