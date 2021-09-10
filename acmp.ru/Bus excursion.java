https://acmp.ru/?main=task&id_task=233
import java.util.Scanner;

public class Main {
    static void run() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int on=0;
        int arr[]=new int[N];
        if(N>1){
        for (int i = 0; i <arr.length ; i++) {
           arr[i]=in.nextInt();
        }
            for (int i = 0; i <arr.length ; i++) {
                if (arr[i]<=437)
                {
                on=i+1;
                break;
                }
            }
            if(on>0)
                System.out.println("Crash "+on);
            else
                System.out.println("No crash");
    }
        if (N==1) {
            arr[0] = in.nextInt();
            if (arr[0] <= 437)
                System.out.println("Crash " + 1);
            else
                System.out.println("No crash");
        }

    }
    public static void main(String[] args) {
        run();
    }
}