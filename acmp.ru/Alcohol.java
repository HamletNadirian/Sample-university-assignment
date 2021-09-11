https://acmp.ru/index.asp?main=task&id_task=757
import java.util.Scanner;
  
class Main {
  
    void run() {
        
        Scanner in = new Scanner(System.in);
            long c=in.nextLong();
            long h=in.nextLong();
            long o=in.nextLong();
            long C=c/2;
            long H=h/6;
            long O = o/1;
          long result=0;
            if(c>=C&&h>=h&&o>=O){
            result=Math.min(Math.min(C, H),O);
            }
            System.out.println(result);
    }
    public static void main(String[] args) {
        new Main().run();
  
    }
}