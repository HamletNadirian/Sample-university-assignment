https://acmp.ru/?main=task&id_task=8
import java.util.*;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        int a, b, c;
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        int res = a*b;
        if(res==c)System.out.println("YES");
        else
            System.out.println("NO");

    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }