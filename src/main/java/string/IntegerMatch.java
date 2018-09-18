package string;

public class IntegerMatch {
    public static void main(String[] args) {
        System.out.println("-12345".matches("-?\\d+"));//可能有一个负号，后面跟着一位或多位数字
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));//可能以一个加号或减号开头，后面跟着一位或多位数字
    }
}
