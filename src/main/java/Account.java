import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private List<Transaction> transactions;
    private Customer customer;

    public Account(Customer customer) {
        this.transactions = new ArrayList<>();
        this.customer = customer;
    }

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

    public int withDrawAmount(int amount) throws Exception{
        if(amount > getBalance())
            throw new Exception();

        transactions.add(new Transaction(-amount, new Date()));

        return getBalance();
    }

    public int depositAmount(int amount) throws Exception{
        if(amount < 0)
            throw new Exception();
        transactions.add(new Transaction(amount, new Date()));

        return getBalance();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}