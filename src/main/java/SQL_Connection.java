import java.sql.*;
import java.util.ArrayList;

public class SQL_Connection implements AutoCloseable {
    private Connection connection;
    private String url;
    private String user;
    private String password;
    //region Statements
    private PreparedStatement ps_Get;
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

    private void prepare() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }
}
