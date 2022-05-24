package Weekly.week292;

/**
 * @Author LiuYunJie
 * @Date 2022/5/22 19:46
 **/

import java.util.Scanner;

/**
 * 字符串中最大的 3 位相同数字
 *
 * 给你一个字符串 num ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
 *
 * 该整数是 num 的一个长度为 3 的 子字符串 。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回 最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串 "" 。
 */
public class test1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.next();
            System.out.println(largestGoodInteger(s));
        }

    }

    public static String largestGoodInteger(String num) {
        int length = num.length();
        String ans = "";
        for (int i=0;i+2<length;i++) {
            if (num.charAt(i) == num.charAt(i+1) && num.charAt(i+1)==num.charAt(i+2)) {
                String s = num.substring(i, i + 3);
                if (ans.compareTo(s) < 0) {
                    ans = s;
                }
            }
        }
        return ans;
    }

}
