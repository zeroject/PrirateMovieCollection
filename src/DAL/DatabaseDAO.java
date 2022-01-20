package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseDAO
{
    private static final String DB_SETTINGS = "DatabaseLogin.txt";
    private SQLServerDataSource dataSource;

    /**
     * the method responsible for getting a connection to the database
     * @throws IOException
     */
    public DatabaseDAO() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DB_SETTINGS));
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(properties.getProperty("Server"));
        dataSource.setDatabaseName(properties.getProperty("Database"));
        dataSource.setUser(properties.getProperty("User"));
        dataSource.setPassword(properties.getProperty("Password"));
    }

    /**
     * a get method used in other method to use the database connection
     * @return a connection
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}


