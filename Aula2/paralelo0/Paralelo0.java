package paralelo0;
public class Paralelo0 implements Runnable { // seria a msm coisa que extends threads.
    private String sFlechas;
    private int loop;

    public Paralelo0(String sFlechas, int loop){ 
        this.sFlechas = sFlechas;
        this.loop = loop;
        Thread t = new Thread(this);// cria uma tread com os parametros do Paralelo0;
        t.start();// Roda a thread {run()}
    }
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println(i);
        }
    }
}
