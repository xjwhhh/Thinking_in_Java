package databasehomework.dormitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class createtable_dormitory {
    public static void main(String[] args) {
        Connection con;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/homework";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table dormitory(" +
                    "building_name varchar(255) not null primary key,campus varchar(255) not null," +
                    "cost double not null,telephone varchar(255) not null)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建宿舍楼表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建宿舍楼表失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
