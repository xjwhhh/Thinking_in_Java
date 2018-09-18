package enum_enum;

import java.util.EnumMap;
import java.util.Map;

public class EnumMaps {
    public static void main(String[] args) {
        //EnumMap是一种特殊的map，它要求其中的键必须来在一个enum
        //由于enum本身的限制，因此EnumMap在内部可由数组实现，速度很快
        //与EnumSet一样，enum实例定义时的次序决定了其在EnumMap中的顺序
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("Kitchen fire");
            }
        });
        em.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("Bathroom alert");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            System.out.println(e.getKey() + ": ");
            e.getValue().action();
        }
        try {//if there's no value for a particular key
            em.get(AlarmPoints.UNITITY).action();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //enum的每个实例作为一个键总是存在的，但是如果你没有为这个键调用put()方法来存入相应值的话，其对应的值就是null
        //与常量相关的方法相比，EnumMap有一个优点，就是允许程序员改变值对象，而常量相关的方法在编译期就被固定了
        //再有多种类型的enum，而且它们之间存在相互操作的情况下，可以用EnumMap实现多路分发
    }
}
