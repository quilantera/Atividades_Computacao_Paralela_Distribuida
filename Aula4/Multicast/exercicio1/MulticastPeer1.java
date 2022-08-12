// codigo adaptado
import java.net.*;
import java.io.*;
public class MulticastPeer1 {
  public static void main(String args[]) { 
   	 // args give message contents & destination multicast group (e.g. "228.5.6.7")
	 MulticastSocket s =null; // Inicia o multicast;

	 String mensagem;// args[0]
	 String IP;// args[1];
	 int PORT = 6789;
	
 	 try {
		  	mensagem = args[0];
		  	IP = args[1]; 
			  //envia a mensagem para o grupo
	   	  	InetAddress group = InetAddress.getByName(IP); // adiciona o ip inserido
	    	s = new MulticastSocket(PORT); // instancia o  multicast.
	    	System.out.println("Time to Live: " + s.getTimeToLive()); // TTL (printa o tempo de esepra)
	    	s.setTimeToLive(0);  // restringindo ao host
			System.out.println("TTL: " + s.getTimeToLive()); // TTL (printa o tempo de esepra)
			s.joinGroup(group);// entra no grupo do multcast, ou cria se nao existir.
 	    	byte [] m = mensagem.getBytes(); //Converte a mensagem em byte.
	    	DatagramPacket messageOut = 
					new DatagramPacket(m, m.length, group,PORT); // guarda a mensagem em um pacote UDP
	    	s.send(messageOut);// envia a mensagem.
	    	
        // recebe a mensagem do grupo 
	    	byte[] buffer = new byte[1000];
 	      for(int i=0; i< 3; i++) {
 		      DatagramPacket messageIn = 
			    	new DatagramPacket(buffer, buffer.length); //cria um pacote UDP.
 		      s.receive(messageIn);//recebe a mensagem.
			  String mensagemRecebida = new String(messageIn.getData());// transforma as info do pacote  UDP de byte para String
			  mensagemRecebida= mensagemRecebida.trim(); // serializa a mensagem recebida 
 		      System.out.println("Received:" + mensagemRecebida + " " + mensagemRecebida.length());
			  System.out.println("TTL: " + s.getTimeToLive()); // TTL
  	     }
	       s.leaveGroup(group);		// sai do grupo
  } catch (SocketException e){System.out.println("Socket: " + e.getMessage());
  } catch (IOException e){System.out.println("IO: " + e.getMessage());
	} finally {if(s != null) s.close();}
 }		     
}