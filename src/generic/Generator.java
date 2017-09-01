package generic;

/**
 * 泛型也可用于接口，例如生成器，这是一种专门负责创建对象的类，实际上这是工厂方法设计模式的一种应用
 * 当使用生成器创建新的对象时，不需要任何参数，而工厂方法一般需要参数，也就是说，生成器无需额外的信息就知道如何创建新对象
 * 一般而言，一个生成器只定义一个方法，该方法用以产生新的对象
 *
 * @param <T>
 */
public interface Generator<T> {
    T next();
}
