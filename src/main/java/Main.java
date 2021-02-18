import java.util.List;
import  java.util.Scanner;

public class Main {
    private static SQL_Connection connection;
    private static Scanner scanner;
    private static Account account;
    private static Customer customer;
    private static Bank bank;
    private static int count = 0;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
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
        
        ShowMenu();
    }

    private static void ShowMenu() {
        System.out.println("\nSelect Item From Menu");
        if (customer == null) {
            System.out.println("1: Login");
            System.out.println("2: Create Account");
        } else {
            System.out.println("1: Log out");
            System.out.println("2: Make Transaction");
        }

        int choice = Integer.parseInt(scanner.nextLine());

        if(choice == 1){
            if(customer == null)
                Login();
            else
                LogOut();
        } else if(choice == 2){
            if(customer == null)
                CreateAccount();
            else
                MakeTransaction();
        } else{
            System.out.println("Please Select From The Menu");
            ShowMenu();
        }
    }

    private static void Login(){
        System.out.println("\nEnter Account Name");

        try {
            customer = connection.FetchCustomerByName(scanner.nextLine());

            if(customer != null){
                account = connection.FetchAccountByID(customer);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void LogOut(){

    }

    private static void CreateAccount() {
        try {
            System.out.println("Enter Account Name:");
            String name;
            name = scanner.nextLine();

            System.out.println("Enter Account City:");
            String city;
            city = scanner.nextLine();

            System.out.println("");
            count++;
            customer = connection.CreateNewCustomer(count, name, city);
            account = connection.CreateNewAccount(customer);

            System.out.println("Creating Account:\n" +
                    "Customer Info: "+ customer.getId()+ ", " + customer.getName() + ", " + customer.getCity() + "\n" +
                    "Account Info: "+ account.getId());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void MakeTransaction(){

    }
}