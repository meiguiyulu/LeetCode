package Weekly.week324;

import java.util.HashMap;

/**
 * @Author LiuYunJie
 * @Date 2022/12/24 16:08
 **/
public class test1 {
    public int similarPairs(String[] words) {
        // 哈希 + 位运算
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(String word : words) {
            int index = 0;
            for(char c : word.toCharArray()) {
                index |= 1 << (c - 'a');
            }
            ans += map.getOrDefault(index, 0);
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        return ans;
    }
}
