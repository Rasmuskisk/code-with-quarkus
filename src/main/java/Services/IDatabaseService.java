package Services;

import Model.Account;

public interface IDatabaseService {
    String CreateAccount(int bankNumber, String firstName, String lastName, Double balance);
    Account GetAccount(int bankNumber);
    String Deposit(int bankNumber, double amount);
}
