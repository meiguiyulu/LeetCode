package Weekly.week310;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LiuYunJie
 * @Date 2022/9/12 17:03
 **/
public class test1 {

    /**
     * 6176. 出现最频繁的偶数元素
     *
     * @param nums
     * @return
     */
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;
        int ans = -1;
        for (int num : nums) {
            if (num % 2 == 0) {
                Integer orDefault = map.getOrDefault(num, 0);
                map.put(num, orDefault + 1);
                if (orDefault + 1 > max) {
                    max = orDefault + 1;
                    ans = num;
                }
                if (orDefault + 1 == max && num < ans) {
                    ans = num;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8154,9139,8194,3346,5450,9190,133,8239,4606,8671,8412,6290};

    }

}
