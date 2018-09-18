package RTTI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    /**
     * 在动态代理技术里，由于不管用户调用代理对象的什么方法，都是调用开发人员编写的处理器的invoke方法（这相当于invoke方法拦截到了代理对象的方法调用）。
     * 并且，开发人员通过invoke方法的参数，还可以在拦截的同时，知道用户调用的是什么方法，因此利用这两个特性，就可以实现一些特殊需求，
     * 例如：拦截用户的访问请求，以检查用户是否有访问权限、动态为某个对象添加额外的功能。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method " + method + ", args" + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.print(" " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}
