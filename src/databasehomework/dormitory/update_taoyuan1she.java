package databasehomework.dormitory;

import java.sql.*;
import java.util.Date;

public class update_taoyuan1she {
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
            String sqlstr = "update dormitory set cost='1200' where building_name='陶园1舍'";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();
            stmt.execute();
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
