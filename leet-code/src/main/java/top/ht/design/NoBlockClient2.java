package top.ht.design;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * nio test client
 *
 * @author: huangtao3
 * @since: 2022/3/14 11:43
 */
public class NoBlockClient2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\huangtao\\Pictures\\Saved Pictures\\哈哈哈.png"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        int count = 0;
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    System.out.println("客户端第" + count++ + "次得到响应。");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer responseBuf = ByteBuffer.allocate(1024);
                    int read = channel.read(responseBuf);
                    if (read > 0) {
                        responseBuf.flip();
                        System.out.println(new String(responseBuf.array(), 0, read));
                    }
                }
                iterator.remove();
            }
        }
    }
}

