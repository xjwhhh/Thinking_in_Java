package VTChallange;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Test {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();

    static String getImageBinary() {
        File f = new File("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\src\\VTChallange\\test.png");
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readFileByBytes(String fileName)  {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(
                    fileName));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                System.out.write(bufferOut, 0, bytes);
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFileByBytes("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Thinking_in_Java\\src\\VTChallange\\test.png");
    }
}
