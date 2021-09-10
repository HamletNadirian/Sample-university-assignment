https://acmp.ru/index.asp?main=task&id_task=685
import java.util.Scanner;
  
class Main {
  public static int[] bubbleSort(int[] arr){
    for(int i = arr.length-1 ; i >  0 ; i--){
        for(int j = 0 ; j < i ; j++){
            if( arr[j] > arr[j+1] ){
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
    return arr;
}
    void run() {
        int n=3;
        Scanner in = new Scanner(System.in);
            int[] a=new int[n];
            for (int i = 0; i <a.length; i++) {
            a[i]=in.nextInt();
        } 
            a=bubbleSort(a);
             int[] b=new int[n];
            for (int i = 0; i < b.length; i++) {
            b[i]=in.nextInt();
        }
         b=bubbleSort(b);

            int sum=0;
            for(int i = 0;i<n;i++)
                sum+=a[i]*b[i];
            System.out.print(sum);
    }
    public static void main(String[] args) {
        new Main().run();
  
    }
}