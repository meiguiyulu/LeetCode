package leetcode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        for (Integer integer : list) {
            plus += integer;
            multiply *= integer;
        }
        return multiply - plus;
    }


    /**
     * 976. 三角形的最大周长
     * <p>
     * 给定由一些正数（代表长度）组成的数组 nums ，返回 由其中三个长度组成的、面积不为零的三角形的最大周长 。
     * 如果不能形成任何面积不为零的三角形，返回 0。
     *
     * @param nums
     * @return
     */
    public static int largestPerimeter(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; --i) {
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                ans = nums[i] + nums[i - 1] + nums[i - 2];
                break;
            }
        }
        return ans;
    }

    /**
     * 1779. 找到最近的有相同 X 或 Y 坐标的点
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int ans = Integer.MAX_VALUE;
        int temp = Integer.MAX_VALUE;
        boolean flag = false;
        for (int[] point : points) {
            int currX = point[0], currY = point[1];

            if (x == currX && y == currY) {
                return 0;
            } else if (x == currX) {
                if (Math.abs(y - currY) < temp) {
                    temp = Math.abs(y - currY);
                    ans = currY;
                } else if (Math.abs(y - currY) == temp) {
                    ans = Math.min(ans, currY);
                }
                flag = true;
            } else if (y == currY) {
                if (Math.abs(x - currX) < temp) {
                    temp = Math.abs(x - currX);
                    ans = currX;
                } else if (Math.abs(x - currX) == temp) {
                    ans = Math.min(ans, currX);
                }
                flag = true;
            }
        }
        if (flag) {
            return ans;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(average(new int[]{4000, 3000, 1000, 2000}));
        System.out.println(largestPerimeter(new int[]{3, 2, 3, 4}));
        System.out.println(nearestValidPoint(3, 4, new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}));
    }


}
