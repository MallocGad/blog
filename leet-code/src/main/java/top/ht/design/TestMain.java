package top.ht.design;

import org.junit.Test;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2022/1/5 17:57
 */
public class TestMain {
    LRUCache cache;

    @Test
    public void test() {
        cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(1,0);
        cache.put(3,3);
        cache.put(4,4);
    }
}
