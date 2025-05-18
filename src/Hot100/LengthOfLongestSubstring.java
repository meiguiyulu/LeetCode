package Hot100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;
        int end = 0;
        for(;end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));  // 这里+1是表示下一个子串的开始位置
            }
            map.put(s.charAt(end), end + 1);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int ans = 0;
        int start = 0;
        for (; start < s.length(); start++) {
            Set<Character> set = new HashSet<>();
            for (int end = start;end<s.length();++end) {
                if (set.contains(s.charAt(end))) {
                    break;
                } else {
                    set.add(s.charAt(end));
                    ans = Math.max(ans, end - start + 1);
                }
            }
        }
        return ans;
    }

}
