package itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreatePDF {
    public static void main(String[] args){
        try {
            //Step 1—Create a Document.
            Document document = new Document();
            //Step 2—Get a PdfWriter instance.
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\i343740\\Desktop\\createSamplePDF.pdf"));
            //Step 3—Open the Document.
            document.open();
            //Step 4—Add content.
            document.add(new Paragraph("Hello World"));
            //Step 5—Close the Document.
            document.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (DocumentException e){
            e.printStackTrace();
        }
    }
}
