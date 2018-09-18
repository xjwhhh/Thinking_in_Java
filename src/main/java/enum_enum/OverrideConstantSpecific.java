package enum_enum;

//除了实现abstract方法外，也可以覆盖常量相关的方法
public enum OverrideConstantSpecific {
    NUT, BOLT,
    WASHER {
        void f() {
            System.out.println("Overridden method");
        }
    };

    void f() {
        System.out.println("default method");
    }

    public static void main(String[] args) {
        for (OverrideConstantSpecific ocs : values()) {
            System.out.println(ocs + ": ");
            ocs.f();
        }
    }
}
