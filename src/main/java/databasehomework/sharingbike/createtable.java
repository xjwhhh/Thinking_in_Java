package databasehomework.sharingbike;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class createtable {
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
            createUser();
            createBike();
            createRecord();
//            createRepair();
//            createAddress();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table user (id int primary key,name VARCHAR (255) not null,telephone VARCHAR (255) not null,balance double not null,address VARCHAR (255))";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建用户表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建用户表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createBike() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table bike (id int primary key,canuse int)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建单车表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建单车表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createRecord() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table record (id int PRIMARY key auto_increment,user_id int not null,bike_id int not null, borrow_address VARCHAR (255) not null,borrow_time datetime default NULL,return_address VARCHAR (255) not null,return_time datetime default NULL,cost double)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建借车记录表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建借车记录表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createRecord1() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table recordd (id int PRIMARY key auto_increment,user_id int not null,bike_id int not null, borrow_address VARCHAR (255) not null,borrow_time datetime default NULL,return_address VARCHAR (255) not null,return_time datetime default NULL,cost double)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建借车记录表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建借车记录表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createRepair() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table repair (bike_id int primary key,final_adddress VARCHAR (255) not null, usetime double)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建维修表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建维修表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createAddress() {
        try {
            Statement stmt = con.createStatement();
            Date start = new Date();
            String sqlstr = "create table address (id int primary key auto_increment,name varchar(255) not null)";
            int result = stmt.executeUpdate(sqlstr);
            Date end = new Date();
            if (result == 0) {
                System.out.println("创建地点表成功");
                System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
            } else {
                System.out.println("创建地点表失败");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
