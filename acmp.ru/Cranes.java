https://acmp.ru/?main=task&id_task=92
import java.util.Scanner;

class Main {

     void run() {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int peter =s/6;
        int serg = s/6;
        int kate=(peter+serg)*2;
         System.out.println(peter+" "+kate+" "+serg);
    }

    public static void main(String[] args) {
               new Main().run();
 
    }
  }