https://acmp.ru/?main=task&id_task=597
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in = new Scanner(System.in);
        int r1 = in.nextInt();
        int r2 = in.nextInt();
        int r3 = in.nextInt();
  if (r1>=(r2+r3)) 
      System.out.println("YES");
 
  else 
      System.out.println("NO");
 

    }
    public static void main(String[] args) {
        new Main().run();
  
    }
}