import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

import org.jgroups.JChannel;

public class SimpleChat {
    JChannel channel;
    String user_name=System.getProperty("user.name","n/a");
    private void start() throws Exception{
        channel=new JChannel();
        channel.connect("chatCluster");
        eventLoop();
        channel.close();
    }
private void eventLoop(){
    BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    while(true){
        try{
            System.out.print("> "); System.out.println("exit");
        }
    }
}
    public static void main(String[] args) throws  Exception{
        new SimpleChat().start();
    }
}