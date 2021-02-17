import java.util.List;
import  java.util.Scanner;

public class Main {
    private static SQL_Connection connection;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        Customer c1 = new Customer("Jon");
        Customer c2 = new Customer("Mads");
        Account account = new Account(c1.getId());
        account.setCustomer(c1);
        Account account1 = new Account(c2.getId());
        account1.setCustomer(c2);

        try {
            System.out.println("Type In Username:");
            String user = scanner.nextLine();
            System.out.println("Type In Password:");
            String password = scanner.nextLine();
            connection = new SQL_Connection(
                    "jdbc:mysql://localhost/?serverTimezone=UTC",
                    user,
                    password);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}