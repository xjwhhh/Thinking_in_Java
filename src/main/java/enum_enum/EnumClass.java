package enum_enum;

enum Shrubbery {GROUND, CRAWLING, HANGING}

public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s + " ordinal: " + s.ordinal());//返回一个int值，是每个enum实例在声明时的次序，从零开始
            System.out.println(s.compareTo(Shrubbery.CRAWLING));
            System.out.println(s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());//所属的enum类
            System.out.println(s.name());//返回enum实例声明时的名字，与toString()方法效果相同
            System.out.println("..................");
        }

        //produce an enum value from a string name
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);//根据给定的名字返回相应的enum实例，如果不存在给定名字的实例，将会抛出异常
            System.out.println(shrubbery);
        }
    }
}
