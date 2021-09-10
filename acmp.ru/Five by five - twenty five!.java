https://acmp.ru/index.asp?main=task&id_task=3&ins=1#solution
import java.util.Scanner;
class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if((n>0)&&(n&(n-1))==0)    
        System.out.println("YES"); 
     else
             System.out.println("NO");
    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }