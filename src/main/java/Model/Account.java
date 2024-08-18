package Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {

    private int accountNumber;
    private String firstName;
    private String lastName;
    private Double balance;

    // You have to insert the account number in the constructor, when fetching
    // meaning that when it is not provided in the constructor
    //It creates a new random one that does not correlate to the
    //Fetched one from the dB.
    public Account(int accountNumber, String firstName, String lastName, Double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

}
