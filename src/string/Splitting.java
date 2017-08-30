package string;

import java.util.Arrays;

public class Splitting {
    public static String knights = "Then, when you have found the shrubbery, you must "
            + "cut down the mightiest tree in the forest... "
            + "with... a herring";

    //将字符串从正则表达式匹配的地方切开
    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");//按空格划分
        split("\\W+");//非单词字符，将标点字符删除了，如果是"\w"表示一个单词字符
        split("n\\W+");//字母n后面跟着一个或多个非单词字符
    }
}
