https://acmp.ru/index.asp?main=task&id_task=66&ins=1#solution
import java.util.Scanner;
  
class Main {
  
    void run() {
        
        Scanner in = new Scanner(System.in);
        String keys="qwertyuiopasdfghjklzxcvbnmq";
        String letter=in.next();
        for (int i = 0; i < keys.length()-1; i++) {
            if(letter.charAt(0)==keys.charAt(i)){
                System.out.println(keys.charAt(i+1));
            }
        }
        
    }
      
    public static void main(String[] args) {
        new Main().run();
  
    }
}