package leetcode;

/**
 * 303. 区域和检索 - 数组不可变
 */
public class NumArray {

    private int[] arr;

    public NumArray(int[] nums) {
        int length = nums.length;
        arr = new int[length];
        arr[0] = nums[0];
        for (int i = 1; i < length; i++) {
            arr[i] = arr[i-1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return arr[right];
        } else {
            return arr[right] - arr[left-1];
        }

    }

}
