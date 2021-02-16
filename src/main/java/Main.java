import java.util.List;

public class Main {

    public static void main(String[] args) {

        Customer c1 = new Customer("Jon");
        Customer c2 = new Customer("Mads");
        Account account = new Account(c1);
        Account account1 = new Account(c2);


        int newBalance = 0;
        try {
            newBalance = account.depositAmount(-5);
            System.out.println(String.format("New balance: %d", newBalance));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            newBalance = account.depositAmount(325);
            System.out.println(String.format("New balance: %d", newBalance));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            newBalance = account.withDrawAmount(220);
            System.out.println(String.format("New balance: %d", newBalance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}