https://acmp.ru/index.asp?main=task&id_task=892
import java.util.Scanner;
 
class Main {
 
    void run() {
        Scanner in = new Scanner(System.in);
        byte n = in.nextByte();
       if(n==1||n==2||n==12) System.out.print("Winter");
       else if(n==3||n==4||n==5)System.out.print("Spring");
      else if(n==6||n==7||n==8) System.out.print("Summer");
     else  if(n==9||n==10||n==11) System.out.print("Autumn");
        else System.out.println("Error");
     
    }
    public static void main(String[] args) {
        new Main().run();
 
    }
}