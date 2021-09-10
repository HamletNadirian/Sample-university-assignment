https://acmp.ru/?main=task&id_task=25

import java.util.Scanner;


public class Main {
   static void run()
    {
    Scanner in = new Scanner(System.in);
    int a = in.nextInt();
    int b = in .nextInt();
    if(a>b)System.out.println(">");
    else if(a<b)System.out.println("<");
    else if (a==b)System.out.println("=");
    }
    public static void main(String[] args) {
    run();    
    }
}