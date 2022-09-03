package Weekly.week308;

import javax.swing.*;
import java.util.Arrays;

/**
 * @Author LiuYunJie
 * @Date 2022/9/3 14:05
 **/
public class test1 {

    /**
     * 2389. 和有限的最长子序列
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueries(int[] nums, int[] queries) {
        int length = queries.length;
        int[] answer = new int[length];
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < length; i++) {
            int index = 0;
            for (; index < nums.length; index++) {
                if (nums[index] > queries[i]) {
                    break;
                }
            }
            answer[i] = index;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 2, 1};
        int[] queries = new int[]{3, 10, 21};
        System.out.println(Arrays.stream(answerQueries(nums, queries)));
    }

}
