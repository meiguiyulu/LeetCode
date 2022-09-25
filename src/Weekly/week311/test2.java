package Weekly.week311;

/**
 * @Author LiuYunJie
 * @Date 2022/9/25 10:49
 **/
public class test2 {

    /**
     * 2414. 最长的字母序连续子字符串的长度
     *
     * @param s
     * @return
     */
    public int longestContinuousSubstring(String s) {
        int ans = 1;
        int left = 0;
        for (int right = 1; right < s.length(); ++right) {
            if (s.charAt(right) - s.charAt(right - 1) != 1) {
                left = right;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}
