
package client;

import java.net.*;

import panel.Panel;

import java.io.*;

public class Client {
    
    public Client(){}
    public void execute (int choose, int num1, int num2) {
        try{
            Chooser operation = new Chooser(choose,num1,num2);  
            printState("----------------------------------------------");      
            printState("Tentando se conectar ao servidor");
           // arguments supply message and hostname
            Socket s = null;
            try{
                
                int serverPort = 7896;
                s = new Socket("localhost", serverPort);    
                DataInputStream in = new DataInputStream( s.getInputStream());
                ObjectOutputStream out =new ObjectOutputStream(s.getOutputStream());
                printState("conectado");

                out.writeObject(operation);     
                printState("enviando os parametros"); 	// UTF is a string encoding see Sn. 4.4
                String data = in.readUTF();	   
                System.out.println("Received: "+ data) ; 
                printState("valor recebido: "+data);// read a line of data from the stream
               
            }catch (UnknownHostException e){printState("Socket:"+e.getMessage());
            }catch (EOFException e){printState("EOF:"+e.getMessage());
            }catch (IOException e){printState("readline:"+e.getMessage());
            }finally {if(s!=null) try {s.close();}catch (IOException e){printState("close:"+e.getMessage());}}
     }catch(Exception e){
        printState("Erro na execucao, inserir valores corretamente");
    }
        
    
    }
    public void printState(String value){
        Panel.showStateClient(value);
    }
}

