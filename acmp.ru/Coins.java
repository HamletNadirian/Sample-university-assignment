https://acmp.ru/index.asp?main=task&id_task=106&ins=1#solution
import java.util.Scanner;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        byte n = in.nextByte();
        byte[]array=new byte[n];
        int cross=0;//орел
        int pile=0;//решка
         for (int i = 0; i < n; i++) {
            array[i]=(byte)in.nextByte();
             if(array[i]==1)
                 cross++;
             else if(array[i]==0)
                 pile++; 
         }
         int res=Math.min(cross, pile);
         System.out.println(res);
    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }