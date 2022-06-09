package Weekly.week295;

/**
 * @Author LiuYunJie
 * @Date 2022/6/9 11:45
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 重排字符形成目标字符串
 * <p>
 * 给你两个下标从 0 开始的字符串 s 和 target 。你可以从 s 取出一些字符并将其重排，得到若干新的字符串。
 * <p>
 * 从 s 中取出字符并重新排列，返回可以形成 target 的 最大 副本数。
 */
public class test1 {
    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        int ans = Integer.MAX_VALUE;
        for (Character character : map2.keySet()) {
            ans = Math.min(ans, map1.getOrDefault(character, 0) / map2.get(character));
        }
        return ans;
    }
}
