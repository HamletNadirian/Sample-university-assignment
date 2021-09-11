//https://acmp.ru/index.asp?main=task&id_task=766&ins=1#solution
import java.util.Scanner;
public class Main {
   static void run()
    {
    Scanner in = new Scanner(System.in);
    byte a =(byte) in.nextByte();
    byte b = (byte)in .nextByte();
    int c = in.nextInt();
    if((a*b)>=c)System.out.println("YES");
    else
            System.out.println("NO");
    }
    public static void main(String[] args) {
    run();    
    }
}