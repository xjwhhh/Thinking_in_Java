package arrays;

import java.util.Arrays;

public class FillingArrays {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = new boolean[size];
        Arrays.fill(a1, true);//fill()，只能用同一个值填充各个位置，而针对对象而言，就是复制同一个引用进行填充
        System.out.println("a1: " + a1.toString());
        String[] a2 = new String[size];
        Arrays.fill(a2, "Hello");
        System.out.println("a2: " + Arrays.toString(a2));
        Arrays.fill(a2, 3, 5, "World");//可以填充整个数组，也可以只填充数组的某个区域
        System.out.println("a2: " + Arrays.toString(a2));
    }
}
