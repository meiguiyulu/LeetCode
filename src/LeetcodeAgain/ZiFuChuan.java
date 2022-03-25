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

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        if (digits.length() == 0) {
            return ans;
        }
        dfsForLetter(digits, map, ans, builder, 0);
        return ans;
    }

    private void dfsForLetter(String digits, Map<Character, String> map, List<String> ans, StringBuilder builder, int index) {
        if (builder.length() == digits.length()) {
            ans.add(builder.toString());
            return;
        }
        String s1 = map.get(digits.charAt(index));
        for (int i = 0; i < s1.length(); i++) {
            builder.append(s1.charAt(i));
            dfsForLetter(digits, map, ans, builder, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfsForGenerateParenthesis(ans, builder, 0, 0, n);
        return ans;
    }

    private void dfsForGenerateParenthesis(List<String> ans, StringBuilder builder, int left, int right, int n) {
        if (left == n && right == n) {
            ans.add(builder.toString());
            return;
        }
        if (left < n) {
            builder.append("(");
            ++left;
            dfsForGenerateParenthesis(ans, builder, left, right, n);
            --left;
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right < left) {
            builder.append(")");
            ++right;
            dfsForGenerateParenthesis(ans, builder, left, right, n);
            --right;
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int length = needle.length();
        if (length == 0) {
            return 0;
        }
        int ans = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (i + length - 1 < haystack.length()
                        && haystack.substring(i, i + length).equals(needle)) {
                    ans = i;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 38. 外观数列
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String str = countAndSay(n - 1);
        char curr = 'c';
        int num = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != curr) {
                if (num != 0) {
                    builder.append(num);
                    builder.append(curr);
                }
                curr = str.charAt(i);
                num = 1;
            } else {
                num++;
            }
        }
        builder.append(num);
        builder.append(curr);
        return builder.toString();
    }

    /**
     * 43. 字符串相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int length1 = num1.length(), length2 = num2.length();
        int[] arr = new int[length1 + length2];
        for (int i = length1 - 1; i >= 0; i--) {
            int i1 = num1.charAt(i) - '0';
            for (int j = length2 - 1; j >= 0; j--) {
                int i2 = num2.charAt(j) - '0';
                arr[i + j + 1] += i1 * i2;
            }
        }
        for (int i = arr.length - 1; i >= 1; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] = arr[i] % 10;
        }
        int index = arr[0] == 0 ? 1 : 0;
        StringBuilder builder = new StringBuilder();
        while (index < length1 + length2) {
            builder.append(arr[index]);
            ++index;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        ZiFuChuan chuan = new ZiFuChuan();
        for (int i = 1; i <= 5; i++) {
            System.out.println(chuan.countAndSay(i));
        }
    }

}
