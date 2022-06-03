package Weekly.week294;

/**
 * @Author LiuYunJie
 * @Date 2022/6/3 16:46
 **/

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 巫师的总力量和
 */
public class test4 {

    public int totalStrength(int[] strength) {
        final var mod = (int) 1e9 + 7;

        var n = strength.length;
        var left = new int[n];  // left[i] 为左侧严格小于 strength[i] 的最近元素位置（不存在时为 -1）
        var right = new int[n]; // right[i] 为右侧小于等于 strength[i] 的最近元素位置（不存在时为 n）
        Arrays.fill(right, n);
        var st = new ArrayDeque<Integer>();
        for (var i = 0; i < n; i++) {
            while (!st.isEmpty() && strength[st.peek()] >= strength[i]) right[st.pop()] = i;
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        var s = 0L; // 前缀和
        var ss = new int[n + 2]; // 前缀和的前缀和
        for (var i = 1; i <= n; ++i) {
            s += strength[i - 1];
            ss[i + 1] = (int) ((ss[i] + s) % mod); // 注意取模后，下面计算两个 ss 相减，结果可能为负
        }

        var ans = 0L;
        for (var i = 0; i < n; ++i) {
            int l = left[i] + 1, r = right[i] - 1; // [l,r] 左闭右闭
            var tot = ((long) (i - l + 1) * (ss[r + 2] - ss[i + 1]) - (long) (r - i + 1) * (ss[i + 1] - ss[l])) % mod;
            ans = (ans + strength[i] * tot) % mod; // 累加贡献
        }
        return (int) (ans + mod) % mod; // 防止算出负数
    }

}
