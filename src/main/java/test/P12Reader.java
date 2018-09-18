package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class P12Reader {
    public static void main(String[] args){
        try {

            InputStream is = new FileInputStream("C:\\Users\\i343740\\Desktop\\Incident\\GBQ\\saphanadspaloalto-be61504e70e6.p12");

            byte[] b = new byte[1024];
            int i = 0;
            int index = 0;
            while((i=is.read())!=-1){
                b[index]=(byte) i;
                index++;
            }
            System.out.println(new String(b));
            is.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
