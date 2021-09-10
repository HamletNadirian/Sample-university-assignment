https://acmp.ru/index.asp?main=task&id_task=61&ins=1#solution
import java.util.*;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        final byte N=4;
        byte[]a=new byte[N];
        byte[]b=new byte[N];
        byte a1=0;
        byte b1=0;
         for (int i = 0; i <N; i++) {
             a[i]=in.nextByte();
             b[i]=in.nextByte();
             a1+=a[i];
             b1+=b[i];
         }
         if(a1>b1)System.out.println("1");
         else if (a1<b1) System.out.println("2");
         else if (a1==b1) System.out.println("DRAW");
    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }