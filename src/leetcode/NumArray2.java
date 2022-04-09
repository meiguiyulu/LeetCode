package leetcode;

public class NumArray2 {

    private int[] arr;

    public NumArray2(int[] nums) {
        int length = nums.length;
        arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = nums[i];
        }
    }

    public void update(int index, int val) {
        arr[index] = val;
    }

    public int sumRange(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            ans += arr[i];
        }
        return ans;
    }
}
