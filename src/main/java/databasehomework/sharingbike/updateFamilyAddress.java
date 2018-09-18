package databasehomework.sharingbike;

import java.sql.*;
import java.util.Date;

public class updateFamilyAddress {
    static Connection con;

    public static void main(String[] args) {
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
            String sql = "UPDATE user u,(\n" +
                    "\n" +
                    " SELECT\n" +
                    " table1.user_id,\n" +
                    " table1.return_address\n" +
                    " FROM (SELECT\n" +
                    " r.user_id,\n" +
                    " r.return_address,\n" +
                    " count(*) c\n" +
                    " FROM record r\n" +
                    " WHERE date_format(return_time,'%H')>='18' and date_format(return_time,'%H')<='24'\n" +
                    " GROUP BY r.user_id, r.return_address\n" +
                    " ORDER BY r.user_id ASC) table1\n" +
                    " GROUP BY table1.user_id\n" +
                    " ORDER BY table1.c DESC) recordInfo\n" +
                    "SET u.address = recordInfo.return_address\n" +
                    "WHERE u.id = recordInfo.user_id";
            stmt.executeUpdate(sql);
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
