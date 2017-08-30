package string;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {
    public String toString() {
        return "InfiniteRecursion address" + super.toString() + "\n";
        //如果用this，会将this自动转换成string,再次调用toString()，产生递归调用
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
