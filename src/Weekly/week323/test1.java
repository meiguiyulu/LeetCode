package Weekly.week323;

import java.util.Arrays;

/**
 * @Author LiuYunJie
 * @Date 2022/12/17 15:37
 **/
public class test1 {

    /**
     * 2500. 删除每行中的最大值
     *
     * @param grid
     * @return
     */
    public int deleteGreatestValue(int[][] grid) {
        int Row = grid.length;
        int Col = grid[0].length;

        for (int r = 0; r < Row; r++) {
            Arrays.sort(grid[r]);
        }

        int res = 0;
        for (int c = 0; c < Col; c++) {
            int cur = grid[0][c];
            for (int r = 1; r < Row; r++) {
                cur = Math.max(cur, grid[r][c]);
            }
            res += cur;
        }

        return res;
    }

}
