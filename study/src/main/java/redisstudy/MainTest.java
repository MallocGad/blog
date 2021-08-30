package redisstudy;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * lettuce 使用
 *
 * @author: huangtao3
 * @since: 2021/6/19 15:09
 */
public class MainTest {

    private static RedisClient client;
    private static StatefulRedisConnection<String, String> connection;

    @Test
    public void test1() {
        // 获取同步的command
        RedisCommands<String, String> redisCommands = connection.sync();

        SetArgs args = SetArgs.Builder.nx().ex(500);
        String result = redisCommands.set("name", "throwable", args);
        System.out.println("set结果：:" + result);
        String name = redisCommands.get("name");
        System.out.println(name);
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("redis连接开始：=============");
        RedisURI uri = RedisURI.create("redis://localhost/");
        //RedisURI uri = RedisURI.builder().withHost("localhost").withPort(6379).build();
        //RedisURI uri = new RedisURI("localhost", 6379, Duration.ofSeconds(60));
        // 创建客户端
        client = RedisClient.create(uri);
        // 创建线程安全的链接
        connection = client.connect();
        System.out.println("redis连接成功：=============");
    }

    @AfterClass
    public static void afterClass() {
        connection.close();
        client.shutdown();
        System.out.println("redis关闭连接：==============");
    }
}
