https://acmp.ru/?main=task&id_task=606
import java.util.Scanner;

class Main {

    void run() {
        Scanner in=new Scanner(System.in);
        int a=in.nextInt();
        int b=in.nextInt();
        int c=in.nextInt();
        if((a+b)>c&&(a+c)>b&&(b+c)>a)
            System.out.println("YES");
            else
            System.out.println("NO");
    }


    public static void main(String[] args) {
        new Main().run();

    }
}