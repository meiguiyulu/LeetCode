package Weekly.week291;

/**
 * @Author LiuYunJie
 * @Date 2022/5/21 22:40
 **/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 含最多 K 个可整除元素的子数组
 *
 * 给你一个整数数组 nums 和两个整数 k 和 p ，找出并返回满足要求的不同的子数组数，要求子数组中最多 k 个可被 p 整除的元素。
 */
public class test3 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,3,2,2};
        int k = 2, p = 2;
        long currentTime = System.currentTimeMillis();
        System.out.println(countDistinct(nums, k, p));
        System.out.println(System.currentTimeMillis() - currentTime);
    }


    public static int countDistinct(int[] nums, int k, int p) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i=0;i<length;i++) {
            List<Integer> list = new ArrayList<>();
            int count = 0;
            for (int j=i;j<length;j++) {
                if (nums[j] % p == 0) {
                    ++count;
                }
                if (count > k) {
                    break;
                }
                list.add(nums[j]);
                System.out.println(list);
                int hashCode = list.hashCode();
                if (!set.contains(hashCode)) {
                    set.add(hashCode);
                }
            }
        }
        return set.size();
    }
}
