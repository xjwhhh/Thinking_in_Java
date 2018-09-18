package netease;

import java.util.Scanner;

public class Third {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = 0;
        int N = in.nextInt();
        int M = in.nextInt();
        int[] workNum = new int[N];
        int[] workDiff = new int[N];
        for (int i = 0; i < N; i++) {
            workNum[i] = in.nextInt();
            workDiff[i] = in.nextInt();

        }
        int[] people = new int[M];
        for (int i = 0; i < M; i++) {
            people[i] = in.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int max = 0;
            for (int j = 0; j < N; j++) {
                if (people[i] >= workDiff[j]) {
                    max = Math.max(max, workDiff[i]);
                }
            }
            System.out.println(max);
        }
    }
}
