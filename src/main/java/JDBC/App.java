package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import JDBC.DAO.CustomerDao;
import JDBC.DAO.CustomerDaoImpl;
import JDBC.model.Customer;

public class App {
    public static void main(String[] args) {
        try(Connection conn = DbUtil.getConnection()) {
            CustomerDao dao = new CustomerDaoImpl(conn);
            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.println("Choose operation: create / read / find / update / delete / exit");
                String input = scanner.nextLine().trim().toLowerCase();

                switch (input) {
                    case "create" -> {
                        System.out.print("Enter customer first name: ");
                        String first_name = scanner.nextLine();
                        System.out.print("Enter customer last name: ");
                        String last_name = scanner.nextLine();
                        System.out.print("Enter customer email: ");
                        String email = scanner.nextLine();

                        dao.create(new Customer(0, first_name, last_name, email));
                        System.out.println("Customer is created!");
                    }

                    case "update" -> {
                        System.out.print("Enter customer Id: ");
                        int Id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter customer new first name or \"null\" for no changes: ");
                        String first_name = scanner.nextLine();
                        System.out.print("Enter customer new last name or \"null\" for no changes: ");
                        String last_name = scanner.nextLine();
                        System.out.print("Enter customer new email or \"null\" for no changes: ");
                        String email = scanner.nextLine();

                        dao.update(new Customer(Id, first_name, last_name, email));
                        System.out.println("Customer is updated successfully!");
                    }

                    case "find" -> {
                        System.out.println("Enter an Id:");
                        int id = Integer.parseInt(scanner.nextLine());

                        Customer c = dao.findById(id);
                        System.out.printf("%d: %s %s (%s)%n", c.getId(), c.getFirstName(), c.getLastName(), c.getEmail());
                    }

                    case "read" -> {
                        List<Customer> list = dao.findAll();
                        for(Customer c: list) {
                            System.out.printf("%d: %s %s (%s)%n", c.getId(), c.getFirstName(), c.getLastName(), c.getEmail());
                        }
                    }

                    case "delete" -> {
                        System.out.print("Enter customer Id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        dao.delete(id);
                        System.out.println("Deleted");
                    }

                    case "exit" -> {
                        System.out.println("You exited JDBC :(");
                        return;
                    }

                    default -> { System.out.println("Unknown operation :("); }
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error occured while connecting to the server..." + e.getMessage());
        }
    }
}