import java.util.Scanner;
//https://acmp.ru/index.asp?main=task&id_task=52&ins=1#solution
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        
        //int happy_ticket=in.nextInt();
        String strN=in.next();
        int happy_ticket=Integer.parseInt(strN);
        int []arr = new int[strN.length()];
        int i =0;
        while(happy_ticket>0)
        {
        int digit=happy_ticket%10;
        happy_ticket/=10;
       arr[i]= digit;
           // System.out.println("d"+digit);
        i++;
        }
        
        int sum1=0;
        int sum2=0;
           for (int m = arr.length/2; m <arr.length ; m++) {
            sum1+=arr[m];
        }
        //   System.out.println("sum1:"+sum1);
           
        
         for (int j = 0; j < arr.length/2; j++) {
            sum2+=arr[j];
        }
       // System.out.println("sum2:"+sum2);
        if(sum1==sum2)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}