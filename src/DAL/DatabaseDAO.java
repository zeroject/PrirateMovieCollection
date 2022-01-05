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

    public DatabaseDAO() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DB_SETTINGS));
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(properties.getProperty("IpAddress"));
        dataSource.setServerName(properties.getProperty("Database"));
        dataSource.setServerName(properties.getProperty("Id"));
        dataSource.setServerName(properties.getProperty("Password"));
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) {

    }

}


