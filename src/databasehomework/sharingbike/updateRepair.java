package databasehomework.sharingbike;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class updateRepair {
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
            String str2 = "DROP EVENT IF EXISTS updateRepair;";
            String str3="CREATE DEFINER =`root`@`localhost` EVENT updateRepair\n" +
                    " ON SCHEDULE EVERY 1 MONTH STARTS '2017-11-01 00:00:00'\n" +
                    " ON COMPLETION PRESERVE ENABLE DO\n" +
                    "\n" +
                    " BEGIN\n" +
                    " INSERT INTO `repair`\n" +
                    " SELECT\n" +
                    " repairResult.rbid,\n" +
                    " repairResult.repid\n" +
                    " FROM\n" +
                    " (SELECT\n" +
                    " r1.bike_id rbid,\n" +
                    " r1.return_address repid,\n" +
                    " r1.return_time retime\n" +
                    " FROM `record` r1,\n" +
                    " (SELECT\n" +
                    " r.bike_id bid,\n" +
                    " max(r.return_time) etime,\n" +
                    " sum(minute(r.return_time - r.borrow_time) + 60 * hour(r.return_time - r.borrow_time)) totalminute\n" +
                    " FROM `record` r\n" +
                    " GROUP BY bid\n" +
                    " HAVING totalminute > 200 * 60) unavailableBikes\n" +
                    " WHERE r1.bike_id = unavailableBikes.bid AND r1.return_time = unavailableBikes.etime\n" +
                    " GROUP BY rbid, repid) repairResult;\n" +
                    " END\n";
            stmt.addBatch(str2);
            stmt.addBatch(str3);
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
