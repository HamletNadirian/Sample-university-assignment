import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
Coefficient correlation Pearson
Coefficient correlation Kendall 
Student's t-distribution
Chebyshev distance
*/

public class Main {
    static int P = 0, Q = 0;
    double[] standard_deviation = new double[8];
    static double maxj;
    static double maxk;
    static double mink;
    static double minj;
    static final int N = 137;
    static final int m = 8;
    static double mx = 0;
    static double mjx[] = new double[m];
    static double tau = 0;
    static double[][] ted = new double[N][m];
    static double R = 0;
    static ArrayList<String> number = new ArrayList<>();
    static ArrayList<String> number_of_leukocytes = new ArrayList<>();
    static ArrayList<String> number_of_lymphocytes = new ArrayList<>();
    static ArrayList<String> T_lymphocytes = new ArrayList<>();
    static ArrayList<String> T_helpers = new ArrayList<>();
    static ArrayList<String> T_suppressors_reduce = new ArrayList<>();
    static ArrayList<String> theophylline_sensiti = new ArrayList<>();
    static ArrayList<String> theophylline_resista = new ArrayList<>();
    static ArrayList<String> B_lymphocytes = new ArrayList<>();
    static ArrayList<String> Diagnosis = new ArrayList<>();
    static double xk_spearman[] = new double[138];
    static double xj_spearman[] = new double[138];
    static double rangk_kendall[] = new double[138];
    static double rangj_kendall[] = new double[138];
    static ArrayList<String>[] arrayListsKandall = new ArrayList[8];
    static ArrayList<String>[] arrayListsPirson = new ArrayList[8];
    static ArrayList<String>[] arrayListsDch = new ArrayList[8];
    static double arrayPirson[] = new double[64];
    static double arrayKendall[] = new double[64];
    static double arrayDch[] = new double[64];

    static double xk_kendall[] = new double[138];
    static double xj_kendall[] = new double[138];

    static double avgDevition(ArrayList<String> x_ar) {
        double avg = 0;
        double x[] = new double[137];
        for (int i = 0; i < N; i++) {
            x[i] = Double.parseDouble(x_ar.get(i));
        }
        for (int i = 0; i < N; i++) {
            avg += x[i] / N;
        }
        System.out.println(avg);

        return avg;
    }
    static void readColumn() throws IOException {
        FileReader fr = new FileReader("lab1_v2.txt");
        BufferedReader reader = new BufferedReader(fr);
        String line2;
        while ((line2 = reader.readLine()) != null) {
            String[] splited = line2.split("\\s");
            number_of_leukocytes.add(splited[0]);
            number_of_lymphocytes.add(splited[1]);
            T_lymphocytes.add(splited[2]);
            T_helpers.add(splited[3]);
            T_suppressors_reduce.add(splited[4]);
            theophylline_sensiti.add(splited[5]);
            theophylline_resista.add(splited[6]);
            B_lymphocytes.add(splited[7]);
        }

    }

