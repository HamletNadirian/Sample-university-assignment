import java.util.Scanner;

class Main {

    void run() {
        Scanner in=new Scanner(System.in);
        int a,b,c,t;
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        t = in.nextInt();
        int result = 0;

        if(t==a) {
            result = a * b;
            System.out.println(result);
        }
        else if (a<t){
            result=(t-a)*c+(a*b);
            System.out.println(result);
        }
        else if (a>t)
        {
            result=b*t;
            System.out.println(result);
        }




    }


    public static void main(String[] args) {
        new Main().run();

    }
}