package multithread;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * future使用
 *
 * @author: huangtao3
 * @since: 2021/10/21 16:02
 */
public class STDFuture {
    private final ExecutorService executor = new ThreadPoolExecutor(
            10, 50, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1500));
    @Test
    public void test() {
        List<Integer> sleeps = Arrays.asList(1001,2000,1050);

        System.out.println("test开始===========");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("CompletableFuture并发");
        List<Integer> collect = sleeps.stream().map(item -> CompletableFuture.supplyAsync(() -> {
            Thread thread = Thread.currentThread();
            System.out.println("当前线程：" + thread.getId());
            try {
                Thread.sleep(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getId() + "睡眠了" + item);
            if (item.equals(1000)) {
                throw new RuntimeException("失败");
            }
            return item / 10;
        }, executor)).map(CompletableFuture::join).collect(Collectors.toList());
        Thread thread = Thread.currentThread();
        System.out.println("当前线程：" + thread.getId());
//        List<Integer> collect = sleeps.stream().filter(i -> i > 1).collect(Collectors.toList());
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("test结束==========");
//        List<Integer> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
//        System.out.println(collect1);
    }
}
