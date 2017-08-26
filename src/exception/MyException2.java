package exception;

/**
 * 加入额外的构造器和成员，覆盖了Throwable.getMessage()方法，以产生更详细的信息，类似toString()
 */
public class MyException2 extends Exception {
    private int x;

    public MyException2() {
    }

    public MyException2(String msg) {
        super(msg);
    }

    public MyException2(String msg, int x) {
        super(msg);
        this.x = x;
    }

    public int val() {
        return x;
    }

    public String getMessage() {
        return "Detail Message: " + x + " " + super.getMessage();
    }
}
