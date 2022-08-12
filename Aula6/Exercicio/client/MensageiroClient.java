package client;
//exemplo de http://www.guj.com.br/articles/37
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class MensageiroClient {

	public static void main( String args[] ) {
		
		try {
			int num1 = Integer.parseInt(args[0]);
			int num2 =  Integer.parseInt(args[1]);
			String operator = args[2];

			Mensageiro mref = (Mensageiro) Naming.lookup( "rmi://localhost:1099/MensageiroService" );
			System.out.println( mref.lerMensagem() );
			mref.enviarMensagem( num1,num2,operator );
		}
		catch( MalformedURLException e ) {
			System.out.println();
			System.out.println( "MalformedURLException: " + e.toString() );
		}
		catch( RemoteException e ) {
			System.out.println();
			System.out.println( "RemoteException: " + e.toString() );
		}
		catch( NotBoundException e ) {
			System.out.println();
			System.out.println( "NotBoundException: " + e.toString() );
		}
		catch( Exception e ) {
			System.out.println();
			System.out.println( "Exception: " + e.toString() );
		}
	}
}
