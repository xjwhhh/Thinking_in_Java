package enum_enum;

import java.util.EnumSet;

import static enum_enum.AlarmPoints.*;

//引入EnumSet,是为了通过enum创造一种替代品，以替代传统的基于int的位标志，这种标志可以用来表达某种开/关信息
//EnumSet基于long
//向EnumSet添加enum实例的顺序并不重要，因为其输出的次序决定于enum实例定义时的次序
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);//空集合
        points.add(BATHROOM);
        System.out.println(points);
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        System.out.println(points);
        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        System.out.println(points);
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        System.out.println(points);
        points = EnumSet.complementOf(points);//反向
        System.out.println(points);
    }
}
