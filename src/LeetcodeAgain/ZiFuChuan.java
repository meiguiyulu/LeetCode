package LeetcodeAgain;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

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

    /**
     * 12. 整数转罗马数字
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            int index = 0;
            while (index < values.length && values[index] > num) {
                ++index;
            }
            num -= values[index];
            builder.append(symbols[index]);
        }
        return builder.toString();
    }

    /**
     * 13. 罗马数字转整数
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            map.put(symbols[i], values[i]);
        }
        int ans = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (i + 1 < length) {
                StringBuilder builder = new StringBuilder();
                builder.append(s.charAt(i));
                builder.append(s.charAt(i + 1));
                if (map.containsKey(builder.toString())) {
                    ans += map.get(builder.toString());
                    i++;
                } else {
                    ans += map.get(String.valueOf(s.charAt(i)));
                }
            } else {
                ans += map.get(String.valueOf(s.charAt(i)));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ZiFuChuan().romanToInt("MCMXCIV"));
    }

}
