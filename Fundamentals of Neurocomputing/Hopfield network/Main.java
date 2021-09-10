/*
Завдання №4
Розробити дискретну мережу Хопфілда, здатну розпізнавати п’ять букв Вашого прізвища або імені. При цьому обґрунтуйте вибір: числа нейронів мережі та спосіб кодування елементів чорно-білих зображень. 

КОД ПРОГРАМИ ДЛЯ ЗАВДАННЯ №4
*/
import java.util.Arrays;
public class RGZ {
    static int sign(int x){return x>0?1:-1;}
    static void rec(int x1[],int x2[],int x3[],int x4[],int x5[],int rec[]){
        int mark[]=new int[5];
        for (int i = 0; i <9 ; i++) {
            if (x1[i]==rec[i])mark[0]++;
            if (x2[i]==rec[i])mark[1]++;
            if (x3[i]==rec[i])mark[2]++;
            if (x4[i]==rec[i])mark[3]++;
            if (x5[i]==rec[i])mark[4]++;

        }
        System.out.print("Распознана буква: ");
        if (mark[0]>mark[1]&&mark[0]>mark[2]&&mark[0]>mark[3]&&mark[0]>mark[4]) System.out.println("H");
        else if (mark[1]>mark[0]&&mark[1]>mark[2]&&mark[1]>mark[3]&&mark[1]>mark[4]) System.out.println("T");
        else if (mark[2]>mark[1]&&mark[2]>mark[0]&&mark[2]>mark[3]&&mark[2]>mark[4]) System.out.println("Г");
        else if (mark[3]>mark[1]&&mark[3]>mark[0]&&mark[3]>mark[2]&&mark[3]>mark[4]) System.out.println("А");
        else if (mark[4]>mark[1]&&mark[4]>mark[0]&&mark[4]>mark[2]&&mark[4]>mark[3]) System.out.println("Л");

        else System.out.println("Не распознано");
    }
    public static void main(String[] args) {
        int w[][]=new int[9][9];
        int x1[]={1,-1,1, 1,1,1, 1,-1,1};
        int x5[]={1,1,1, 1,1,1, 1,-1,1};
        int x6[]={1,-1,1, 1,-1,1, 1,-1,1};
        int x2[]={1,1,1, -1,1,-1, -1,1,-1};
        int x4[]={-1,1,1,
                -1,1,-1,
                -1,1,-1};
        int x3[]={-1,-1,-1,
                   1, 1,-1,
                  -1, 1,-1};

        System.out.println("Буква Н: "+Arrays.toString(x1));
        System.out.println("Буква Т: "+Arrays.toString(x2));
        System.out.println("Буква Г: "+Arrays.toString(x4));
        System.out.println("Буква А: "+Arrays.toString(x5));
        System.out.println("Буква Л: "+Arrays.toString(x6));
        System.out.println("Буква для распознование: "+Arrays.toString(x3));

        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                w[i][j]=x1[i]*x1[j]+x2[i]*x2[j]+x4[i]*x4[j]+x5[i]*x5[j]+x6[i]*x6[j];
            }
        }
        for (int i = 0; i <9 ; i++) {
            w[i][i]=0;
        }
        System.out.println("\t\t\t\t\tМатриця ваг зв’язків");

        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                System.out.print(w[i][j]+"\t\t");
            }
            System.out.println("");
        }
        int y[]=new int[9];
        for(int j=1;j<3;j++)// итерации
        {
            for(int i=0;i<9;i++)
            {y[i]=0;
                for(int k=0;k<9;k++)y[i]+=w[i][k]*x3[k];
            }
            for(int i=0;i<9;i++)x3[i]=sign(y[i]);//обратная связь
        }
        System.out.println("Проверка. Обратная связь для [x3]: "+ Arrays.toString(x3));
        for(int i=0;i<9;i++)
        {
            System.out.print(y[i]+" ");
        }
        System.out.println("\nПроверка. Обратная связь для [x3]: "+ Arrays.toString(x3));

        rec(x1,x2,x4,x5,x6,x3);
    }


}