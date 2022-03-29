package javabase.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者-消费者
 *
 * @author: huangtao3
 * @since: 2022/3/22 10:46
 */
public class ProducerAndConsumer {
    private static int count = 0;
    private static final int SIZE = 1;
    private static final Object lock = new Object();
    private static final int THREAD_NUM = 1;

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static final Semaphore mutex = new Semaphore(1);
    private static final CountDownLatch downLatch = new CountDownLatch(10);

    private

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                synchronized (lock) {
                    while (count >= SIZE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前count：" + count);
                    lock.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                synchronized (lock) {
                    while (count <= 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前count：" + count);
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread[] producer = new Thread[THREAD_NUM];
        Thread[] consumer = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            producer[i] = new Thread(new Producer());
            consumer[i] = new Thread(new Consumer());
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            producer[i].start();
            consumer[i].start();
        }

    }
}