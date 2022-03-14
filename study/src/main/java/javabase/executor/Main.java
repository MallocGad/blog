package javabase.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2022/3/9 13:57
 */
@Slf4j
public class Main {
    @Test
    public void test1() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        AtomicInteger count = new AtomicInteger(0);
        executor.scheduleWithFixedDelay(() -> log.info("time:"+count.incrementAndGet()), 0,40, TimeUnit.MILLISECONDS);
        Thread.sleep(10000);
        System.out.println(count.get());
    }
}
