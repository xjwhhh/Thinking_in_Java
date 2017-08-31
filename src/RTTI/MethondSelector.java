package RTTI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethondSelector implements InvocationHandler{
    private Object proxied;
    public MethondSelector(Object proxied){
        this.proxied=proxied;
    }

    //可以通过传递其余的参数来过滤某些方法调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("interesting")){
            System.out.println("Proxy detected the interesting method");
        }
        else {
            return method.invoke(proxied, args);
        }
        return null;
    }
}
