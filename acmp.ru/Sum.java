import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sum=0;
        if (n>0){
            for (int i = n; i >=0 ; i--) {
                sum+=n;
                n--;
            }
        }
        else
            for (int j = n; j <=1 ;j++) {
                sum+=n;                              
                n++;
            }
        System.out.println(sum);
    }
}