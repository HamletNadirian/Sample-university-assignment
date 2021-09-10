/*
Завдання № 2 - 3
Розробити мережу Хеммінга, здатну розпізнавати п’ять букв Вашого прізвища або імені. При цьому обґрунтуйте вибір: числа рецепторних та вихідних нейронів, види функцій активації нейронів кожного шару, величини ваг зв’язків та зсувів у подмережі Хеммінга. Навчити нейронну мережу Хеммінга еталонним зображенням букв Підберіть вхідне зображення, що рівно віддалене по відстані Хеммінга від двох еталонних зображень. Яка реакція мережі при пред’явленні цього зображення? Запропонуйте спосіб більш інформативного поводження мережі при пред’явленні подібних зображень.
КОД ПРОГРАМИ ДЛЯ ЗАВДАННЯ № 2-3
*/
public class Main {
    static String recLetter="";
    static double m = 9;
    static  double bi=m/2;
    static  double n=5;
    static  double execelant=0.2;
    static double v1[]={
                      1,-1,1,
                      1,1,1,
                      1,-1,1};
    static double v2[]={
                    -1,1,-1,
                     1,1,1,
                     1,-1,1};

    static double v3[]={
                     1,-1,1,
                     -1,1,-1,
                     -1,1,-1};

    static double v4[]={
                     -1,1,-1,
                     -1,1,-1,
                     -1,1,-1};

    static double v5[]= {
                      1,1,1,
                      1,-1,1,
                      1,1,1};
    static double v6[]= {
             1,1,1,
            -1,1,-1,
            -1,1,-1};

    static double s1[]={
                      -1,-1,1,
                      1,1,1,
                      1,-1,1};

    static double s2[]={
                    -1,-1,1,
                    -1,1,-1,
                    -1,-1,-1};

    static double s3[]={
                    -1,-1,-1,
                    -1,1,-1,
                    -1,1,-1};

    static double wik[][]=new double[9][6];
    static double wik2[][]=new double[9][6];

    static void half(){
        for (int i = 0; i <9; i++) {
                wik[i][0]=v1[i]/2;
                wik[i][1]=v2[i]/2;
                wik[i][2]=v3[i]/2;
                wik[i][3]=v4[i]/2;
                wik[i][4]=v5[i]/2;
                wik[i][5]=v6[i]/2;
                wik2[i][0]=s1[i]/2;
                wik2[i][1]=s2[i]/2;
                wik2[i][2]=s3[i]/2;
        }
    }
   static double uIn[]=new double[6];
   static double uZ[]=new double[6];
   static double uA[]=new double[6];

    static void calcInputSignal(){
        for (int i = 0; i <9 ; i++) {
          uIn[0]=uIn[0]+(wik[i][0]*s1[i]);
          uIn[1]=uIn[1]+(wik[i][1]*s1[i]);
          uIn[2]=uIn[2]+(wik[i][2]*s1[i]);
          uIn[3]=uIn[3]+(wik[i][3]*s1[i]);
          uIn[4]=uIn[4]+(wik[i][4]*s1[i]);
          uIn[5]=uIn[5]+(wik[i][5]*s1[i]);
        }
        uIn[0]+=bi;
        uIn[1]+=bi;
        uIn[2]+=bi;
        uIn[3]+=bi;
        uIn[4]+=bi;
        uIn[5]+=bi;
        System.out.println("~Uin~");
        System.out.println(+uIn[0]);
        System.out.println(+uIn[1]);
        System.out.println(+uIn[2]);
        System.out.println(+uIn[3]);
        System.out.println(+uIn[4]);
        System.out.println(+uIn[5]);
        System.out.println("~~~~~~~~~~~~~~~~Rusult~~~~~~~~~~~~~~~~~");


        double k = 0.1;
        int mark=0;
        for (int j = 0; j < 6; j++)
            uZ[j] = uIn[j] * k;
     while (true) {

            uA[0] = uZ[0] - execelant * (uZ[1] + uZ[2] + uZ[3] + uZ[4]+uZ[5]);
            uA[1] = uZ[1] - execelant * (uZ[0] + uZ[2] + uZ[3] + uZ[4]+uZ[5]);
            uA[2] = uZ[2] - execelant * (uZ[0] + uZ[1] + uZ[3] + uZ[4]+uZ[5]);
            uA[3] = uZ[3] - execelant * (uZ[0] + uZ[1] + uZ[2] + uZ[4]+uZ[5]);
            uA[4] = uZ[4] - execelant * (uZ[0] + uZ[1] + uZ[2] + uZ[3]+uZ[5]);
            uA[5] = uZ[5] - execelant * (uZ[0] + uZ[1] + uZ[2] + uZ[3]+uZ[4]);

            if (uA[0] < 0) uA[0] = 0;
            if (uA[1] < 0) uA[1] = 0;
            if (uA[2] < 0) uA[2] = 0;
            if (uA[3] < 0) uA[3] = 0;
            if (uA[4] < 0) uA[4] = 0;
            if (uA[5] < 0) uA[5] = 0;
            if (uA[0] > 0) mark++;
            if (uA[1] > 0) mark++;
            if (uA[2] > 0) mark++;
            if (uA[3] > 0) mark++;
            if (uA[4] > 0) mark++;
            if (uA[5] > 0) mark++;
         for (int i = 0; i <6; i++) {
             System.out.printf("%.4f ",uA[i]);
         }
            if (mark == 1) {
                System.out.println();
                for (int i = 0; i <6 ; i++) {
                    if (uA[i]>0){
                        if (i==0)recLetter+=" H ";
                        if (i==1)recLetter+=" A ";
                        if (i==2)recLetter+=" Y ";
                        if (i==3)recLetter+=" I ";
                        if (i==4)recLetter+=" R ";
                        if (i==5)recLetter+=" T ";
                        System.out.println("Распознано букву: "+recLetter);
                    }

                }
                break;
            }
         for (int i = 0; i <6 ; i++) {
             uZ[i]=uA[i];
         }
         System.out.println();
            mark=0;

        }

    }

    public static void main(String[] args) {
        half();
        calcInputSignal();
        System.out.println();
    }
}