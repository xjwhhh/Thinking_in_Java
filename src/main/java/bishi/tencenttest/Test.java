package bishi.tencenttest;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

    }

    public static int cal(long n) {
        int max = 1;
        for (int i = 0; i < n; i++) {
            if (max * 2 <= n) {
                max++;
            }
        }
        max--;
        if (max == 0) {
            return 0;
        } else {
            return 2 + cal(max - n) + cal(n);
        }
    }
}
