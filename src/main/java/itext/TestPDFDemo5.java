package itext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

//创建列表
public class TestPDFDemo5 {
    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test5.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("content here"));

        //添加有序列表
        List orderedList = new List(List.ORDERED);
        orderedList.add(new ListItem("Item one"));
        orderedList.add(new ListItem("Item two"));
        orderedList.add(new ListItem("Item three"));
        document.add(orderedList);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
