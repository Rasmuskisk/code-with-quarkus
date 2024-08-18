package org.acme;

import Handler.AccountHandler;
import Handler.IAccountHandler;
import Model.Account;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Account/Get")
public class GetAccount {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Account Get(int bankNumber) {

        IAccountHandler obj = new AccountHandler();

        return obj.GetAccount(bankNumber);
    }
}
