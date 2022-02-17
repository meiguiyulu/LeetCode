package leetcode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgrammingAbility {


    /**
     * 1523. 在区间范围内统计奇数数目
     * 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     *
     * @param low
     * @param high
     * @return
     */
    public int countOdds(int low, int high) {
        /*
        * 暴力超时了
        *         int ans = 0;
        for (int i = low; i <= high; ++i) {
            if (i % 2 == 1) {
                ++ans;
            }
        }
        return ans;
        * */

        /*前缀和*/
        return (high + 1) / 2 - low / 2;
    }


    /*1491. 去掉最低工资和最高工资后的工资平均值*/
    public static double average(int[] salary) {
        int length = salary.length;
        double total = salary[0];
        int minIndex = 0, maxIndex = 0;
        int minValue = salary[0], maxValue = salary[0];
        for (int i = 1; i < length; ++i) {
            total += salary[i];
            if (salary[i] < minValue) {
                minIndex = i;
                minValue = salary[i];
            }
            if (salary[i] > maxValue) {
                maxIndex = i;
                maxValue = salary[i];
            }
        }
        return (total - salary[minIndex] - salary[maxIndex]) / (length - 2);
    }


    /*191. 位1的个数*/
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ++ans;
        }
        return ans;
    }

    /*1281. 整数的各位积和之差*/
    public int subtractProductAndSum(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n = n / 10;
        }
        int plus = 0;
        int multiply = 1;
        for (Integer integer: list) {
            plus += integer;
            multiply *= integer;
        }
        return multiply - plus;
    }

    public static void main(String[] args) {
        System.out.println(average(new int[]{4000, 3000, 1000, 2000}));
    }


}
