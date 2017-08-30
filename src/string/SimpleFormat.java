package string;

public class SimpleFormat {
    public static void main(String[] args) {
        int x = 5;
        double y = 5.332542;
        System.out.println("Row1: [" + x + " " + y + " ]");
        System.out.format("Row1: [%d,%f]\n", x, y);
        System.out.printf("Row1: [%d,%f]\n", x, y);
        //format()与printf()是等价的，它们只需要一个简单的格式化字符串，加上一串参数即可，每个参数对应一个格式修饰符
    }
}
