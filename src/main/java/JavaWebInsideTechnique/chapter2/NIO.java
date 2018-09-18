package JavaWebInsideTechnique.chapter2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIO {
    public void selector() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //调用Selector的静态工厂创建一个选择器
        Selector selector = Selector.open();
        //创建一个服务器端的channel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //将通信信道设置为非阻塞
        ssc.configureBlocking(false);
        //绑定到一个socket对象
        ssc.socket().bind(new InetSocketAddress(8080));
        //将这个通信信道注册到选择器上
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            Set selectedKeys = selector.selectedKeys();
            Iterator iterator = selectedKeys.iterator();
            //调用selector的selectedKeys方法来检查已经注册在这个选择器上的所有通信信道是否有需要的事件发生
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                //监听事件
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    //获取通信信道对象
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                }
                //处理请求的时间
                else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        //将字节写入通信信道
                        buffer.flip();
                    }
                    iterator.remove();
                }
            }
        }
    }

    //FileChannel.map将文件按照一定大小块映射为内存区域，当程序访问这个内存区域时将直接操作这个文件数据
    public static void map(String[] args) {
        int BUFFER_SIZE = 1024;
        String fileName = "p12.db";
        long fileLength = new File(fileName).length();
        int bufferCount = 1 + (int) (fileLength / BUFFER_SIZE);
        MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;
        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file = new RandomAccessFile(fileName, "r");
                buffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY, i * BUFFER_SIZE,
                        (int) Math.min(remaining, BUFFER_SIZE));
            } catch (Exception e) {
                e.printStackTrace();
            }
            remaining -= BUFFER_SIZE;
        }
    }
}
