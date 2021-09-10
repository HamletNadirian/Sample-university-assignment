https://acmp.ru/?main=task&id_task=23
import java.util.Scanner;
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        int num = in.nextInt();
       int sum=0;
       
        for (int j = 1; j <=num; j++) {
            if(num%j==0){
            sum+=j;
            }
        }        
        System.out.println(sum);
        
        
    }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}