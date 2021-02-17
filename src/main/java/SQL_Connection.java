import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL_Connection implements AutoCloseable {
    private Connection connection = null;
    private String url = "";
    private String user = "";
    private String password = "";
    //region Statements
    //Getters
    private PreparedStatement ps_GetCustomers = null;
    private PreparedStatement ps_GetAccounts = null;
    private PreparedStatement ps_GetTransactionByID = null;
    private PreparedStatement ps_GetBanks = null;
    //Setters
    private PreparedStatement ps_SetNewCustomer = null;
    private PreparedStatement ps_SetNewAccount = null;
    private PreparedStatement ps_SetNewTransaction = null;
    private PreparedStatement ps_SetNewBank = null;
    //endregion

    public SQL_Connection(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        prepare();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    //region Create
    public Account CreateNewAccount(Customer c) throws Exception{
        Account result = new Account(c.getId());
        result.setCustomer(c);
        ps_SetNewAccount.setInt(1, c.getId());
        ps_SetNewAccount.executeUpdate();
        return result;
    }

    public Customer CreateNewCustomer(int id, String name, String city) throws Exception{
        Customer result = new Customer(id, name, city);
        ps_SetNewCustomer.setInt(1,id);
        ps_SetNewCustomer.setString(2, name);
        ps_SetNewCustomer.setString(3, city);
        ps_SetNewCustomer.executeUpdate();
        return result;
    }
    //endregion

    //region Fetch
    public Transaction[] FetchTransactionsByIndex(int index) throws Exception{
        List<Transaction> result = new ArrayList<>();

        ps_GetTransactionByID.setInt(1, index);
        try (ResultSet rs = ps_GetTransactionByID.executeQuery()){
            while(rs.next()){
                int id = rs.getInt(1);
                int beløb = rs.getInt(2);
                String dato = rs.getString(3);
                result.add(new Transaction(id, beløb, dato));
            }
            return result.toArray(new Transaction[result.size()]);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Account[] FetchAccounts() throws Exception{
        List<Account> result = new ArrayList<>();

        try(ResultSet rs = ps_GetAccounts.executeQuery()){
            while (rs.next()){
                result.add(new Account(rs.getInt(1)));
            }
            return result.toArray(new Account[result.size()]);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Customer[] FetchCustomers() throws Exception {
        List<Customer> result = new ArrayList<>();

        try (ResultSet rs = ps_GetCustomers.executeQuery()){
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String city = rs.getString(3);
                result.add(new Customer(id, name, city));
            }
            return result.toArray(new Customer[result.size()]);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Bank[] FetchBanks() throws Exception {
        List<Bank> result = new ArrayList<>();

        try(ResultSet rs = ps_GetBanks.executeQuery()) {
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String city = rs.getString(3);
                result.add(new Bank(id, name, city));
            }
            return result.toArray(new Bank[result.size()]);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    //endregion

    private void prepare() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);

        //region Getters
        ps_GetCustomers = connection.prepareStatement("SELECT * FROM bank.kunde");
        ps_GetAccounts = connection.prepareStatement("SELECT * FROM bank.konto");
        ps_GetTransactionByID = connection.prepareStatement("SELECT * FROM bank.transaktion WHERE transaktion_id = ?");
        ps_GetBanks = connection.prepareStatement("SELECT * FROM bank.bank");
        //endregion
        //region Setters
        ps_SetNewCustomer = connection.prepareStatement("INSERT INTO bank.kunde " +
                "(kunde_id, navn, by) VALUES (?, ?, ?)");
        ps_SetNewAccount = connection.prepareStatement("INSERT INTO bank.konto " +
                "(konto_id) VALUES (?)");
        ps_SetNewTransaction = connection.prepareStatement("INSERT INTO bank.transaktion " +
                "(transaktion_id, beløb, dato) VALUES (?, ?, ?)");
        ps_SetNewBank = connection.prepareStatement("INSERT INTO bank.bank " +
                "(bank_id, navn, by) VALUES (?, ?, ?)");
        //endregion
    }
}
