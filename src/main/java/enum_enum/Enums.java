package enum_enum;

import java.util.Random;

//利用泛型，使得从enum实例中进行随机选择更加一般化
public class Enums {
    private static Random rand = new Random(47);

    //T extends Enum<T>表明T是一个enum实例
    //Class<T>作为参数，可以利用Class对象得到enum实例的数组
    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
