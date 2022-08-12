package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface How extends Remote {
    String sayHow() throws RemoteException;
}