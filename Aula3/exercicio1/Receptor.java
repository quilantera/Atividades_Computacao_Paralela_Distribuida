package exercicio1;
import java.net.*;
import java.io.*;
// Receptor receberá a mensagem em UDP
public class Receptor {
    public static void main(String args[]){ 
		// args give porta deste receptor  6789 ou 6790 ou 6791
		DatagramSocket aSocket = null;
		try {
            int portaDoServidor = Integer.parseInt(args[0]);
			aSocket = new DatagramSocket(portaDoServidor); // 6789   
            //conecta ao servidor
			byte[] buffer = new byte[1000];
			DatagramPacket replic = new DatagramPacket(buffer, buffer.length);
            //cria um buffer para receber a mensagem do direcionador
			while (true) {
				aSocket.receive(replic);
                // recebe a mensagem do direcionador
                String message = new String(replic.getData());
                //converte Bit para string

				System.out.println("Do direcionador: " + message);	
			}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      
}
