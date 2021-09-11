https://acmp.ru/index.asp?main=task&id_task=839
import java.util.Scanner;
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        String str = in.next();
        int couter=0;
       
        char[] str1=str.toCharArray();
        for (int i = 0; i < str1.length; i++) {
            if(str1[i]=='0')
            couter++;
        } 
        if (couter > 0) 
            System.out.println("NO");
        else 
            System.out.println("YES");
       
        }
   
  

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}