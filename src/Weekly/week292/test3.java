package Weekly.week292;

/**
 * @Author LiuYunJie
 * @Date 2022/5/23 12:43
 **/

import java.util.Scanner;

/**
 * 统计打字方案数
 */
public class test3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        countTexts(s);
    }

    public static int countTexts(String pressedKeys) {
        int mod = (int) 1e9 + 7;
        int length = pressedKeys.length();
        char[] chars = pressedKeys.toCharArray();
        long[] dp = new long[length + 1];
        dp[0] = 1; // 没有字符 也算1个方案
        dp[1] = 1; // 只有1个字符 方案数是1

        for (int i = 2; i <= length; i++) {
            // i个字符 在chars中下标是 i-1
            dp[i] = dp[i - 1];
            if (chars[i - 1] == chars[i - 2]) {
                dp[i] += dp[i - 2];
                if (i >= 3 && chars[i - 1] == chars[i - 3]) {
                    dp[i] += dp[i - 3];
                    if (i >= 4 && (chars[i - 1] == '7' || chars[i - 1] == '9') && chars[i - 1] == chars[i - 4]) {
                        dp[i] += dp[i - 4];
                    }
                }
            }
            dp[i] %= mod;
        }

        for (long l : dp) {
            System.out.println(l);
        }

        return (int) dp[length];
    }

}
