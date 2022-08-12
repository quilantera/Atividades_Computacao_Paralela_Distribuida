package exercicio1;
import java.net.*;
import java.io.*;
// envia a mensagem para o servidor de forma TCP; 
public class Emissor {
    // 3 args, args[0] =  mensagem  args[1] = porta do direcionador.
    public static void main (String args[]){
        Socket s = null; // cria o socket
        try{
            String mensagem  = args[0];
            int portaDoDirecionador = Integer.parseInt(args[1]); //7896

            s = new Socket("localhost", portaDoDirecionador);// conecta ao servidor.
            //DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(mensagem);// envia a mensagem para o servidor

            /*e quiser lear a mensagem utiliza-se :
            /String data = in.readUTF()
			 System.out.println("Received: "+ data) ; 
            */
        }
        catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
        //Tratamento de erro.
        
    } 

}
