package Weekly.week320;

/**
 * @Author LiuYunJie
 * @Date 2022/11/26 15:39
 **/
public class test1 {

    /**
     * 2475. 数组中不等三元组的数目
     *
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                for (int z = j + 1; z < nums.length; ++z) {
                    if (nums[i] != nums[j] && nums[j] != nums[z] && nums[z] != nums[i]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }

}
