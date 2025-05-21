package Hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     * 思路：排序+遍历
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; ) {
            List<Integer> list1 = new ArrayList<>();
            int start = intervals[i][0];
            int end = intervals[i][1];
            int j = i + 1;
            for (; j < intervals.length; j++) {
                if (intervals[j][0] <= end) {
                    end = Math.max(end, intervals[j][1]);
                } else {
                    break;
                }
            }
            list1.add(start);
            list1.add(end);
            list.add(list1);
            i = j;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = new int[]{list.get(i).get(0), list.get(i).get(1)};
        }
        return res;
    }

}
