package Weekly.week294;

/**
 * @Author LiuYunJie
 * @Date 2022/6/3 16:14
 **/

import java.util.Arrays;

/**
 * 表示一个折线图的最少线段
 */
public class test3 {

    int minimumLines(int[][] stockPrices) {
        int num = stockPrices.length;
        if (num <= 2)
            return num - 1;
        int ans = 1;
        Arrays.sort(stockPrices, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 2; i < num; i++) {
            int deltaY2 = stockPrices[i][1] - stockPrices[i - 1][1];
            int deltaY1 = stockPrices[i - 1][1] - stockPrices[i - 2][1];
            int deltaX2 = stockPrices[i][0] - stockPrices[i - 1][0];
            int deltaX1 = stockPrices[i - 1][0] - stockPrices[i - 2][0];
            if (deltaY2 * deltaX1 != deltaY1 * deltaX2)
                ++ans;
        }
        return ans;
    }

}
