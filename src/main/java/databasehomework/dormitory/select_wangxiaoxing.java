package databasehomework.dormitory;

import java.sql.*;
import java.util.Date;

public class select_wangxiaoxing {
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
            String sqlstr = "select distinct student.department_name \n" +
                    "from\n" +
                    "(select s.building_name bdname\n" +
                    "from student s\n" +
                    "where s.student_name='王小星') as temp,\n" +
                    "student\n" +
                    "where student.building_name=temp.bdname\n";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();
            ResultSet resultSet = stmt.executeQuery(sqlstr);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
