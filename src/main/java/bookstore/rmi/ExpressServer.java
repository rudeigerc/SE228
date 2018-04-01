package bookstore.rmi;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ExpressServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Constructing server implementation...");
        ExpressImpl express = new ExpressImpl();
        LocateRegistry.createRegistry(1199);
        java.rmi.Naming.rebind("rmi://localhost:1199/express", express);
        System.out.println("Waiting for invocations from clients...");
    }
}
