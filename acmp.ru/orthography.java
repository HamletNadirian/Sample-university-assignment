https://acmp.ru/index.asp?main=task&id_task=940
mport java.util.Scanner;

public class Main {
    static void run() {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt()-1;
        String str = in.next();
       str= str.substring(0,K)+str.substring(K+1);
        System.out.println(str);
    }
    public static void main(String[] args) {
        run();
    }
}
