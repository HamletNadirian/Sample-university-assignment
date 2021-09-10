https://acmp.ru/?main=task&id_task=81
import java.util.Scanner;

public class Main {
  static  public int[] shellSorting(int[] array) {
        int h = 1;
        int n = array.length;
        while (h < n / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j] > array[j - h]; j -= h) {
                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;
                }
            }
            h = h / 3;
        }
        return array;
    }
   
    static void run() {
        int min=0;
        int max=0;
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int on=0;
        int arr[]=new int[N];


        for (int i = 0; i <arr.length ; i++) {
           arr[i]=in.nextInt();
        }
        arr=shellSorting(arr);
        System.out.println(arr[arr.length-1]);
        System.out.println(arr[0]);


    }
    public static void main(String[] args) {
        run();
    }
}