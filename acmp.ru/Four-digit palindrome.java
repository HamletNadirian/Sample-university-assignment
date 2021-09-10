https://acmp.ru/?main=task&id_task=324
import java.util.Scanner;
  
class Main {
  
    void run() {
        
        Scanner in = new Scanner(System.in);
            int a=in.nextInt();
            int temp=0;
            int[]arr=new int[4];
     for(int i=0;i<4;i++){
                temp=a%10;
                a=a/10;
                arr[i]=temp;
     }
            int c =0;
            int j = arr.length;
          for(int i=0;i<4;i++){
              j--;
              if(arr[i]==arr[j])
                     c++;

                  }
                        if(c==4)
                  System.out.println("YES");
              else
                  System.out.println("NO");
    }
      
      }
    public static void main(String[] args) {
        new Main().run();
  
    }
}