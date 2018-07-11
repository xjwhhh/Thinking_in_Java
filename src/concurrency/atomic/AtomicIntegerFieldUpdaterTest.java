package concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    //创建原子更新器，并设置需要更新的对象类和对象属性
    private static AtomicIntegerFieldUpdater<User> a=AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    public static void main(String[] args){
        //设置柯南年龄为10岁
        User conan=new User("conan",10);
        //柯南长了一岁，但是仍会输出旧的年龄
        System.out.println(a.getAndIncrement(conan));
        //输出柯南现在的年龄
        System.out.println(a.get(conan));
    }

    static class User{
        private String name;
        //更新类的字段必须使用public volatile修饰符
        public volatile int old;

        public User(String name,int old){
            this.name=name;
            this.old=old;
        }

        public String getName(){
            return name;
        }

        public int getOld(){
            return old;
        }
    }
}
