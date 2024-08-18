package Services;

import Database.DatabaseConnections;
import Model.Account;

import java.sql.*;

public class DatabaseService implements IDatabaseService {

    @Override
    public String CreateAccount(int bankNumber, String firstName, String lastName, Double balance) {

        DatabaseConnections db = new DatabaseConnections();
        var connection = db.getConnection();

        String query = "INSERT INTO Accounts(bankNumber, firstName, lastName, balance) VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, bankNumber);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setDouble(4, balance);

            stmt.executeUpdate();

            return "successful";

        } catch (Exception e) {
            e.printStackTrace();
            return "failed to insert into dB";
        }
    }



    // Use this method for GET the bankNumber
    public Account GetAccount(int bankNumber) {

        //I get a warning wanting to pull out this block into a method
        DatabaseConnections db = new DatabaseConnections();
        var connection = db.getConnection();

        Account account = null;

        String query = "SELECT * FROM Accounts WHERE bankNumber = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Safely set the parameter in the SQL query
            pstmt.setInt(1, bankNumber);

            // Execute the query
            ResultSet resultSet = pstmt.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                account = new Account(
                        resultSet.getInt("bankNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("balance")
                );
            }
            return account;

        } catch (Exception e) {
            e.printStackTrace();
            return account;
        }
    }

    @Override
    public String Deposit(int bankNumber, double amount) {

        DatabaseConnections db = new DatabaseConnections();
        var connection = db.getConnection();

        String query = "UPDATE Accounts SET balance = balance + ? WHERE bankNumber = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, bankNumber);

            stmt.executeUpdate();

            return "successful";

        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}
