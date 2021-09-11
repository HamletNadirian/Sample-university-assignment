https://acmp.ru/index.asp?main=import java.util.Scanner;

public class Main {


    static void run() {
        Scanner in = new Scanner(System.in);
        int N=in.nextInt();
        int[] arr=new int[N];
        int res=0;
        for (int i = 0; i <N ; i++) {
            arr[i]=in.nextInt();
            res+=arr[i];
        }
        res-=(N-1);
        System.out.println(res);
    }
    public static void main(String[] args) {
        run();
    }
}task&id_task=818