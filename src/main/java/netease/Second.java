package netease;

import java.util.Scanner;

public class Second {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int num=0;
        int n=in.nextInt();
        int[] leftx=new int[n];
        int[] lefty=new int[n];
        int[] rightx=new int[n];
        int[] righty=new int[n];
        for(int i=0;i<n;i++){
            leftx[i]=in.nextInt();
        }
        for(int i=0;i<n;i++){
            lefty[i]=in.nextInt();
        }
        for(int i=0;i<n;i++){
            rightx[i]=in.nextInt();
        }
        for(int i=0;i<n;i++){
            righty[i]=in.nextInt();
        }

    }
}
