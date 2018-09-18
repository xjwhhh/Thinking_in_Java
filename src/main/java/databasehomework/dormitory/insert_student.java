package databasehomework.dormitory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

public class insert_student {

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
            String sqlstr = "insert into student values(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();

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
                HSSFCell studentIdCell = row.getCell(1); // 学号列
                HSSFCell studentNameCell = row.getCell(2); // 姓名列
                HSSFCell genderCell = row.getCell(3); // 性别列
//                HSSFCell campusCell = row.getCell(4); // 校区列
                HSSFCell dormitoryCell = row.getCell(5); // 宿舍楼列
//                HSSFCell costCell = row.getCell(6); // 住宿标准列

                stmt.setString(1, studentIdCell.getStringCellValue());
                stmt.setString(2, studentNameCell.getStringCellValue());
                stmt.setString(3, genderCell.getStringCellValue());
                stmt.setString(4, dormitoryCell.getStringCellValue());
                stmt.setString(5, departmentNameCell.getStringCellValue());
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
