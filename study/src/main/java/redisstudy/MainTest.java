package redisstudy;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
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
        String ping = redisCommands.ping();
        System.out.println("ping:" + ping);
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("redis连接开始：=============");
        RedisURI uri = RedisURI.create("redis://localhost/");
        //RedisURI uri = RedisURI.builder().withHost("localhost").withPort(6379).build();
        //RedisURI uri = new RedisURI("localhost", 6379, Duration.ofSeconds(60));
        client = RedisClient.create(uri);
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
