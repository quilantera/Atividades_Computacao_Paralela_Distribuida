
package server;

import compute.Task;
import java.io.Serializable;

public class Pow implements Task<Long>, Serializable {
    int num1, num2;
    public Pow(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public Long execute() {
        return executePow(num1,num2);
    }
    public static Long executePow(int num1, int num2) {
        int result = num1;
       for (int i=1; i<num2 ;i++){
           result = result * num1;
       }
       return  (long)result;
   }
}
