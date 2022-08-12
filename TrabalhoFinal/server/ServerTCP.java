package server;
import java.net.*;
import java.io.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.Chooser;
import compute.Compute;

public class ServerTCP {
    public static void main (String args[]) throws ClassNotFoundException {
		try{

			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				if (clientSocket!= null){
					Connection c = new Connection(clientSocket);
				}
				
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
    Chooser operation;
	public Connection (Socket aClientSocket) throws ClassNotFoundException {
		try {
			System.out.println("------------------------");
			System.out.println("Cliente Conectado");

			clientSocket = aClientSocket;
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            operation = (Chooser) ois.readObject();
			out =new DataOutputStream( clientSocket.getOutputStream());
			
			System.out.println("parametros Recebidos");
			this.start();
		} catch(IOException e) {System.out.print("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 // an echo server
            connectEngine(operation);
            
		} catch(Exception e) {System.out.print("Erro - nao foi possivel executar"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
    public void connectEngine(Chooser operation) throws IOException {
       /* if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            Compute comp = (Compute) registry.lookup(name);
			switch(operation.getChoose()){
				case 0: 
				System.out.println("Executando a potencia de "+operation.getNum1()+
										" e "+ operation.getNum2());
					Pow taskPow = new Pow(operation.getNum1(),operation.getNum2());
					Long pow = comp.executeTask(taskPow);
					out.writeUTF(Long.toString(pow));
					System.out.println("Resultado recebido :"+pow);
				break;
				case 1:
					System.out.println("Executando o MDC de "+operation.getNum1()+
										  " e "+ operation.getNum2());
					MDC taskMDC= new MDC(operation.getNum1(),operation.getNum2());
					Long mdc = comp.executeTask(taskMDC);
					out.writeUTF(Long.toString(mdc));
					System.out.println("Resultado recebido :"+mdc);
				break;
				case 2:
					System.out.print("executando o MDC de "+operation.getNum1()+
										 " e "+ operation.getNum2());
					MMC taskMMC = new MMC(operation.getNum1(),operation.getNum2());
					int mmc= comp.executeTask(taskMMC);
					out.writeUTF(Integer.toString(mmc));
					System.out.print("Resutado recebido :"+mmc);
				
			}
        } catch (Exception e) {
            System.err.print("ComputePi exception:");
            e.printStackTrace();
        }
		System.out.println("\n");
		clientSocket.close();
    }
}
