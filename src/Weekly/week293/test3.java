package Weekly.week293;

/**
 * @Author LiuYunJie
 * @Date 2022/5/24 15:16
 **/

import java.util.Scanner;

/**
 * 按位与结果大于零的最长组合
 */
public class test3 {
    public static int largestCombination1(int[] candidates) {
        int ans = 0;
        int length = candidates.length;
        for (int i = 0; i < length; i++) {
            int curr = candidates[i];
            for (int j = i + 1; j < length; j++) {
                curr &= candidates[j];
                if (curr > 0) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public static int largestCombination2(int[] candidates) {
        int length = candidates.length;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int curr = 0;
            for (int j = 0; j < length; j++) {
                if ((candidates[j] & (1 << i)) > 0) {
                    ++curr;
                }
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] candidates = new int[length];
        for (int i = 0; i < length; i++) {
            candidates[i] = sc.nextInt();
        }
        System.out.println(largestCombination1(candidates));
    }
}
