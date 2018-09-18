package databasehomework.sharingbike;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;

public class insertdata {
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
//            insertUser();
            insertBike();
            insertRecord();
//            insertAddress();
//            updateCost();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser() {
        String sqlstr = "insert into user (id,name,telephone,balance) values(?,?,?,?)";
        Date start = new Date();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            String pathname = "C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\user.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    String[] info = line.split(";");
                    stmt.setInt(1, Integer.valueOf(info[0]));
                    stmt.setString(2, info[1]);
                    stmt.setString(3, info[2]);
                    stmt.setDouble(4, Double.valueOf(info[3]));
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println("插入用户表运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
    }

    public static void insertBike() {

        Date start = new Date();
        try {
            Statement statement = con.createStatement();
            String pathname = "C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\bike.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    String sqlstr = "insert into bike (id) values(";
                    sqlstr += (line + ")");
                    statement.addBatch(sqlstr);
                }

            }
            statement.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println("插入单车表运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
    }

    public static void insertRecord() {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into record (user_id,bike_id,borrow_address,borrow_time,return_address,return_time) values");
        Date start = new Date();
        try {
            String pathname = "C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\record.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            String[] info = line.split(";");
            String zero = info[0].substring(1);
            sql.append("("+zero+",'"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"'),");
            sql.deleteCharAt(sql.length() - 1);
            Statement stmt=con.createStatement();
            stmt.addBatch(sql.toString());
            stmt.executeBatch();
            sql = new StringBuffer();
            sql.append("insert into record (user_id,bike_id,borrow_address,borrow_time,return_address,return_time) values");
            int count=0;

            while (line != null) {
                if (count < 10000) {
                    line = br.readLine();
                    if (line != null) {
                        info = line.split(";");
                        sql.append("(" + info[0] + ",'" + info[1] + "','" + info[2] + "','" + info[3] + "','" + info[4] + "','" + info[5] + "'),");
                    }
                    count++;
                }
                else{
                    sql.deleteCharAt(sql.length() - 1);
                    stmt.addBatch(sql.toString());
                    stmt.executeBatch();
                    count = 0;
                    sql = new StringBuffer();
                    sql.append("insert into record (user_id,bike_id,borrow_address,borrow_time,return_address,return_time) values");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println("插入借车记录表运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
    }

    public static void updateCost(){
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAddress() {
        HashSet<String> addresses=new HashSet<String>();
        String sqlstr = "insert into address (name) values(?)";
        Date start = new Date();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            String pathname = "C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\record.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    String[]info = line.split(";");
                    addresses.add(info[2]);
                    addresses.add(info[4]);
                }
            }
            for(String s:addresses){
                stmt.setString(1,s);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println("插入地址表运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
    }

}
