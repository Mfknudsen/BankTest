import java.util.List;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SQL_Connection connection;

        Customer c1 = new Customer("Jon");
        Customer c2 = new Customer("Mads");
        Account account = new Account(c1);
        Account account1 = new Account(c2);

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