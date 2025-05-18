package Hot100;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，
     * 返回这些子串的起始索引。不考虑答案输出的顺序。
     输入: s = "cbaebabacd", p = "abc"
     输出: [0,6]
     解释:
     起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     * <p>
     输入: s = "abab", p = "ab"
     输出: [0,1,2]
     解释:
     起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     */

    public List<Integer> findAnagramsOpt(String s, String p) {
        int[] counts = new int[26];
        for (char c : p.toCharArray()) {
            counts[c - 'a']++;
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int i = s.charAt(right) - 'a';
            counts[i]--;
            while (counts[i] < 0) {
                counts[s.charAt(left) - 'a']++;
                left++;
            }
            if (right - left + 1 == p.length()) {
                ans.add(left);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int length = p.length();
        String countNum = countNums(p);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - length; i++) {
            String s1 = countNums(s.substring(i, i + length));
            if (countNum.equals(s1)) {
                result.add(i);
            }
        }
        return result;
    }

    public String countNums(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <26; i++) {
            if (counts[i] > 0) {
                builder.append((char)(i + 'a'));
                builder.append(counts[i]);
            }
        }
        return builder.toString();
    }

}
