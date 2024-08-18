package org.acme;

import Handler.AccountHandler;
import Handler.IAccountHandler;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Account/Deposit")
public class DepositAmount {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String Deposit(int bankNumber, double amount) {

        IAccountHandler obj = new AccountHandler();

        return obj.Deposit(bankNumber, amount);

    }
}
