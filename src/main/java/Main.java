import java.sql.SQLException;
import java.util.List;
import  java.util.Scanner;

public class Main {
    private static SQL_Connection connection;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try {
            System.out.println("Type in your MySQL Username:");
            String user = scanner.nextLine();
            System.out.println("Type in your MySQL Password:");
            String password = scanner.nextLine();
            connection = new SQL_Connection(
                    "jdbc:mysql://localhost/?serverTimezone=UTC",
                    user,
                    password);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            Customer c1 = connection.CreateNewCustomer(1, "John", "København");
            Customer c2 = connection.CreateNewCustomer(2,"Mads", "Nærum");
            Account account = connection.CreateNewAccount(c1);
            Account account1 = connection.CreateNewAccount(c2);
        }
        catch (Exception e){
            e.printStackTrace();
        }


        boolean finish = false;

        System.out.println("Welcome to the bank, what would you like to do?");
        System.out.println("Press 1, to deposit money");
        System.out.println("Press 2, to withdraw money");
        System.out.println("Press 3, to transfer money to another account");
        System.out.println("Press 9, to quit the program");

        while (!finish) {


            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Enter the name of the account owner and the amount you would like to deposit");
                    break;

                case "2":
                    System.out.println("Enter the name of the account owner and the amount you would like to withdraw");

                    break;
                case "3":
                    System.out.println("First enter the name of the account owner and the amount you would like to withdraw");
                    System.out.println("Secondly enter the name of the account owner you would like that amount transfered to");
                    break;

                case "9":
                    System.out.println("-------------------------------------------");
                    System.out.println("Closing down the system");
                    finish=true;
                    break;
            }

        }
    }
}
