https://acmp.ru/?main=task&id_task=754
import java.util.Scanner;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        int m1 = in.nextInt();
        int m2 = in.nextInt();
        int m3 = in.nextInt();
        int max = 0;
        if(m1>=94&m1<=727 &m2>=94&m2<=727&m3>=94&m3<=727){
        if(m1>=m2&m1>=m3)
            max=m1;
        else if(m2>=m1&m2>=m3)
            max=m2;
        else
            max=m3;
        System.out.print(max);

        }
        else
             System.out.println("Error");
        
    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }