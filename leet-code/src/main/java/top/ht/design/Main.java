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
        ListNode newNode = new ListNode(value);
        cacheMap.put(key, newNode);
        addToHead(newNode);
        if (count > capacity) {
            deleteTail();
        }
    }

    private void deleteTail() {
        count--;
        if (count == 0) {
            head = tail = null;
        }
        tail.pre.next = null;
        tail = tail.pre;
    }

    private void addToHead(ListNode newNode) {
        count++;
        if (count == 1) {
            head = newNode;
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
        }
        node.pre.next = node.next;
        node.next = head;
        node.pre = null;
        head = node;
    }

    // 双向链表
    class ListNode {
        ListNode next;
        ListNode pre;
        int value;

        ListNode() {

        }

        ListNode(int value) {
            this.value = value;
        }
    }
}
