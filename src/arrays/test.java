package arrays;

public class test {
    public static void main(String[] args){
        Integer a1 = 100;
        Integer a2 = 100;
        System.out.println(a1 == a2);   // true

        Integer b1 = new Integer(100);
        Integer b2 = new Integer(100);
        System.out.println(b1 == b2);   // false

        Integer c1 = 150;
        Integer c2 = 150;
        System.out.println(c1 == c2);   // false


    }
}
