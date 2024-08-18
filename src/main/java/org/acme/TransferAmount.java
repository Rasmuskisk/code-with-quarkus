package org.acme;

import Handler.AccountHandler;
import Handler.IAccountHandler;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Account/Transfer")
public class TransferAmount {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String Deposit(int bankNumberSender, int bankNumberReceiver, double amount) {

        IAccountHandler obj = new AccountHandler();

        return obj.Transfer(bankNumberSender, bankNumberReceiver, amount);

    }
}
