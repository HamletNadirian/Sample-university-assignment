//https://acmp.ru/index.asp?main=task&id_task=529
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        double a=0;
        double x1=in.nextInt();
        double y1=in.nextInt();
        double x2=in.nextInt();
        double y2=in.nextInt();
        double res= Math.abs(Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1,2)));
        System.out.println(res);    
    }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}