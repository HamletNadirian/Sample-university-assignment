https://acmp.ru/index.asp?main=task&id_task=4&ins=1#solution
import java.util.Scanner;
public class Main {
    void run(int n){
        System.out.println(n+"9"+(9-n));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        new Main().run(n);
    }
}