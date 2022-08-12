package exercicio1;
import java.net.*;
import java.io.*;
public class Direcionador {
    public static void main (String args[]) {  // porta do direcionador, portas dos 3 receptores
        // 7896 6789 6790 6791
        try{
            int direcionadorPort = Integer.parseInt(args[0]); // 7896; // the server port
            int[] receptoresUDP = new int[3];
            receptoresUDP[0] = Integer.parseInt(args[1]);
            receptoresUDP[1] = Integer.parseInt(args[2]);
            receptoresUDP[2] = Integer.parseInt(args[3]);
            ServerSocket listenSocket = new ServerSocket(direcionadorPort);// inicia o socket TCP
            while(true) {
                Socket clientSocket = listenSocket.accept();// cria o client socket
                Repassador c = new Repassador(clientSocket, receptoresUDP); //instancia a thread.
            }
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
        }
        }

class Repassador extends Thread { // transforma a mensagem TCP para UDP.
    DatagramSocket aSocket = null; // Cria um socket UDP
    DatagramPacket replic;  // Cria um pacote UDP
    byte[] buffer = new byte[1000];  // Cria um buffer para converter a mensagem de string pra byte.
    DataInputStream in; 
    DataOutputStream out; 
    Socket clientSocket; // cria o socket 
    int[] porta = new int[3];
    public Repassador (Socket aClientSocket, int[] recPort) { // recebe o socket do emissor e as portas dos receptores
        try {
            clientSocket = aClientSocket; // recebe os parametros do socket do emissor.
            porta = recPort;
            in = new DataInputStream( clientSocket.getInputStream());// recebe os parametros de entrada;
            out =new DataOutputStream( clientSocket.getOutputStream());// recebe os parmetros de saida;
            this.start(); // inicia a thread ==> executa o run()

        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
        }
    public void run(){
    try {			                 
        String data = in.readUTF(); //le a mensagem TCP do emissor
        //out.writeUTF(data);
        buffer = data.getBytes(); // converte para Byte
        try {
            aSocket = new DatagramSocket();
            for (int i=0; i < 3; i++) {
                replic = new DatagramPacket(buffer,buffer.length,
                    InetAddress.getByName("localhost"),porta[i]); // cria um pacote UDP 
                aSocket.send(replic);// envia o pacote
            }
        } catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
    } catch(IOException e) {System.out.println("readline:"+e.getMessage());
    } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
    }
}
