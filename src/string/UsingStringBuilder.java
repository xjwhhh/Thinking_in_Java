package string;

import java.util.Random;

public class UsingStringBuilder {
    public static Random rand = new Random(47);

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
            result.append(rand.nextInt(100));
            result.append(", ");

        }
        result.delete(result.length() - 2, result.length());//删除最后一个逗号与空格，以便添加右括号
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usingStringBuilder = new UsingStringBuilder();
        System.out.println(usingStringBuilder.toString());
    }

    /**
     * 如果要在toString()方法中使用循环，最好自己创建一个StringBuilder对象，否则编译器会每经过循环一次就构造一个新的StringBuilder对象
     * StringBuilder非线程安全，StringBuffer线程安全，前者快一些
     */
}
