import java.util.Objects;

public class Customer {
    private String name = "", city = "";
    private int id = 0;

    public Customer(int id, String name, String city) {
        this.name = name;
        this.city = city;
        this.id = id;
    }

    //region Getters
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;

        Customer customer = (Customer) o;

        return Objects.equals(getName(), customer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}