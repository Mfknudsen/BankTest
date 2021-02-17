import java.util.List;
import  java.util.Scanner;

public class Main {
    private static SQL_Connection connection;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try {
            Customer c1 = connection.CreateNewCustomer(1, "John", "Lyngby");
            Customer c2 = connection.CreateNewCustomer(2,"Mads", "Nærum");
            Account account = connection.CreateNewAccount(c1);
            Account account1 = connection.CreateNewAccount(c2);
        }
        catch (Exception e){
            e.printStackTrace();
        }

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