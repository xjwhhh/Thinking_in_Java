package string;

public class Replacing {
    static String s = Splitting.knights;

    public static void main(String[] args) {
        System.out.println(s.replaceFirst("f\\w+", "located"));//以字母f开头，后面跟一个或多个字母，并且只替换掉第一个匹配的部分
        System.out.println(s.replaceAll("shurubbery|tree|herring", "banana"));//匹配三个单词中的任意一个，并且替换所有匹配的部分
    }
}
