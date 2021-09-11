import java.util.Scanner;
  
class Main {
  
    void run() {
        
        Scanner in = new Scanner(System.in);
            int a=in.nextInt();
            int b=in.nextInt();
            System.out.println((a-1)*(b-1));
    }
    public static void main(String[] args) {
        new Main().run();
  
    }
}