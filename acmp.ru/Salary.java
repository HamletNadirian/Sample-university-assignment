https://acmp.ru/index.asp?main=task&id_task=21&ins=1#solution
import java.util.*;
 class Main {
    static void main(String[] x) {
    Scanner i = new Scanner(System.in);
    int a,b,c;
    a = i.nextInt();
     b = i.nextInt();
    c = i.nextInt();
     int m= Math.min(a, Math.min(b,c));
     int z= Math.max(a, Math.max(b,c));
     System.out.println(m-z);}
}