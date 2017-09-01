package RTTI;

import java.lang.reflect.Proxy;

public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(SomeMethods.class.getClassLoader(), new Class[]{SomeMethods.class}, new MethondSelector(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
}
