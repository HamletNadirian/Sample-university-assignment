https://acmp.ru/?main=task&id_task=504
import java.util.Scanner;

class Main {

    void run() {
        Scanner in=new Scanner(System.in);
        int k = in.nextInt();
        String[]str=new String[3];
        str[0]="G";
        str[1]="C";
        str[2]="V";
        String temp="";
        for (int i=0;i<k; i++){
            temp=str[2];
            str[2]=str[1];
            str[1]=temp;
            temp=str[0];
            str[0]=str[1];
            str[1]=temp;
        }
        System.out.println(str[0]+str[1]+str[2]);
    }


    public static void main(String[] args) {
        new Main().run();

    }
}