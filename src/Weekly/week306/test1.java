package Weekly.week306;

/**
 * @Author LiuYunJie
 * @Date 2022/8/20 14:05
 **/
public class test1 {

    /**
     * 2373. 矩阵中的局部最大值
     *
     * @param grid
     * @return
     */
    public int[][] largestLocal(int[][] grid) {
        int length = grid.length;
        int[][] ans = new int[length - 2][length - 2];
        for (int i = 0; i < length - 2; ++i) {
            for (int j = 0; j < length - 2; ++j) {
                for (int k = i; k < i + 3; ++k) {
                    for (int z = j; z < j + 3; ++z) {
                        ans[i][j] = Math.max(ans[i][j], grid[k][z]);
                    }
                }
            }
        }
        return ans;
    }

}
