package org.acme;

import Handler.AccountHandler;
import Handler.IAccountHandler;
import Model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This is my unit tests that I wanted to add. It is hard to test, given that a dB connection is not working,
//And I could not make an integration test to the dB, with mock data.
//It works as a accession of what i would do,
public class AccountHandlerTest {
    @Test
    void Successfull_Get_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        Account expectedAccount = new Account(200, "Rasmus", "Kristensen", 12.0);

        var response = accountHandler.GetAccount(expectedAccount.getAccountNumber());

        //If the db connected I would be able to have this Account in the db.

        assertEquals(expectedAccount, response);
    }

    @Test
    void Failed_Get_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        //The account is not in the dB and therefore expects a return object of null
        var response = accountHandler.GetAccount(999999);

        Assertions.assertNull(response);
    }

    @Test
    void Successfull_Create_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.CreateAccount("Rasmus","Kristensen", 20.0);

        Assertions.assertEquals("successful", response);
    }

    @Test
    void Failed_Create_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        //Trigger an error from the dB connection and return the exception message
        var response = accountHandler.CreateAccount("Rasmus","Kristensen", 20.0);

        Assertions.assertEquals("failed to insert into dB", response);
    }

    @Test
    void Successfull_Deposit_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Deposit(200, 20.0);

        Assertions.assertEquals( "successful", response);
    }

    @Test
    void Failed_Deposit_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Deposit(999999, 20.0);

        //The number does not exist in the dB and will therefore throw an exception
        Assertions.assertEquals( "failed", response);
    }

    @Test
    void Successfull_Transfer_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Transfer(200, 201, 20.0);

        Assertions.assertEquals( "Successfully transfered the money", response);
    }

    @Test
    void Failed_Transfer_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Transfer(200, 201, 20.0);

        //Trigger a db error so the thread can be catched

        Assertions.assertEquals( "unable to deposit", response);
    }

    @Test
    void Failed_Sender_Low_Balance_Transfer_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Transfer(201, 204, 20.0);

        // Find an account with a balance lower than of the amount being sent
        Assertions.assertEquals( "Insufficient funds", response);
    }

    @Test
    void Failed_Negative_Amount_Transfer_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Transfer(201, 204, -20.0);

        // Amount cannot be negative and would have been hit,
        // if not for the dB exception that is returning an unable to deposit
        Assertions.assertEquals( "Amount can't be negative", response);
    }

    @Test
    void Failed_Same_Account_Transfer_AccountHandler_Test() {

        IAccountHandler accountHandler = new AccountHandler();

        var response = accountHandler.Transfer(201, 201, 0);

        // Amount cannot be sent to the same account
        Assertions.assertEquals( "Cannot send to same account", response);
    }

}
