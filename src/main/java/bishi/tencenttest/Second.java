package bishi.tencenttest;

import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt();
        int A = in.nextInt();
        int X = in.nextInt();
        int B = in.nextInt();
        int Y = in.nextInt();
//        int K=5;
//        int A=2;
//        int B=3;
//        int X=3;
//        int Y=3;
        long result = 0;
        if (A > B) {
//            System.out.println(1);
            result = allocate(K, A, B, X, Y);

        } else {
//            System.out.println(2);
            result = (allocate(K, B, A, Y, X));
        }
        System.out.println(result % 1000000007);

    }

    public static long allocate(int k, int m, int n, int x, int y) {
        int q = k / m;
//        int w=k%m;
        long result = 0;
//        System.out.println(q);
        for (int i = Math.min(x, q); i >= 0; i--) {
            int e = (k - i * m) % n;
//            System.out.println(e);
            if (e == 0) {
                int r = (k - i * m) / n;
                if (r <= y) {
                    result += (cal(x) / (cal(i) * cal(x - i))) * (cal(y) / (cal(r) * cal(y - r)));
                }
//                System.out.println(i);
//                System.out.println(result);
            }
        }
        return result;
    }

//    public int allolcate(int k,int a,int x,int b,int y ){
//        int result=0;
//        if(k<a){
//            return 0;
//        }
//        if(k<b){
//            return 0;
//        }
//        if(k>=a&&k>=b){
//            return
//        }
//        if(k>=a&&k<b){
//            if(x>0){
//                result+= 1+allolcate(k-a,a,x-1,b,y);
//
//        }
//        if(k>=b&&k<a){
//            if(y>0){
//                result+= 1+allolcate(k-b,a,x,b,y-1);
//            }
//        }
//    }

    public static long cal(int a) {
        long result = 1;
        for (long i = a; i >= 1; i--) {
            result *= i;
        }
        return result;
    }
}
