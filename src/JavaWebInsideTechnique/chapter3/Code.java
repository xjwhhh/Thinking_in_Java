package JavaWebInsideTechnique.chapter3;

import java.io.*;

public class Code {

    public static void main(String[] args){
        memoryCode();
    }

    public static void IOCode() {
        String file = "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\src\\JavaWebInsideTechnique\\chapter3\\stream.txt";
        String charset = "UTF-8";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charset);
            writer.write("这是要保存的中文字符");
            writer.close();
        } catch (FileNotFoundException|UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream, charset);
            StringBuffer buffer = new StringBuffer();
            char[] buf = new char[64];
            int count;
            while ((count = reader.read(buf)) != -1) {
                buffer.append(buf, 0, count);
            }
            System.out.println(buffer);
            reader.close();
        } catch (FileNotFoundException|UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void memoryCode(){
        String s="这是一段中文字符串";
        try {
            byte[] b = s.getBytes("UTF-8");
            String n = new String(b, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
