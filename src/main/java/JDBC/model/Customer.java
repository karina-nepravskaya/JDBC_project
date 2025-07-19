package JDBC.model;

public class Customer {
    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;

    public Customer() {}

    public Customer(int id, String fn, String ln, String em) {
        this.customer_id = id;
        this.first_name = fn;
        this.last_name = ln;
        this.email = em;
    }

    public int getId() { return this.customer_id; }
    public void setId(int id) { this.customer_id = id; }

    public String getFirstName() { return this.first_name; }
    public void setFirstName(String fn) { this.first_name = fn; }

    public String getLastName() { return this.last_name; }
    public void setLastName(String ln) { this.last_name = ln; }

    public String getEmail() { return this.email; }
    public void setEmail(String em) { this.email = em; }
}
