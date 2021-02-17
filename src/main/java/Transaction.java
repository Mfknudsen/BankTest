import java.util.Date;

public class Transaction {
    private int id, amount;
    private String date;

    public Transaction(int id, int amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}