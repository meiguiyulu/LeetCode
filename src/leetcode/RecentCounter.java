package leetcode;

/**
 * @Author LiuYunjie
 * @Date 2022/5/6 8:50
 **/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 933. 最近的请求次数
 */
public class RecentCounter {

    Map<Integer, Integer> map;
    int last; // 上一次的时间t

    public RecentCounter() {
        map = new LinkedHashMap<>();
        last = 0;
        map.put(0, 0);
    }

    public int ping(int t) {
        int curr = map.get(last) + 1;
        map.put(t, curr);
        last = t;
        int index = 0;
        for (Integer integer : map.keySet()) {
            if (integer >= t - 3000) {
                break;
            }
            index = integer;
        }
        return curr - map.get(index);
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        System.out.println(counter.ping(2196));
        System.out.println(counter.ping(3938));
        System.out.println(counter.ping(4723));
        System.out.println(counter.ping(4775));
        System.out.println(counter.ping(5952));
    }
}
/**
 * class RecentCounter {
 *     Queue<Integer> queue;
 *
 *     public RecentCounter() {
 *         queue = new ArrayDeque<Integer>();
 *     }
 *
 *     public int ping(int t) {
 *         queue.offer(t);
 *         while (queue.peek() < t - 3000) {
 *             queue.poll();
 *         }
 *         return queue.size();
 *     }
 * }
 *
 */
