package bookstore.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ExpressClient {

    public String getStatus(String orderId) throws RemoteException, NotBoundException, MalformedURLException {
        Express express = (Express)Naming.lookup("rmi://localhost:1199/express");
        return express.getStatus(orderId);
    }
}
