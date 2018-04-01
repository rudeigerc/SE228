package bookstore.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ExpressImpl extends UnicastRemoteObject implements Express {

    ExpressImpl() throws RemoteException {
        super();
    }

    public String getStatus(String orderId) throws RemoteException {
        return String.format("Order #%s: Processing", orderId);
    }
}
