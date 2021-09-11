https://acmp.ru/index.asp?main=task&id_task=819&ins=1#solution
import java.util.Scanner;
  
class Main {
  
    void run() {
        
        Scanner in = new Scanner(System.in);
             long a=in.nextInt();
            long b=in.nextInt();
            long c=in.nextInt();
            long S=(a*b+b*c+a*c)*2;
            long V= a*b*c;
            System.out.println(S+" "+V);
    }
    public static void main(String[] args) {
        new Main().run();
  
    }
}