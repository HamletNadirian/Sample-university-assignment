КОД ПРОГРАМИ ДЛЯ ЗАВДАННЯ №1
public class Main {

    static int F=0;
    static int F2=0;
    static int W[][]=new int[25][9];
    static int s1[]=new int[5];
    static int s2[]=new int[5];
    static int s3[]=new int[5];
    static int s4[]=new int[5];
    static int s5[]=new int[5];

    static int search(int x[],int y){
        int S=0;
        if(F>4){
        for (int i = 0; i <x.length; i++) {
            W[F][i]=W[F-5][i]+x[i]*y;
            System.out.print(W[F][i]+" ");
        }
        }
        else {
            for (int i = 0; i <x.length; i++) {
                W[F][i]=+x[i]*y;
                System.out.print(W[F][i]+" ");
            }

        }
        for (int i = 0; i <x.length ; i++) {
            S=S+((W[F][i])*(x[i]));
        }
        System.out.println("S: "+S);
        F++;
        return S;
    }
    static int inputPattern(int x[],int y){
        int S=0;
            for (int i = 0; i <x.length; i++) {
                W[F2][i]=+x[i]*y;
                System.out.print(W[F2][i]+" ");
            }
        for (int i = 0; i <x.length ; i++) {
            S=S+W[F2][i]*x[i];
        }
        System.out.println("S: "+S);
        F2++;
        return S;
    }



    static void test(int input[]){
        int mark1=0;
        int mark2=0;
        int mark3=0;
        int mark4=0;
        int mark5=0;
        for (int i = 0; i < 5; i++) {
            if(s1[i]>0)s1[i]=1;
            else if(s1[i]<0)s1[i]=-1;
            if(s2[i]>0)s2[i]=1;
            else if(s2[i]<0)s2[i]=-1;
            if(s2[i]>0)s2[i]=1;
            else if(s2[i]<0)s2[i]=-1;
            if(s3[i]>0)s3[i]=1;
            else if(s3[i]<0)s3[i]=-1;
            if(s4[i]>0)s4[i]=1;
            else if(s4[i]<0)s4[i]=-1;
            if(s5[i]>0)s5[i]=1;
            else if(s5[i]<0)s5[i]=-1;
            if(input[i]>0)input[i]=1;
            else if(input[i]<0)input[i]=-1;
        }
        System.out.print("S1:");
        for (int i = 0; i < 5; i++) {
            System.out.print(s1[i]+" ");
        }
        System.out.print("\nS2:");
        for (int i = 0; i < 5; i++) {
            System.out.print(s2[i]+" ");
        }
        System.out.print("\nS3:");
        for (int i = 0; i < 5; i++) {
            System.out.print(s3[i]+" ");
        }
        System.out.print("\nS4:");
        for (int i = 0; i < 5; i++) {
            System.out.print(s4[i]+" ");
        }
        System.out.print("\nS5:");
        for (int i = 0; i < 5; i++) {
            System.out.print(s5[i]+" ");
        }
        System.out.print("\ninput:");
        for (int i = 0; i < 5; i++) {
            System.out.print(input[i]+" ");
        }

        for (int i = 0; i <5 ; i++) {
            if (input[i]==s1[i])mark1++;
            if (input[i]==s2[i])mark2++;
            if (input[i]==s3[i])mark3++;
            if (input[i]==s4[i])mark4++;
            if (input[i]==s5[i])mark5++;
        }
        if (mark1>mark2&&mark1>mark3&&mark1>mark4&&mark1>mark5) System.out.println("\nопределена буква Г");
        if (mark2>mark1&&mark2>mark3&&mark2>mark4&&mark2>mark5)System.out.println("определена буква А");
        if (mark3>mark1&&mark3>mark2&&mark3>mark4&&mark3>mark5)System.out.println("определена буква Т");
        if (mark4>mark1&&mark4>mark2&&mark4>mark3&&mark4>mark5)System.out.println("определена буква Н");
        if (mark5>mark1&&mark5>mark2&&mark5>mark3&&mark5>mark4)System.out.println("определена буква M");
    }
    public static void main(String[] args) {
      int x1[]={ 1, 1, 1,1, -1, -1,1,-1,-1};
      int x2[]={-1, 1, -1,1, 1, 1,1,-1,1};
      int x3[]={ 1, 1, 1,-1, 1,-1,-1, 1,-1};
      int x4[]={ 1,-1, 1,1, 1, 1,1,-1,1};
      int x5[]={ 1,-1, 1,1, 1, 1,1,1,1};
      int y1=1;
      int y2=-1;
      int y3=-1;
      int y4=-1;
      int y5=-1;
                //Г
        System.out.println("Г");
        s1[0]= search(x1,y1);
        s1[1]=search(x1,y2);
        s1[2]= search(x1,y3);
        s1[3]= search(x1,y4);
        s1[4]= search(x1,y5);
                //А
        System.out.println("A");
        s2[0]=search(x2,y2);
        s2[1]=search(x2,y1);
        s2[2]=search(x2,y3);
        s2[3]=search(x2,y4);
        s2[4]=search(x2,y5);
                //Т
        System.out.println("T");
        s3[0]=search(x3,y2);
        s3[1]=search(x3,y3);
        s3[2]=search(x3,y1);
        s3[3]=search(x3,y4);
        s3[4]=search(x3,y5);
                //Н
        System.out.println("H");
        s4[0]=search(x4,y2);
        s4[1]=search(x4,y3);
        s4[2]=search(x4,y4);
        s4[3]=search(x4,y1);
        s4[4]=search(x4,y5);
        System.out.println("M");
        s5[0]=search(x5,y2);
        s5[1]=search(x5,y3);
        s5[2]=search(x5,y4);
        s5[3]=search(x5,y5);
        s5[4]=search(x5,y1);
        //Г
       System.out.println("Rec");
        int test[]={1,-1, 1,1, 1, 1,1,1,1};
         int input[]=new int[5];
        input[0]= inputPattern(test,y2);
        input[1]=inputPattern(test,y3);
        input[2]= inputPattern(test,y4);
        input[3]= inputPattern(test,y5);
        input[4]= inputPattern(test,y1);
       test(input);
    }
}
