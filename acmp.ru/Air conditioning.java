//https://acmp.ru/index.asp?main=task&id_task=854
import java.util.Scanner;
  
class Main {
  
    void run() {
        Scanner in=new Scanner(System.in);
        
        int Tr=in.nextInt();
        int Tc=in.nextInt();
        int res=0;
        int min=0;
        int max=0;
        String control = in.next();
        switch(control){
            case "freeze":
                if(Tr>=Tc)
                res=Tc;
                else
                  res=Tr;  
                System.out.println(res);
                break;
            case "heat":
                if(Tr<=Tc)
                res=Tc;
                else
                res=Tr;                
                System.out.println(res);
                break;
            case "auto":
                res=Tc;
                System.out.println(res);
                break;
            case "fan":
                res=Tr;
                System.out.println(res);
                break;
        }
    }

      
    public static void main(String[] args) {
        new Main().run();
  
    }
}