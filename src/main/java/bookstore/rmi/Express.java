package bookstore.rmi;

import java.rmi.*;

public interface Express extends Remote {
    String getStatus(String orderId) throws RemoteException;
}
