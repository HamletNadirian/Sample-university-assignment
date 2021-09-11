//https://acmp.ru/index.asp?main=task&id_task=948
import java.util.Scanner;

public class Main {
    static void run() {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt();
        int N = in.nextInt();
        int page = 0;
        int c = 0;
        int column = 0;
        if(K>=N){
            System.out.println(1);
        System.out.println(N);}
        else if (N>K){
            for (int i = 0; i <N ; i+=K) {
            page++;
            }

            column=N-(K*(page-1));
            System.out.print(page+" ");
            System.out.println(column);
        }
    }
    public static void main(String[] args) {
        run();
    }
}