    static void ReadTed() throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("lab1_v2.txt")));
        int rows = 137;
        int columns = 8;

        while (sc.hasNextLine()) {
            for (int i = 0; i < N; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < m; j++) {
                    ted[i][j] = Double.parseDouble(line[j]);
                    System.out.print(ted[i][j] + " ");
                }
                System.out.println("\t");
            }

        }
    }

    static void Calc_m() {
        for (int i = 0; i < 137; i++) {
            for (int j = 0; j < m; j++) {
                mx += ted[i][j];

            }
        }
        mx = mx / N / m;
        System.out.print("mx = " + mx + "");
        System.out.print("Геометрический центр облака данных = " + mx + "");
    }

    static void Calc_s() {
        double s = 0;

        for (int i = 0; i < 137; i++) {
            for (int j = 0; j < m; j++) {
                s = s + Math.pow(ted[i][j] - mx, 2);
            }

        }
        System.out.println("s:" + s);
        s = s / Math.sqrt(s / (N * m - 1));
        // System.out.println();
        // System.out.println("s = "+s);
        System.out.println("Среднеквадратичное отклонение облака данных = " + s);
    }

    static void Calc_R() {
        R = Math.abs(ted[0][1] - mx);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < m; j++) {
                if (R < Math.abs(ted[i][j]) - mx) {
                    R = Math.abs(ted[i][j] - mx);
                }
            }
        }
        System.out.println(R);
    }

    static void Calc_mj() {
        for (int j = 1; j < m; j++) {
            mjx[j] = 0;
            for (int i = 0; i < N; i++) {
                mjx[j] = mjx[j] + ted[i][j];
            }
            mjx[j] = mjx[j] / N;
        }

    }

    static double Pirson(ArrayList<String> al_xk, ArrayList<String> al_xj) {
        double xk[] = new double[137];
        double xj[] = new double[137];
        double sk = 0;
        double sj = 0;
        double skj = 0;
        double sk2 = 0;
        double sj2 = 0;
        for (int i = 0; i < N; i++) {
            xk[i] = Double.parseDouble(al_xk.get(i));
            xj[i] = Double.parseDouble(al_xj.get(i));
        }
        for (int i = 0; i < N; i++) {
            sk = sk + xk[i];
            sj = sj + xj[i];
            skj = skj + xk[i] * xj[i];
            sk2 = sk2 + Math.pow(xk[i], 2);
            sj2 = sj2 + Math.pow(xj[i], 2);
        }
        //  System.out.println();
        tau = (N * skj - sk * sj) / Math.sqrt((N * sk2 - Math.pow(sk, 2)) * (N * sj2 - Math.pow(sj, 2)));
        System.out.printf("%4f\t", tau);

        //  System.out.println(tau+" ");
        return tau;
    }

    static void MySortList(double x[], double y[], int N) {
        double a;
        double f = 1.0;

        for (int j = 0; j < N - 1; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (x[i] > x[i + 1]) {
                    a = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = a;
                    a = y[i];
                    y[i] = y[i + 1];
                    y[i + 1] = a;
                    f = 0;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            xk_kendall[i] = x[i];
        }
        for (int i = 0; i < N; i++) {
            xj_kendall[i] = y[i];

        }
    }

    static void MySortList_rangk_k(double x[], double y[], int N) {

        double a;
        double f = 1.0;

        for (int j = 0; j < N - 1; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (x[i] > x[i + 1]) {
                    a = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = a;
                    a = y[i];
                    y[i] = y[i + 1];
                    y[i + 1] = a;
                    f = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            xk_kendall[i] = x[i];
        }
        for (int i = 0; i < N; i++) {
            rangk_kendall[i] = y[i];
        }
    }

    public static double[] Calc_rang(double x[], int N) {
        double rang[] = new double[137];
        int kol = 0;
        for (int i = 0; i < N; i++) {
            if (x[i] == x[i + 1]) {
                kol++;
            } else {
                for (int j = i - kol; j <= i; j++) {
                    rang[j] = i - kol / 2;
                }
                kol = 0;
            }
        }
        for (int j = N - kol; j < N; j++) {
            rang[j] = N - kol / 2;
        }
        return rang;
    }

    private static double Calc_V(double[] rang, int N) {
        int kol = 0;
        double V = 0;
        double ti = 1;
        for (int i = 0; i < N - 1; i++) {
            if (rang[i] == rang[i + 1]) {
                kol++;
            } else {
                V = V + Math.pow(ti, 2) - ti;
                ti = 1;
            }

        }
        V = (V + Math.pow(ti, 2) - ti) / 2;
        return V;
    }

    private static void Calc_PQ(double rangk[]) {
        P = 0;
        Q = 0;
        for (int i = 0; i < N; i++) {
            for (int k = i; k < N; k++) {
                if (rangk[k] > rangk[i]) {
                    P++;
                } else if (rangk[k] < rangk[i]) {
                    Q++;
                }
            }

        }


    }

    static double Kendall(ArrayList<String> al_xk, ArrayList<String> al_xj) {

        for (int i = 0; i < N; i++) {
            xk_kendall[i] = Double.parseDouble(al_xk.get(i));
            xj_kendall[i] = Double.parseDouble(al_xj.get(i));
        }
        MySortList(xk_kendall, xj_kendall, N);
        double rangk[] = Calc_rang(xk_kendall, N);
        double Vk = Calc_V(rangk, N);
        MySortList_rangk_k(xj_kendall, rangk, N);

        double rangj[] = Calc_rang(xj_kendall, N);
        double Vj = Calc_V(rangj, N);
        Calc_PQ(rangk_kendall);
        double a = (Math.pow(N, 2) - N) / 2;

        double r = (P - Q) / (Math.sqrt((a - Vk) * (a - Vj)));
        System.out.printf("%4f\t", r);
        return r;
    }


    static void callKandall() {

        arrayListsKandall[0] = number_of_leukocytes;
        arrayListsKandall[1] = number_of_lymphocytes;
        arrayListsKandall[2] = T_lymphocytes;
        arrayListsKandall[3] = T_helpers;
        arrayListsKandall[4] = T_suppressors_reduce;
        arrayListsKandall[5] = theophylline_sensiti;
        arrayListsKandall[6] = theophylline_resista;
        arrayListsKandall[7] = B_lymphocytes;
        int count=0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                arrayKendall[count]=Kendall(arrayListsKandall[i], arrayListsKandall[j]);
                count++;
            }

            System.out.println("\t");

        }

    }

    static void callPirson() {
        arrayListsPirson[0] = number_of_leukocytes;
        arrayListsPirson[1] = number_of_lymphocytes;
        arrayListsPirson[2] = T_lymphocytes;
        arrayListsPirson[3] = T_helpers;
        arrayListsPirson[4] = T_suppressors_reduce;
        arrayListsPirson[5] = theophylline_sensiti;
        arrayListsPirson[6] = theophylline_resista;
        arrayListsPirson[7] = B_lymphocytes;
        int count=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            arrayPirson[count]=Pirson(arrayListsPirson[i], arrayListsPirson[j]);
            count++;
            }
            System.out.println("\t");
        }

    }

    private static double Calc_T(double[] rang, int N) {
        double T = 0.0;
        double ti = 1;
        int kol = 0;
        for (int i = 0; i < N - 1; i++) {
            if (rang[i] == rang[i + 1]) {
                kol++;
            } else {
                T = T + Math.pow(ti, 3) - ti;
                ti = 1;
            }
        }
        T = (T + Math.pow(ti, 3)) / 12;
        return T;
    }

    static void maxmink(ArrayList<String> al_xk, int N, double max, double min) {
        double x[] = new double[N];
        for (int i = 0; i < N; i++) {
            x[i] = Double.parseDouble(al_xk.get(i));
        }
        max = x[1];
        min = x[1];
        for (int i = 0; i < N; i++) {
            if (x[i] < min) {
                min = x[i];
            } else if (x[i] > max) {
                max = x[i];
            }
            maxk = max;
            mink = min;

        }
    }

    static void maxminj(ArrayList<String> al_xk, int N, double max, double min) {
        double x[] = new double[N];
        for (int i = 0; i < N; i++) {
            x[i] = Double.parseDouble(al_xk.get(i));
        }
        max = x[1];
        min = x[1];
        for (int i = 0; i < N; i++) {
            if (x[i] < min) {
                min = x[i];
            } else if (x[i] > max) {
                max = x[i];
            }
            maxj = max;
            minj = min;
        }
    }

  

    static double Dch(ArrayList<String> al_xk, ArrayList<String> al_xj, int m) {
        double a[] = new double[137];
        double b[] = new double[137];
        for (int i = 0; i < N; i++) {
            a[i] = Double.parseDouble(al_xk.get(i));
            b[i] = Double.parseDouble(al_xj.get(i));
        }
        double d = Math.abs(a[0] - b[0]);
        for (int k = 0; k < m; k++) {
            if (d < Math.abs(a[k] - b[k])) {
                d = Math.abs(a[k] - b[k]);
            }

        }           System.out.printf("%4f\t", d);

        return d;
    }
    static void CallDch(){

     arrayListsDch[0]=number_of_leukocytes;
     arrayListsDch[1]=number_of_lymphocytes;
     arrayListsDch[2]=T_lymphocytes;
     arrayListsDch[3]=T_helpers;
     arrayListsDch[4]=T_suppressors_reduce;
     arrayListsDch[5]=theophylline_sensiti;
     arrayListsDch[6]=theophylline_resista;
     arrayListsDch[7]=B_lymphocytes;
        int count=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                arrayDch[count]=Dch(arrayListsDch[i], arrayListsDch[j],m);
                count++;
            }
            System.out.println("\t");
        }

    }
    static void Centering_and_normalizing(ArrayList<String> al_xk) {
        double x[] = new double[137];
        double AVG = avgDevition(al_xk);
        Calc_R();
        for (int i = 0; i < N; i++) {
            x[i] = Double.parseDouble(al_xk.get(i));
        }


        double[] standart = new double[N];
        System.out.println("Centering_and_normalizing");
        for (int i = 0; i < N; i++) {
            standart[i] = (x[i] - AVG) / R;
            System.out.printf("%.3f\t", standart[i]);
            if (i % 5 == 0 && i != 0) {
                System.out.println();
            }
        }


    }

    static void student_cof_Pir() {
        double t = 1.9777;
        double kr = 0;
        double C[][] = new double[8][8];
        double oneToDim[][] = new double[8][8];
        int c = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                oneToDim[i][j] = arrayPirson[c];
                c++;
            }

        }

        for (int j = 0; j < 8; j++) {
            for (int k = j; k < 8; k++) {
                if (j != k) {
                    kr = Math.abs(oneToDim[j][k]) * Math.sqrt(N - 2) / Math.sqrt(1 - Math.pow(oneToDim[j][k], 2));
                }
                if (kr > t) {
                    C[j][k] = 1;
                    C[k][j] = 1;
                } else {
                    C[j][k] = 0;
                    C[k][j] = 0;

                }
                if (C[j][k] == 1 && oneToDim[j][k] < 0) {
                    C[j][k] = -1;
                    C[k][j] = -1;
                }
                if (j == k)
                    C[j][k] = 1;
            }
        }
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                System.out.print(C[i][j]+" ");
            }
            System.out.println("\t");
        }
    }

   static void student_cof(double arrayNameAuth[]) {
       double t = 1.9777;
       double kr = 0;
       double C[][] = new double[8][8];
       double oneToDim[][] = new double[8][8];
       int c = 0;
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               oneToDim[i][j] = arrayNameAuth[c];
               c++;
           }

       }

       for (int j = 0; j < 8; j++) {
           for (int k = j; k < 8; k++) {
               if (j != k) {
                   kr = Math.abs(oneToDim[j][k]) * Math.sqrt(N - 2) / Math.sqrt(1 - Math.pow(oneToDim[j][k], 2));
               }
               if (kr > t) {
                   C[j][k] = 1;
                   C[k][j] = 1;
               } else {
                   C[j][k] = 0;
                   C[k][j] = 0;

               }
               if (C[j][k] == 1 && oneToDim[j][k] < 0) {
                   C[j][k] = -1;
                   C[k][j] = -1;
               }
               if (j == k)
                   C[j][k] = 1;
           }
       }
       for (int i = 0; i <8 ; i++) {
           for (int j = 0; j <8 ; j++) {
               System.out.print(C[i][j]+" ");
           }
           System.out.println("\t");
       }
   }

    public static void main(String[] rgs) throws IOException {
        ReadTed();
        readColumn();
        callPirson();
        System.out.println("\n");
        callKandall();
        student_cof(arrayPirson);
        System.out.println();
        student_cof(arrayKendall);
        CallDch();

    }
}
