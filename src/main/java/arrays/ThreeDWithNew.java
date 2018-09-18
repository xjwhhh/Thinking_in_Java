package arrays;

import java.util.Arrays;

public class ThreeDWithNew {
    public static void main(String[] args) {
        //3-D array with fixed length
        int[][][] a = new int[2][2][4];
        //arrays.deepToString()可以将多维数组转化为多个String
        System.out.println(Arrays.deepToString(a));
    }
}
