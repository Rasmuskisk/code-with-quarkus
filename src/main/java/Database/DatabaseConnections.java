package Database;

import java.sql.*;

public class DatabaseConnections {

    //This class is executed when I want to access the dB.
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:Accounts", "sa", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
