package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LYJ
 * @create 2021-09-12 16:41
 *
 * 146. LRU 缓存机制
 */
public class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode next;
        DLinkedNode prev;
        DLinkedNode () {}
        DLinkedNode (int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    int size;
    int capacity;
    DLinkedNode head;
    DLinkedNode tail;
    Map<Integer, DLinkedNode> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode(); // 伪头部
        this.tail = new DLinkedNode(); // 伪尾部
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DLinkedNode node) {
        deleteNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void deleteNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // key之前不存在
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            ++size;
            if (size > capacity) {
                DLinkedNode deleteTail = deleteTail();
                cache.remove(deleteTail.key);
                --size;
            }
            addToHead(newNode);
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private DLinkedNode deleteTail() {
        DLinkedNode prev = tail.prev;
        deleteNode(prev);
        return prev;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
    }
}
