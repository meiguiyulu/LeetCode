package Weekly.week292;

/**
 * @Author LiuYunJie
 * @Date 2022/5/23 12:58
 **/

/**
 * 检查是否有合法括号字符串路径
 */
public class test4 {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n) % 2 == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;
        boolean[][][] visited = new boolean[m][n][(m + n + 1) / 2];
        return dfs(0, 0, 0, visited, grid);
    }

    private boolean dfs(int x, int y, int num, boolean[][][] visited, char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (num > m + n - x - y - 1)
            return false;
        if (x == m - 1 && y == n - 1) // 最后一个 一定是 ')'
            return num == 1;
        if (visited[x][y][num]) // 遍历过
            return false;
        visited[x][y][num] = true;
        num += grid[x][y] == '(' ? 1 : -1;
        return num >= 0 && ((x + 1 < m && dfs(x + 1, y, num, visited, grid)) ||
                (y + 1 < n && dfs(x, y + 1, num, visited, grid)));
    }

}
