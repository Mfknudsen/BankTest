import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private List<Transaction> transactions = new ArrayList<>();
    private Customer customer = null;
    private int id;

    public Account(int id) {
        this.transactions = new ArrayList<>();
        this.id = id;
    }

    //region Getters
    public Customer getCustomer() {
        return customer;
    }

    public int getBalance(){
        int sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    public int getId() {
        return id;
    }
    //endregion

    //region Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    //endregion

    public int withDrawAmount(int amount) throws Exception{
        if(amount > getBalance())
            throw new Exception();

        transactions.add(new Transaction(customer.getId() ,-amount, new Date().toString()));

        return getBalance();
    }

    public int depositAmount(int amount) throws Exception{
        if(amount < 0)
            throw new Exception();
        transactions.add(new Transaction(customer.getId() ,amount, new Date().toString()));

        return getBalance();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}