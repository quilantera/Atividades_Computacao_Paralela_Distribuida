// codigo adaptado
import java.net.*;
import java.io.*;
public class MulticastPeer2 {
  public static void main(String args[]) { 
   	 // args give message contents & destination multicast group (e.g. "228.5.6.7")

		// idem MulticastPeer1... é o mesmo codigo.
	 MulticastSocket s =null;

	 String mensagem;// args[0]
	 String IP;// args[1];
	 int PORT = 7809;
 	 try {
		  mensagem = args[0];
		  IP = args[1]; 
	   	  InetAddress group = InetAddress.getByName(IP);
	    	s = new MulticastSocket(PORT);
	    	System.out.println("Time to Live: " + s.getTimeToLive()); // TTL
	    	s.setTimeToLive(0);  // restringindo ao host
			System.out.println("TTL: " + s.getTimeToLive()); // TTL
			s.joinGroup(group);
 	    	byte [] m = mensagem.getBytes(); // mensagem passada em linha de comando
	    	DatagramPacket messageOut = 
					new DatagramPacket(m, m.length, group, PORT);
	    	s.send(messageOut);
	    	
        // get messages from others in group
	    	byte[] buffer = new byte[1000];
 	      for(int i=0; i< 3; i++) {
 		      DatagramPacket messageIn = 
			    	new DatagramPacket(buffer, buffer.length);
 		      s.receive(messageIn);
			  String mensagemRecebida = new String(messageIn.getData());
			  mensagemRecebida = mensagemRecebida.trim();
 		      System.out.println("Received:" + mensagemRecebida + " " + mensagemRecebida.length());
			  System.out.println("TTL: " + s.getTimeToLive()); // TTL
  	     }
	       s.leaveGroup(group);		
  } catch (SocketException e){System.out.println("Socket: " + e.getMessage());
  } catch (IOException e){System.out.println("IO: " + e.getMessage());
	} finally {if(s != null) s.close();}
 }		     
}