/*Рисунок 3 – робота програми завдання №4
Завдання №5
Розробити дискретну мережу Кохонена, здатну розпізнавати п’ять букв Вашого прізвища або імені. При цьому обґрунтуйте вибір: числа нейронів мережі, попередній вибір параметрів   R та ваг зв’язків нейронної мережі для Вашого випадку
Код програми завдання №5
*/


import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //static int vector[][]={{0,0,0,1},{0,0,1,1},{1,0,0,0},{1,1,0,0}};
    static String[] letter={"Г","А","Н","Р","Т"};
    static int vector[][]={

            {1,1,1,1,1,
             1,0,0,0,0,
             1,0,0,0,0,
             1,0,0,0,0,
             1,0,0,0,0,
             1,0,0,0,0
            },
            {0,1,1,1,0,
             1,0,0,0,1,
             1,1,1,1,1,
             1,0,0,0,1,
             1,0,0,0,1,
             1,0,0,0,1
            },
            {1,0,0,0,1,
             1,0,0,0,1,
             1,1,1,1,1,
             1,0,0,0,1,
             1,0,0,0,1,
             1,0,0,0,1
            },
            {1,1,1,1,1,
             1,0,0,0,1,
             1,1,1,1,1,
             1,0,0,0,0,
             1,0,0,0,0,
             1,0,0,0,1,
            },
            {1,1,1,1,1,
             0,0,1,0,0,
             0,0,1,0,0,
             0,0,1,0,0,
             0,0,1,0,0,
             0,0,1,0,0
            },
            {       1,1,1,1,1,
                    0,0,1,0,0,
                    0,0,1,0,0,
                    0,0,1,0,0,
                    0,0,1,0,0,
                    0,1,1,1,0
            },

    };

    static int neuron = 4;
    static int m = neuron/2;
    static int A = 4;
    static double k = 0.25;
    static double alfa0 = 0.95;
    static double mq[][]={
            {0.7,0.6,0.5,04,0.3,0.3},
            {0.4,0.3,0.2,0.15,0.1,0.1},
            {0.5,0.5,0.5,0.5,0.5,0.5},
            {0.2,0.3,0.4,0.5,0.9,0.9},
            {0.4,0.32,0.31,0.3,0.2,0.2},
            {0,3,0.2,0.1,0.1,0.5,0.5},
    };
    static double destination[][]=new double[10000][6];
    static int index_i=0;
    static int index_j=0;

    static ArrayList<double[][]> arrayList = new ArrayList<double[][]>();

    static double R = 0;
    static int res=0;
    static int res2=0;

    static double[][] run(){
        double temp1[][]=new double[36][6];
        double temp2[][]=new double[36][6];
        int n1=0;
        int n2=1;
        int n3=0;

        double alf = 0.40;
        double alf2 = 0.6;

        int one=1;
        double res1=0;
        int counter = 0;
        boolean array=false;
        boolean array2=false;
        alf2=1;
        for (int i = 1; i <1000 ; i++) {
            alf2=alf2*0.6;
            alf=one-alf2;
            n3=0;
            extracted(n1, n2,n3,alf,alf2);
            n3=1;
            extracted(n1, n2,n3,alf,alf2);
            n3=2;
            extracted(n1, n2,n3,alf,alf2);
            n3=3;
            extracted(n1, n2,n3,alf,alf2);
            for ( index_i= 0; index_i < 6; index_i++) {
                for ( index_j = 0; index_j <6 ; index_j++) {
                    destination[res][index_j]=mq[index_i][index_j];
                }
                res++;
            }

            if(i%2==0){
                for ( index_i= 0; index_i < 6; index_i++) {
                    for ( index_j = 0; index_j <6 ; index_j++) {
                        temp1[index_i][index_j]=mq[index_i][index_j];
                    }
                    //  res++;
                }
                array=true;
            }
            else {
                for ( index_i= 0; index_i < 6; index_i++) {
                    for ( index_j = 0; index_j <6 ; index_j++) {
                        temp2[index_i][index_j]=mq[index_i][index_j];
                    }
                    // res2++;
                }
                array2=true;
            }
            if (array&&array2){
                for ( index_i= 0; index_i < 6; index_i++) {
                    for ( index_j = 0; index_j <6 ; index_j++) {
                        res1 = temp2[index_i][index_j]-temp1[index_i][index_j];
                        if (res1<=0.0005)counter++;
                    }
                }

            }
            if (counter==36) {
                System.out.println("Количество итерации:"+i);

             /*   for ( index_i= 0; index_i < 5; index_i++) {
                    for ( index_j = 0; index_j <5 ; index_j++) {
                        System.out.printf("%5f ",temp1[index_i][index_j]);
                    }
                    System.out.println();
                }*/
                System.out.println();
                for ( index_i= 0; index_i < 6; index_i++) {
                    for ( index_j = 0; index_j <6 ; index_j++) {
                           if (temp2[index_i][index_j]>=0.5)
                               // temp2[index_i][index_j]=Math.ceil(temp2[index_i][index_j]);
                                temp2[index_i][index_j]=1.0;
                            else if(temp2[index_i][index_j]<0.5)
                                temp2[index_i][index_j]=Math.floor(temp2[index_i][index_j]);

                        System.out.printf("%5f ",temp2[index_i][index_j]);
                    }
                    System.out.println();
                }
                break;
            }
            counter=0;
        }

   return temp2;

    }

    private static void extracted(int n1, int n2,int n3,double alf1,double alf2) {
        double da1=0;
        double da2=0;

        for (int i = 0; i <6 ; i++) {
            da1=da1+Math.pow((mq[i][n1]-vector[n3][i]),2);
            da2=da2+Math.pow((mq[i][n2]-vector[n3][i]),2);
        }
        if(da1>da2){

            for (int i = 0; i <6 ; i++) {
                mq[i][n2]=alf1*mq[i][n2]+alf2*vector[n3][i];


            }
        }
        if(da1<da2){
            for (int i = 0; i <6 ; i++) {
                mq[i][n1]=alf1*mq[i][n1]+alf2*vector[n3][i];
            }
        }

    }
    static void copyTo(){
        double output [] [] = run();
        double result[][]=new double[6][5];
        double input[]=new double[6];
        for (int i = 0; i <6; i++) {
            for (int j = 0; j <5 ; j++) {
                result[i][j]=output[i][j];
            }
        }
        for (int i = 0; i <6 ; i++) {
            input[i]=output[i][5];
        }
        System.out.println();
    check(result,input);
    }

    private static void check(double[][] result, double[] input) {
        int claster[] = new int[5];
        int clasterSum[] = new int[5];
/*        for (int i = 0; i <6; i++) {
            for (int j = 0; j <5 ; j++) {
                if (result[i][j]==1){
                    claster[j]+=1+i/2;
                }
            }
        }*/
        System.out.println();

        for (int i = 0; i < 5; i++) {
            claster[i] = i;
            System.out.print("Claster №" + claster[i] + "-" + letter[i]);
            System.out.println();
        }
        int max[] = new int[5];
   /*     for (int i = 0; i < 6; i++) {
                if(result[i][0]==input[i]){
                    max[0]+=1;
        }
    }*/

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <5 ; j++) {
                if (result[i][j] == input[i]) {
                    max[j] += 1;
                }
            }
        }
        System.out.println("Номер кластер входной буквы = " + maxArrayIndex(max, 0, max.length-1));

        System.out.println();
    }
    static int maxArrayIndex(int[] X, int p, int r) {
        int currentMaxIndex = 0;
        for (int i = 0; i < X.length; i++) {
            if(X[i] > X[currentMaxIndex]){
                currentMaxIndex = i;
            }
        }
        return currentMaxIndex;
    }
    public static void main(String[] args) {
     copyTo();
    }

}
