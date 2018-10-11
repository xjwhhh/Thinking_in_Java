package itext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

//添加图片
public class TestPDFDemo3 {
    public static void main(String[] args) throws DocumentException, IOException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test3.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("content here"));

        //图片1
        Image image1 = Image.getInstance("image.JPG");
        //设置图片位置的x轴和y周
        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        //图片2
        Image image2 = Image.getInstance(new URL("http://static.cnblogs.com/images/adminlogo.gif"));
        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
