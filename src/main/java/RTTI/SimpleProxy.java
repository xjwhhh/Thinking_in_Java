package RTTI;

/**
 * 代理，将额外的操作从实际对象中分离到不同的地方，特别是当你希望能够很容易作出修改，从没有使用额外操作转为使用这些操作，或者反过来时
 */
public class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse" + arg);
        proxied.somethingElse(arg);
    }
}
