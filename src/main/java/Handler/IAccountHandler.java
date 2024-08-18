package Handler;

import Model.Account;

public interface IAccountHandler {


    String CreateAccount(String firstName, String lastName, Double balance);

    Account GetAccount(int bankNumber);

    String Deposit(int bankNumber, double amount);

    String Transfer(int bankNumberSender, int bankNumberReceiver, double amount);

}
