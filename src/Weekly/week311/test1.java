package Weekly.week311;

/**
 * @Author LiuYunJie
 * @Date 2022/9/23 20:52
 **/
public class test1 {

    /**
     * 2413. 最小偶倍数
     *
     * @param n
     * @return
     */
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }
}
