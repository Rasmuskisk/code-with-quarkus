package Handler;

import Model.Account;
import Services.DatabaseService;
import Services.IDatabaseService;
import java.util.Random;

public class AccountHandler implements IAccountHandler {
    @Override
    public String CreateAccount(String firstName, String lastName, Double balance) {

        //Don't know how do to Dependency Injections so I have to create my own instance every time
        IDatabaseService db = new DatabaseService();
        int bankNumber = new Random().nextInt(10000);

        return db.CreateAccount(bankNumber, firstName, lastName, balance);
    }

    @Override
    public Account GetAccount(int bankNumber) {

        IDatabaseService db = new DatabaseService();

        return db.GetAccount(bankNumber);
    }

    @Override
    public String Deposit(int bankNumber, double amount) {

        IDatabaseService db = new DatabaseService();

        return db.Deposit(bankNumber, amount);
    }

    @Override
    public String Transfer(int bankNumberSender, int bankNumberReceiver, double amount) {

        IDatabaseService db = new DatabaseService();

        try {
            var senderAccount = db.GetAccount(bankNumberSender);
            var receiverAccount = db.GetAccount(bankNumberReceiver);

            //Using guard clauses to return faulty paths earlier
            if (senderAccount.getBalance() < amount) {
                return "Insufficient funds";
            }

            if (amount < 0){
                    return "Amount can't be negative";
            }

            if (bankNumberReceiver == bankNumberSender){
                return "Cannot send to same account";
            }

            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            db.Deposit(bankNumberSender, senderAccount.getBalance());
            db.Deposit(bankNumberReceiver, receiverAccount.getBalance());

            return "Successfully transfered the money";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "unable to deposit";
        }
    }

}
