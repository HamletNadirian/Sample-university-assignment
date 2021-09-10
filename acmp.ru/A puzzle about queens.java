https://acmp.ru/index.asp?main=task&id_task=86&ins=1#solution
import java.util.Scanner;

public class Main {


    static void start( ){
        Scanner in = new Scanner(System.in);
       int n = in.nextInt();
    n=(n*n-3*(n-1)-1);
        System.out.println(n);
}
    public static void main(String[] args) {
start();
    }
}