https://acmp.ru/index.asp?main=task&id_task=539&ins=1#solution
import java.util.*;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        short n= in.nextShort();
       if(n>1){ 
       if(n%2==0)
             System.out.println(n/2);
        else if(n%2!=0)
             System.out.println(n);
       }
       else 
                      System.out.println(0);

     }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }