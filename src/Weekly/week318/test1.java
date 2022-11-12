package Weekly.week318;

/**
 * @Author LiuYunJie
 * @Date 2022/11/12 15:35
 **/
public class test1 {

    /**
     * 2460. 对数组执行操作
     *
     * @param nums
     * @return
     */
    public int[] applyOperations(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] += nums[i];
                nums[i + 1] = 0;
            }
        }
        int [] res = new int[length];
        int ri = 0;
        for (int x: nums){
            if (x != 0){
                res[ri ++] = x;
            }
        }
        return res;
    }

}
