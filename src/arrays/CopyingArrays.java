package arrays;

import java.util.Arrays;

public class CopyingArrays {
    //System.arraycopy()需要的参数有：源数组，表示从原数组的什么位置开始复制的偏移量，表示从目标数组的什么位置开始复制的偏移量，以及需要复制的元素个数
    //对数组的任何越界操作都会导致异常
    //不会在执行自动包装和自动拆包，两个数组必须具有相同的确切类型
    //基本类型数组和对象数组都可以复制，如果复制对象数组只是复制了对象的引用，而不是对象本身的拷贝（浅复制）
    public static void main(String[] args) {
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);
        System.out.println("i: " + Arrays.toString(i));
        System.out.println("j: " + Arrays.toString(j));
        System.arraycopy(i, 0, j, 0, i.length);
        System.out.println("j: " + Arrays.toString(j));
        int[] k = new int[5];
        Arrays.fill(k, 103);
        System.arraycopy(i, 0, k, 0, k.length);
        System.out.println("k: " + Arrays.toString(k));
        Arrays.fill(k, 103);
        System.arraycopy(k, 0, i, 0, k.length);
        System.out.println("i: " + Arrays.toString(i));

        //Objects
        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u, new Integer(47));
        Arrays.fill(v, new Integer(99));
        System.out.println("u: " + Arrays.toString(u));
        System.out.println("v: " + Arrays.toString(v));
        System.arraycopy(v, 0, u, u.length / 2, v.length);
        System.out.println("u: " + Arrays.toString(u));
    }
}
