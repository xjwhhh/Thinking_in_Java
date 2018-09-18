package databasehomework.dormitory;

import java.sql.*;
import java.util.Date;

public class exchangedormitory {
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
            String male_dormitory = "";
            String female_dormitory = "";
            String sqlstr = "SELECT distinct building_name\n" +
                    "from student\n" +
                    "where department_name='软件学院' and gender='女' and student_id like 'MF%';";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();
            ResultSet resultSet = stmt.executeQuery(sqlstr);
            while (resultSet.next()) {
                female_dormitory = resultSet.getString(1);
            }
            sqlstr = "SELECT distinct building_name\n" +
                    "from student\n" +
                    "where department_name='软件学院' and gender='男' and student_id like 'MF%'";
            PreparedStatement stmt3 = con.prepareStatement(sqlstr);
            ResultSet resultSet1 = stmt3.executeQuery(sqlstr);
            while (resultSet1.next()) {
                male_dormitory = resultSet1.getString(1);
            }
            sqlstr = "update student set building_name=? where  department_name='软件学院' and gender='女' and student_id like 'MF%' ";
            PreparedStatement stmt1 = con.prepareStatement(sqlstr);
            stmt1.setString(1, male_dormitory);
            stmt1.executeUpdate();
            sqlstr = "update student set building_name=? where  department_name='软件学院' and gender='男' and student_id like 'MF%' ";
            PreparedStatement stmt2 = con.prepareStatement(sqlstr);
            stmt2.setString(1, female_dormitory)
            ;
            stmt2.executeUpdate();
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
