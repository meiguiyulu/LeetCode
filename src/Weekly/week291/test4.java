package Weekly.week291;

/**
 * @Author LiuYunJie
 * @Date 2022/5/22 8:39
 **/

import java.util.Arrays;

/**
 * 字符串的总引力
 * 字符串的 引力 定义为：字符串中 不同 字符的数量。
 * <p>
 * 例如，"abbca" 的引力为 3 ，因为其中有 3 个不同字符 'a'、'b' 和 'c' 。
 * 给你一个字符串 s ，返回 其所有子字符串的总引力 。
 */
public class test4 {
    public static long appealSum(String s) {
        int[] last = new int[26];
        long ans = 0;
        long sumG = 0;
        Arrays.fill(last, -1);
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sumG += i - last[index];
            ans += sumG;
            last[index] = i;
        }
        return ans;
    }
}
