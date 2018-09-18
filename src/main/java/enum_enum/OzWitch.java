package enum_enum;

public enum OzWitch {
    //必须先定义enum实例，如果在定义enum实例之前定义了任何方法或属性，那么在编译时就会得到错误信息
    WEST("west"),
    NORTH("north"),
    SOUTH("south"),
    EAST("east");
    private String description;

    //Constructor must be package or private access
    //虽然我们有意识地将enum的构造器声明为private，但对于它的可访问性而言，其实并没有什么变化
    //因为即使不声明为private,我们也只能在enum定义的内部使用其构造器创建enum实例
    //一点enum的定义结束，编译器就不允许我们在使用其构造器来创建任何实例了
    private OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    //覆盖toString()方法
    public String toString() {
        return name().toLowerCase();
    }

    public static void main(String[] args) {
        for (OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch + ": " + ozWitch.getDescription());
            System.out.println(ozWitch);
        }
    }

}
