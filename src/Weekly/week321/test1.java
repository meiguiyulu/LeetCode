package Weekly.week321;

/**
 * @Author LiuYunJie
 * @Date 2022/12/4 3:50
 **/
public class test1 {

    /**
     * 2485. 找出中枢整数
     *
     * @param n
     * @return
     */
    public int pivotInteger(int n) {
        int ans = (int) Math.sqrt(n * (n + 1) / 2);
        if (ans * ans * 2 == n * (n + 1)) {
            return ans;
        }
        return -1;
    }

}
