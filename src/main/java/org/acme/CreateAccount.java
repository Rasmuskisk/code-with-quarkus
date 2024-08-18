package org.acme;

import Handler.AccountHandler;
import Handler.IAccountHandler;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Account/Create")
public class CreateAccount {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String Create(String firstName, String lastName, Double balance) {

        IAccountHandler obj = new AccountHandler();

        return obj.CreateAccount(firstName, lastName, balance);
    }
}


