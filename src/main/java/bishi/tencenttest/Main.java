package bishi.tencenttest;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] one = new int[2];
        int[] two = new int[2];
        int[] three = new int[2];
        int[] four = new int[2];
        for (int j = 0; j < t; j++) {
            one[0] = sc.nextInt();
            two[0] = sc.nextInt();
            three[0] = sc.nextInt();
            four[0] = sc.nextInt();
            one[1] = sc.nextInt();
            two[1] = sc.nextInt();
            three[1] = sc.nextInt();
            four[1] = sc.nextInt();
            double[] len = new double[6];
            len[0] = calculate(one, two);
            len[1] = calculate(one, three);
            len[2] = calculate(one, four);
            len[3] = calculate(two, three);
            len[4] = calculate(two, four);
            len[5] = calculate(three, four);
            Arrays.sort(len);
            if (len[0] == len[1] && len[4] == len[5] && len[4] > len[1]) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }
    }

    public static double calculate(int[] node1, int[] node2) {
        return Math.pow(node1[0] - node2[0], 2) + Math.pow(node1[1] - node2[1], 2);
    }
}
