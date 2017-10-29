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

public class insert_dormitory {
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
            String sqlstr = "insert into dormitory values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sqlstr);
            Date start = new Date();

            HashMap<String, Dormitory> dormitoryHashMap = new HashMap<>();
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
                HSSFCell campusCell = row.getCell(4); // 校区列
                HSSFCell dormitoryCell = row.getCell(5); // 宿舍楼列
                HSSFCell costCell = row.getCell(6); // 住宿标准列

                String name = dormitoryCell.getStringCellValue();
                if (!dormitoryHashMap.containsKey(name)) {
                    Dormitory dormitory = new Dormitory();
                    dormitory.buliding_name = name;
                    dormitory.campus = campusCell.getStringCellValue();
                    dormitory.cost = String.valueOf(costCell.getNumericCellValue());
                    dormitoryHashMap.put(name, dormitory);
                }
            }
            String pathname = "C:\\Users\\xjwhh\\Desktop\\哈哈哈\\数据库\\作业\\作业2\\电话.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            br.readLine();
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    String[] info = line.split(";");
                    if (dormitoryHashMap.containsKey(info[0])) {
                        Dormitory dormitory = dormitoryHashMap.get(info[0]);
                        dormitory.telephone = info[1];
                        dormitoryHashMap.put(info[0], dormitory);
                    }
                }
            }
            for (String key : dormitoryHashMap.keySet()) {
                Dormitory dormitory = dormitoryHashMap.get(key);
                stmt.setString(1, dormitory.buliding_name);
                stmt.setString(2, dormitory.campus);
                stmt.setString(3, dormitory.cost);
                stmt.setString(4, dormitory.telephone);
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
