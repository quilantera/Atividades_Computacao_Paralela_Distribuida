package server;

import compute.Task;
import java.io.Serializable;

public class MDC  implements Task<Long>, Serializable{
    int num1;
    int num2;
    public MDC(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    @Override
    public Long execute() {
        return executeMDC(num1,num2);
    }

    public Long executeMDC(int num1, int num2){
        long divisor;

    if (num1 == 0) return (long)num2;
    if (num2 == 0) return (long)num1;
  
    divisor =  Math.min(num1,num2);

    while (/*1*/ num1 % divisor != 0 || num2 % divisor != 0){
        divisor --;
    }
    return divisor;
    }
}
