package exception;

/**
 * 为异常类定义一个接收字符串参数的构造器
 */
public class FullConstructors {
    public static void f() throws MyException {
        System.out.println("f()");
        throw new MyException();
    }

    public static void g() throws MyException {
        System.out.println("g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
    }
}
