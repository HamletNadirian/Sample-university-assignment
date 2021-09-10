https://acmp.ru/?main=task&id_task=331
import java.util.Scanner;

public class Main {

    static void run() {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        char[] a1 = a.toCharArray();
        int h1 = Character.getNumericValue(a1[0]);
        int h2 = Character.getNumericValue(a1[1]);
        int m1 = Character.getNumericValue(a1[3]);
        int m2 = Character.getNumericValue(a1[4]);
        if(h1>=0&&h2>=0&&m1>=0&&m2>=0) {
            h1 = (h1 * 10) + h2;
            m1 = (m1 * 10) + m2;
            int addH = in.nextInt();
            int addM = in.nextInt();
            if(addH>=0&&addM>=0) {
                int resultH = h1 + addH;
            int resultM = m1 + addM;
            int MinToH = 0;
            int MinToM = 0;

            if (resultH >= 24)
                resultH %= 24;
            if (resultM > 59) {
                MinToM = resultM % 60;
                MinToH = resultM / 60;
                resultH += MinToH;
                if(resultH==24)
                    resultH=0;
                resultM = MinToM;
            }
            if (resultH >= 10 && resultM >= 10)
                System.out.println(resultH + ":" + resultM);
             if (resultH < 10 && resultM < 10)
                System.out.println("0" + resultH + ":" + "0" + resultM);
             if (resultH >= 10 && resultM < 10)
                System.out.println(resultH + ":" + "0" + resultM);
             if (resultH < 10 && resultM >= 10)
                System.out.println("0" + resultH + ":" + resultM);
        }
        }
  }
    public static void main(String[] args) {

     run();
  }



}