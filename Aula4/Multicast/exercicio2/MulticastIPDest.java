import java.io.*;
import java.net.*;

public class MulticastIPDest {
  public static void main(String[] args) {
    String ip;//args[0];

    MulticastSocket socket = null;// Inicia o socket multicast igual o Emissor.
    DatagramPacket inPacket = null; // Inicia os pacotes UDP
    String endRem,endGrupo;// nao sei pra q serve
    byte[] inBuf = new byte[256];// inicia o buffer para receber o pacote UDP.

    final int PORT = 9786;// porta do canal.
   
    try {
      //Juntando-se se o grupo Multicast IP
      ip = args[0]; //mesmo ip do  MulticastIPEnvia.

      socket = new MulticastSocket(PORT);// instancia o multicast.
      //System.out.println("Time to Live: " + socket.getTimeToLive());  
      socket.setTimeToLive(0);  // restringindo ao host
      //System.out.println("Time to Live: " + socket.getTimeToLive());
      InetAddress address = InetAddress.getByName(ip);// Adiciona o IP do multicast
      socket.joinGroup(address);// entra no grupo do multicast, ou cria caso nao tenha.
      endGrupo = address.toString().substring(1);
 
      while (true) {
        inPacket = new DatagramPacket(inBuf, inBuf.length);// cria o pacote UDP;
        socket.receive(inPacket);// recebe o pacote UDP do emissor.

        String msg = new String(inBuf, 0, inPacket.getLength());// converte a mensagem de byte para String
        endRem = inPacket.getAddress().toString().substring(1);// recebe o endereco do remetende, depois converte em String.
        System.out.println("Recebido: " + msg + " Remetente: " + endRem + " Grupo: " + endGrupo);
        System.out.println("Time to Live: " + socket.getTimeToLive());
      }
    } catch (IOException ioe) { System.out.println(ioe); }
    catch(Exception e) { System.out.println("argumentos invalidos, por favor insira os argumentos de forma correta");}
  }
}