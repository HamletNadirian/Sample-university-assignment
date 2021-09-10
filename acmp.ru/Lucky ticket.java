https://acmp.ru/?main=task&id_task=52
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        

        String strN=in.next();
        int happy_ticket=Integer.parseInt(strN);
        int []arr = new int[strN.length()];
        int i =0;
        while(happy_ticket>0)
        {
        int digit=happy_ticket%10;
        happy_ticket/=10;
       arr[i]= digit;

        i++;
        }
        
        int sum1=0;
        int sum2=0;
           for (int m = arr.length/2; m <arr.length ; m++) {
            sum1+=arr[m];
        }

           
        
         for (int j = 0; j < arr.length/2; j++) {
            sum2+=arr[j];
        }

        if(sum1==sum2)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}