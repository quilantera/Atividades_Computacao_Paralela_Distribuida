package client;
//exemplo de http://www.guj.com.br/articles/37
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensageiro extends Remote {

	public void enviarMensagem( int num1,int num2, String operator ) throws RemoteException;
	public String lerMensagem() throws RemoteException;
}