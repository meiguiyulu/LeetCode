package Weekly.week295;

/**
 * @Author LiuYunJie
 * @Date 2022/6/10 11:00
 **/

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 到达角落需要移除障碍物的最小数目
 */
public class test4 {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        int inf = 1 << 30;
        for(var i = 0; i < m; i++){
            Arrays.fill(dis[i], inf);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> { return a[0] - b[0]; });
        dis[0][0] = grid[0][0] == 0? 0: 1;
        queue.offer(new int[] {dis[0][0], 0, 0});
        int[][] dir = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        while(!queue.isEmpty()){
            var top = queue.poll();
            int d = top[0], x = top[1], y = top[2];
            // System.out.println(d + " ," + x + " " + y);
            for(var i = 0; i < 4; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n){
                    if(d + grid[nx][ny] < dis[nx][ny]){
                        dis[nx][ny] = d + grid[nx][ny];
                        queue.offer(new int[] { dis[nx][ny], nx, ny} );
                        // System.out.println(dis[nx][ny] + ",, " + nx + " " + ny);
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}
