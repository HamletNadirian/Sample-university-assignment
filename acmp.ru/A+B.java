https://acmp.ru/index.asp?main=task&id_task=1&ins=1#solution
import java.util.Scanner;
public class Main {
    static void add(){
        Scanner scanner = new Scanner(System.in);
       int a=scanner.nextInt();
       int b=scanner.nextInt();
        int res=a+b;
        System.out.println(res);
    }
    public static void main(String[] args) {
        add();
    }
}