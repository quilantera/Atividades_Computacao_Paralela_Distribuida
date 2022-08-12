package server;
//exemplo de http://www.guj.com.br/articles/37
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.Mensageiro;

public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {

	public MensageiroImpl() throws RemoteException {
		super();
	}

	public void enviarMensagem( String msg ) throws RemoteException {
		System.out.println( msg );
	}

	public String lerMensagem() throws RemoteException {
		return "This is not a Hello World! message";
	}
}