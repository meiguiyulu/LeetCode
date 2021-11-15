package leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 677. 键值映射
 *
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 */
class MapSum {

    Map<String, Integer> map;

    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int sum = 0;
        int len = prefix.length();
        for (String element: map.keySet()){
            if (element.substring(0, Math.min(len, element.length())).equals(prefix)) {
                sum += map.get(element);
            }
        }
        return sum;
    }
}