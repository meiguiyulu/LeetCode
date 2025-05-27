package Hot100;

public class Jump {

    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。
     * 换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     */
    public int jump(int[] nums) {
        int count = 0;
        int position = nums.length ;
        while (position > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public int jump2(int[] nums) {
        int ans = 0;
        int curRight = 0; // 已建造的桥的右端点
        int nextRight = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < nums.length - 1; i++) {
            // 遍历的过程中，记录下一座桥的最远点
            nextRight = Math.max(nextRight, i + nums[i]);
            if (i == curRight) { // 无路可走，必须建桥
                curRight = nextRight; // 建桥后，最远可以到达 next_right
                ans++;
            }
        }
        return ans;
    }

}
