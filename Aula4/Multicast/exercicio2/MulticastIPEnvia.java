import java.io.*;
import java.net.*;
import java.rmi.StubNotFoundException;

public class MulticastIPEnvia {
  public static void main(String[] args) {
    MulticastSocket socket = null;  // se DatagramSocket = null; ? ==> Cria o scoket multicast
    DatagramPacket outPacket = null;// Inicia os pacotes UDP.

    String ip;//args[0] ==> sera o mesmo ip para o multicastIPDest
    String nomeDoEmissor;//args[1]
    Long qntMensagens;//args[2] => sera o tamanho do looping do envio de mensagens.
    int tempo;//args[3] ==> o tempo de intercalação de cada mensagem.
    String mensagem; //args[4] 



    int contador = 0;
    String auxMensagem;// variavel so para mudar o texto no canal do receptor.
    String endGrupo; //nome do grupo remetende ??? na real nao sei pra que serve.
    
    byte[] outBuf = new byte[256];/// buffer onde sera convetida a mensagem de string para byte e enviara via udp
    final int PORT = 9786;
    
    
 
    try {
      ip = args[0];
      nomeDoEmissor = args[1];
      qntMensagens = Long.parseLong(args[2]);
      tempo = Integer.parseInt(args[3]);
      mensagem = args[4];

      socket = new MulticastSocket();  // se new DatagramSocket(); ? 
 
      System.out.println("<Identificador>: " + nomeDoEmissor + " <Mensagem>: " + 
                          mensagem + "<tempo>: " + tempo + "\n");
      socket.setTimeToLive(0);  // restringindo ao host
        while (true ) {
          if(contador < qntMensagens){
          auxMensagem = mensagem + " de " + nomeDoEmissor;  // enviada ao receptor
          contador++;
          outBuf = auxMensagem.getBytes();
          // Enviado para o endereco IP Multicast e porta
          InetAddress address = InetAddress.getByName(ip);// aqui é onde voce inserirá o ip do multicast
          outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);// aqui é criar um canal UDP.
          endGrupo = address.toString().substring(1);  // removendo o caracter / inicial
          socket.send(outPacket);// Envia a mensagem.

          System.out.println(nomeDoEmissor + " enviou: " + mensagem + " para o grupo " + endGrupo);
          System.out.println("Time to Live: " + socket.getTimeToLive());// printa o tempo de espera
        }
        try {
          Thread.sleep(tempo);
        } catch (InterruptedException ie) { System.out.println(ie); }
      }
    } catch (IOException ioe) { System.out.println(ioe); }
      catch(Exception e) { System.out.println("argumentos invalidos, por favor insira os argumentos de forma correta");}
  }
}