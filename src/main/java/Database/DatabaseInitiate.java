package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//This class is executed so that a local dB can be created right away.
//It does not work properly and blocks my ability to test the actual dB queries i have made.
public class DatabaseInitiate {
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:Accounts", "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS Accounts(bankNumber INT PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255), balance DECIMAL(10,2))");

            System.out.println("Database created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
