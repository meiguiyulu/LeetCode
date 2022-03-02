package LeetcodeAgain;

import java.util.*;

public class ZiFuChuan {

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            } else {
                map.put(c, right);
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * 6. Z 字形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int length = s.length();
        int num = 2 * (numRows - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < length; j++) {
                if (j % num == i || j % num == num - i) {
                    builder.append(s.charAt(j));
                }
            }
        }
        return builder.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row.toString());
        }
        return ans.toString();
    }

    public static void main(String[] args) {
//        new ZiFuChuan().lengthOfLongestSubstring("abba");
        new ZiFuChuan().convert("ABCD", 2);
    }

}
