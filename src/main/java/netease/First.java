package netease;

import java.util.Scanner;

public class First {
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        int num=0;
//        int n=in.nextInt();
//        int k=in.nextInt();
//        for(int y=1;y<=n;y++){
//            int temp=0;
//            for(int x=k;x<=n;x++){
//                if(x%y>=k){
//                    temp=x;
//                    num+=(y-temp)*((n+1)/y);
//                    break;
//                }
//            }
//
//        }
//        System.out.println(num);
//    }

    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        int num = 0;
        for (int y = 1; y <= n; y++) {
            int temp = 0;
            for (int x = k; x <= n; x++) {
                if (x % y >= k) {
                    temp = x;
                    System.out.println(temp);
                    num += (y - temp) * (n / y);
                    num += Math.max(n % y - temp + 1, 0);
                    System.out.println(num);

                    break;
                }
            }
//            System.out.println((y-temp)*((n+1)/y));
//            System.out.println((y-temp));
//            System.out.println(((n+1)/y));
//            System.out.println("uiop");
        }
        System.out.println(num);
    }
}
