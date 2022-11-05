package Weekly.week317;

/**
 * @Author LiuYunJie
 * @Date 2022/11/5 11:01
 **/
public class test1 {

    /**
     * 2455. 可被三整除的偶数的平均值
     *
     * @param nums
     * @return
     */
    public int averageValue(int[] nums) {
        int totalSum = 0;
        int num = 0;
        for (int i : nums) {
            if (i % 6 == 0) {
                totalSum += i;
                ++num;
            }
        }
        if (num == 0) {
            return 0;
        } else {
            return totalSum / num;
        }
    }

}
