package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while (m.find()) {//Matcher.find()方法可用来在CharSequence中查找多个匹配
            System.out.print(m.group() + "aaa ");
        }

        System.out.println("");
        int i = 0;
        while (m.find(i)) {//接受一个整数作为参数，该整数表示字符串中字符的位置，并以其作为搜索的起点。能根据其参数的值，不断重新设定搜索的起始位置
            System.out.print(m.group() + "bbb ");
            i++;
            System.out.println(" ");
        }
    }
}
