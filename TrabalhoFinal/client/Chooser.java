package client; 

import java.io.Serializable;

public class Chooser implements Serializable {
	private static final long serialVersionUID = 1L;
	int choose;
	int num1;
    int num2;
	
    public Chooser(int choose, int num1, int num2){
        this.choose = choose;
        this.num1 = num1;
        this.num2 = num2;
    }
	public int getChoose(){
        return choose;
    }
    public int getNum1(){
        return num1;
    }
    public int getNum2(){
        return num2;
    }
    public void setChoose(int choose){
        this.choose = choose;
    }
    public void setNum1(int num1){
        this.num1 = num1;
    }
    public void setNum2(int num2){
        this.num2 = num2;
    }
}