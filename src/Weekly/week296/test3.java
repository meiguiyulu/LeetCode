package Weekly.week296;

/**
 * @Author LiuYunJie
 * @Date 2022/6/10 19:42
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 替换数组中的元素
 * 给你一个下标从 0 开始的数组 nums ，它包含 n 个 互不相同 的正整数。请你对这个数组执行 m 个操作，在第 i 个操作中，你需要将数字 operations[i][0] 替换成 operations[i][1] 。
 * <p>
 * 题目保证在第 i 个操作中：
 * <p>
 * operations[i][0] 在 nums 中存在。
 * operations[i][1] 在 nums 中不存在。
 * 请你返回执行完所有操作后的数组。
 */
public class test3 {

    public static int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int[] operation : operations) {
            Integer index = map.get(operation[0]);
            nums[index] = operation[1];
            map.put(nums[index], index);
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        int[][] operations = new int[][]{{1, 3}, {2, 1}, {3, 2}};
        System.out.println(arrayChange(nums, operations));
    }

}
