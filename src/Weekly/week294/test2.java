package Weekly.week294;

/**
 * @Author LiuYunJie
 * @Date 2022/6/2 20:25
 **/

import java.util.Arrays;

/**
 * 装满石头的背包的最大数量
 * 现有编号从 0 到 n - 1 的 n 个背包。给你两个下标从 0 开始的整数数组 capacity 和 rocks 。
 * 第 i 个背包最大可以装 capacity[i] 块石头，当前已经装了 rocks[i] 块石头。
 * 另给你一个整数 additionalRocks ，表示你可以放置的额外石头数量，石头可以往 任意 背包中放置。
 * <p>
 * 请你将额外的石头放入一些背包中，并返回放置后装满石头的背包的 最大 数量。
 */
public class test2 {

    public static void main(String[] args) {
        int[] capacity = new int[]{2, 3, 4, 5};
        int[] rocks = new int[]{1, 2, 4, 4,};
        int additionalRocks = 2;
        System.out.println(maximumBags(capacity, rocks, additionalRocks));
    }

    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int length = capacity.length;
        int[] rest = new int[length];
        for (int i = 0; i < length; i++) {
            rest[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(rest);
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (additionalRocks >= rest[i]) {
                additionalRocks -= rest[i];
                rest[i] = 0;
                ++ans;
            } else {
                break;
            }
        }
        return ans;
    }
}
