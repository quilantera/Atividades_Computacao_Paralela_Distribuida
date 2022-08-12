package server;
//exemplo de http://www.guj.com.br/articles/37
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.Mensageiro;

public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {

	public MensageiroImpl() throws RemoteException {
		super();
	}

	public void enviarMensagem( int num1, int num2, String operator ) throws RemoteException{
		System.out.println( num1+""+operator+" "+num2+ " = "+calculator(num1, num2, operator));
	}

	public String lerMensagem() throws RemoteException {
		return "Sua operação será";
	}

	public String calculator( int num1, int num2, String  operator){
		String result;
		if(operator == "+") 	{result = Double.toString(num1 + num2); return (result);}
		else if(operator == "-"){ result = Double.toString(num1 - num2); return (result);}
		else if (operator == "*") {result = Double.toString(num1 * num2); return (result);}
		else if(operator == "/") {result = Double.toString(num1 / num2); return (result);}
		else return ("operador errado, por favor inserir um valor valido");
	}
}
