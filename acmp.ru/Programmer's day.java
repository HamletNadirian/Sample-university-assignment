https://acmp.ru/index.asp?main=task&id_task=550&ins=1#solution
import java.util.Scanner;

public class Main {
    static void run() {
        Scanner in = new Scanner(System.in);
        int date = in.nextInt();
        if ((date % 400 == 0) || (date % 4 == 0) && date % 100 != 0) {
            if (date < 10) System.out.println("12/09/000" + date);
            if (date < 100 && date >= 10) System.out.println("12/09/00" + date);
            if (date < 1000 && date >= 100) System.out.println("12/09/0" + date);
            if (date >= 1000) System.out.println("12/09/" + date);
        } else {
            if (date < 10) System.out.println("13/09/000" + date);
            if (date < 100 && date >= 10) System.out.println("13/09/00" + date);
            if (date < 1000 && date >= 100) System.out.println("13/09/0" + date);
            if (date >= 1000) System.out.println("13/09/" + date);
        }
    }
    public static void main(String[] args) {
        run();
    }
}