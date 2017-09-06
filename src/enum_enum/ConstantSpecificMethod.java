package enum_enum;

import java.text.DateFormat;
import java.util.Date;

//enum允许程序员为enum实例编写方法，从而为每个enum实例赋予各自不同的行为
//要实现常亮相关的方法，你需要为enum定义一个或多个abstract方法，然后为每个enum实例实现该抽象方法
//通过相应的enum实例，我们可以调用其上的方法，通常也成为表驱动的代码(table-driven code)
public enum ConstantSpecificMethod {
    DATE_TIME {
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }

}
