package server;

import java.io.Serializable;

import compute.Task;

public class MMC implements Task<Integer>, Serializable {
    
    int num1;
    int num2;
    public MMC(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public int verificaNumPrimo(int num1, int num2){
        int auxNum1, auxNum2, rest;
        auxNum1 = num1;
        auxNum2 = num2;

        do{
            rest = auxNum1 % auxNum2;
            auxNum1 = auxNum2;
            auxNum2 = rest;
        } while(rest != 0);
        return (num1*num2)/auxNum1;
    }

    @Override
    public Integer execute() {
        
        return verificaNumPrimo(num1, num2);
    }
}
