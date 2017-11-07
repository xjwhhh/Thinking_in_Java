package databasehomework.sharingbike;

import java.sql.*;
import java.util.Date;

public class updateCost {
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
            String recordSqlStr = "UPDATE record r\n" +
                    "SET r.cost = if(minute(r.return_time - r.borrow_time) <= 30, 1,\n" +
                    " if(minute(r.return_time - r.borrow_time) <= 60, 2, if(minute(r.return_time - r.borrow_time) <= 90, 3, 4)));";
            stmt.addBatch(recordSqlStr);
            String userSqlStr = "UPDATE user u, (SELECT\n" +
                    " r.user_id,\n" +
                    " sum(r.cost) cost\n" +
                    " FROM record r\n" +
                    " GROUP BY r.user_id) costInfo\n" +
                    "SET u.balance = u.balance - costInfo.cost\n" +
                    "WHERE u.id = costInfo.user_id";
            stmt.addBatch(userSqlStr);
            stmt.executeBatch();
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
