package Weekly.week309;

import java.util.*;

/**
 * @Author LiuYunJie
 * @Date 2022/9/11 20:33
 **/
public class test1 {

    /**
     * 2399. 检查相同字母间的距离
     *
     * @param s
     * @param distance
     * @return
     */
    public static boolean checkDistances(String s, int[] distance) {
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (i - map.get(c) - 1 != distance[c - 'a']) {
                    return false;
                }
            } else {
                map.put(c, i);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abaccb";
        int[] distance = new int[]{1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(checkDistances(s, distance));
    }

}
