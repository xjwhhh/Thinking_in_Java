package RTTI;

import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        //Insert a proxy and call again
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(real));
// newProxyInstance方法用来返回一个代理对象，这个方法总共有3个参数，
// ClassLoader loader用来指明生成代理对象使用哪个类装载器，
// Class<?>[] interfaces用来指明生成哪个对象的代理对象，通过接口指定，
// InvocationHandler h用来指明产生的这个代理对象要做什么事情。
// 所以我们只需要调用newProxyInstance方法就可以得到某一个对象的代理对象了。*/
        consumer(proxy);
    }
}
