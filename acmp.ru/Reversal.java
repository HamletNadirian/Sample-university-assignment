https://acmp.ru/?main=task&id_task=149
import java.util.Scanner;

class Main {

    void run() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[]arr=new int [N];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=in.nextInt();
            System.out.print("");
        }
          for (int i =N-1; i>=0 ; i--) {
              System.out.print(arr[i]+" ");
        }

    }

    public static void main(String[] args) {
        new Main().run();

    }
}