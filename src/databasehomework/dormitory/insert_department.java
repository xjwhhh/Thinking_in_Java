package databasehomework.dormitory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class insert_department {
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
            String sqlstr = "insert into department values(?)";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();

            Set<String> department = new HashSet<>();
            //读取excel文件
            FileInputStream excelFileInputStream = new FileInputStream("C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\修改后的数据\\分配方案.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(excelFileInputStream);
            excelFileInputStream.close();
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                //获取每一行列数据
                HSSFCell departmentNameCell = row.getCell(0); // 院系列
                String name = departmentNameCell.getStringCellValue();
                department.add(name);
            }
            for (String name : department) {
                stmt.setString(1, name);
                stmt.executeUpdate();
            }
            Date end = new Date();
            System.out.println("运行时间:" + String.valueOf(end.getTime() - start.getTime()) + "ms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
