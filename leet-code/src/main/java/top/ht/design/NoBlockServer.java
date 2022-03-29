package top.ht.design;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * nio test server
 *
 * @author: huangtao3
 * @since: 2022/3/14 10:44
 */
public class NoBlockServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        ServerSocketChannel socketChannel2 = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(6666));
        socketChannel2.configureBlocking(false);
        socketChannel2.bind(new InetSocketAddress(7777));
//        socketChannel.bind(new InetSocketAddress(7777));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        socketChannel2.register(selector, SelectionKey.OP_ACCEPT);
        int count = 0;
        while (true) {
            int select = selector.select();
            System.out.println("准备好了" + select);
            if (!(select > 0)) break;
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = ssc.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("一个新的客户端加入了链接！");
                    System.out.println(client.getLocalAddress());
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    System.out.println("第" + count++ + "次获取到客户端传入图片。");
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    FileChannel outChannel = FileChannel.open(Paths.get(count + "2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    while (client.read(buf) > 0) {
                        System.out.println("大小：" + buf.position());
                        buf.flip();
                        outChannel.write(buf);
                        buf.clear();
                    }
                }
                iterator.remove();
            }
        }
    }
}
