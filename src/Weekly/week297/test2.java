package Weekly.week297;

/**
 * @Author LiuYunJie
 * @Date 2022/6/15 17:53
 **/

import java.util.Arrays;

/**
 * 网格中的最小路径代价
 */
public class test2 {

    public static int minPathCost(int[][] grid, int[][] moveCost) {
        int rows = grid.length, columns = grid[0].length;
        int ans = 0, next = 0;
        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                int currCost = Integer.MAX_VALUE;
                for (int j = 0; j < columns; j++) {
                    int value = grid[i][j];
                    int[] costs = moveCost[value];
                    int cost = costs[0];
                    int index = 0;
                    for (int k = 1; k < costs.length; k++) {
                        if (value + cost < currCost) {
                            currCost = value + cost;
                            index = k;
                        }
                    }
                    next = index;
                }
                ans += currCost;
            } else {
                int value = grid[i][next];
                int[] costs = moveCost[value];
                int cost = costs[0];
                next = 0;
                for (int j = 1; j < costs.length; j++) {
                    if (costs[j] < cost) {
                        cost = costs[j];
                        next = j;
                    }
                }
                ans += value + cost;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{5,3},{4,0},{2,1}},
                moveCost = new int[][]{{9,8},{1,5},{10,12}};
        System.out.println(minPathCost(grid, moveCost));
    }

}
