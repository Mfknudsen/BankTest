import java.util.ArrayList;
import java.util.List;

public class Bank {
    int id;
    String name, city;
    List<Customer> customers;

    public Bank(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;

        customers = new ArrayList<>();
    }
}
