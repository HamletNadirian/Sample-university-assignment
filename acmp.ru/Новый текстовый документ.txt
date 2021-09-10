https://acmp.ru/?main=task&id_task=697
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        int L=in.nextInt();
        int W=in.nextInt();
        int H=in.nextInt();
       int banks=0;
        int S = 2*(L*H+W*H);

        if(S%16==0)
        banks=S/16;
        else
        banks=S/16+1;
        System.out.println(banks);
    }
      
    public static void main(String[] args) {
        new Main().run();
  
    }
}