package Weekly.week291;

/**
 * @Author LiuYunjie
 * @Date 2022/5/21 20:20
 **/

import java.util.Scanner;

/**
 * 移除指定数字得到的最大结果
 * <p>
 * 给你一个表示某个正整数的字符串 number 和一个字符 digit 。
 * <p>
 * 从 number 中 恰好 移除 一个 等于 digit 的字符后，找出并返回按 十进制 表示 最大 的结果字符串。生成的测试用例满足 digit 在 number 中出现至少一次
 */
public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();
        int digit = sc.nextInt();
        int ans = 0;
        int length = number.length();
        for (int i = 0; i < length; i++) {
            if (number.charAt(i) - '0' == digit) {
                String s = number.substring(0, i) + number.substring(i + 1, length);
                ans = Math.max(ans, Integer.valueOf(s));
            }
        }
        System.out.println(ans);
    }
}
