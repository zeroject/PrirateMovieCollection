package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MovieDAO
{
    private static final String DB_SETTINGS = "DatabaeLogin.txt";

    private SQLServerDataSource dataSource;

    public MovieDAO() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(DB_SETTINGS));
    }

}
