https://acmp.ru/?main=task&id_task=35
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        int k=in.nextInt();
        int[]n=new int[k];
        int[]m=new int[k];
        int d=0;
        int[]result=new int[k];
        for(int i = 0; i <k; i++) {
          n[i]=in.nextInt();
          m[i]=in.nextInt();
          d=19*m[i]+(n[i]+239)*(n[i]+366)/2;
          result[i]=d;
        }
          for(int i = 0; i <k; i++) {
              System.out.println(result[i]);
        }
    }
      
    public static void main(String[] args) {
        new Main().run();
  
    }
}