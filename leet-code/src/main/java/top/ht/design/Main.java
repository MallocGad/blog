package top.ht.design;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2022/1/5 13:51
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * todo 改代码待测试
 */
class LRUCache {

    // 线程不安全的，可以考虑使用ConcurrentHashMap
    // get/set 都是平均O(1)
    Map<Integer, ListNode> cacheMap;
    ListNode head;
    ListNode tail;
    int capacity = 0;
    int count = 0;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        ListNode node = cacheMap.get(key);
        if (null != node) {
            moveToHead(node);
            return node.value;
        }
        return -1;
    }


    public void put(int key, int value) {
        ListNode node = cacheMap.get(key);
        if (null != node) {
            node.value = value;
            moveToHead(node);
            return;
        }
        ListNode newNode = new ListNode(key, value);
        cacheMap.put(key, newNode);
        addToHead(newNode);
        if (count > capacity) {
            deleteTail();
        }
    }

    private void deleteTail() {
        count--;
        if (count == 0) {
            cacheMap.remove(tail.key);
            head = tail = null;
        } else {
            cacheMap.remove(tail.key);
            tail.pre.next = null;
            tail = tail.pre;
        }
    }

    private void addToHead(ListNode newNode) {
        count++;
        if (count == 1) {
            tail = head = newNode;
            return;
        }
        newNode.next = head;
        head.pre = newNode;
        head = newNode;
    }

    private void moveToHead(ListNode node) {
        if (node == head) {
            return;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
        if (node.pre == head) {
            node.pre.pre = node;
        }
        node.pre.next = node.next;
        node.next = head;
        node.pre = null;
        head = node;
    }

    // 双向链表
    static class ListNode {
        ListNode next;
        ListNode pre;
        int value;
        int key;

        ListNode() {

        }

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}
