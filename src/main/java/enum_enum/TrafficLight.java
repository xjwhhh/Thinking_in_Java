package enum_enum;

enum Signal {GREEN, YELLOW, RED}

public class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        //在case语句中不需要使用enum类型来修饰enum实例
        // 如果在case语句中调用return，就需要default语句
        //否则，即使没有覆盖所有分支，也不需要default
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(trafficLight);
            trafficLight.change();
        }
    }
}
