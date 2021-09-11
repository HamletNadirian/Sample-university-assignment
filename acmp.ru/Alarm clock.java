//https://acmp.ru/index.asp?main=task&id_task=777
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        int a=0;
        int S=in.nextInt();
        int T=in.nextInt();
        int d=12;
        //if(1<S&&T<=12&S!=T){
        if(S>T)
        a= d-(S-T);
        else
        a= T-S;
        System.out.println(Math.abs(a));
        }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}