https://acmp.ru/?main=task&id_task=195
import java.util.Scanner;

public class Main {
   static void run()
    {
    Scanner in = new Scanner(System.in);
    byte a =(byte) in.nextByte();
    byte b = (byte)in .nextByte();
    byte N  = (byte)in .nextByte();
    int  result = (a*b*N)*2;
        System.out.println(result);
    }
   public static void main(String[] args) {
    run();    
    }
}