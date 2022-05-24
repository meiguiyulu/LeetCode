package Weekly.week293;

/**
 * @Author LiuYunJie
 * @Date 2022/5/24 15:06
 **/

import java.util.Arrays;

/**
 * 不含特殊楼层的最大连续楼层数
 */
public class test2 {

    public int maxConsecutive(int bottom, int top, int[] special) {
        int length = special.length;
        Arrays.sort(special);
        int ans = special[0] - bottom;
        for (int i = 1; i < length; i++) {
            ans = Math.max(ans, special[i] - special[i - 1] - 1);
        }
        return Math.max(ans, top - special[length - 1]);
    }

}
