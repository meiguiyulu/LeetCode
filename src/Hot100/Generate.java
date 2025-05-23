package Hot100;

import java.util.ArrayList;
import java.util.List;

public class Generate {

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        int[][] matrix = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (matrix[i][j] != 0) {
                    list.add(matrix[i][j]);
                }
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = generate(3);
        for (List<Integer> list : res) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }

}
