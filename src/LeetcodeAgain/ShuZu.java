package LeetcodeAgain;

import java.util.*;


/**
 * LeetCode中数组类型的题目
 */
public class ShuZu {
    /**
     * 1. 两数之和
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现
     * 你可以按任意顺序返回答案。
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1和nums2。请你找出并返回这两个正序数组的中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*合并两个有序数组*/
        int m = nums1.length, n = nums2.length;
        int[] num = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                num[k++] = nums1[i++];
            } else {
                num[k++] = nums2[j++];
            }
        }
        while (i < m) {
            num[k++] = nums1[i++];
        }
        while (j < n) {
            num[k++] = nums2[j++];
        }
        if ((m + n) % 2 == 0) {
            return (num[(m + n) / 2] + num[(m + n) / 2 - 1]) * 1.0 / 2;
        } else {
            return num[(m + n - 1) / 2];
        }
    }

    /**
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = Integer.MIN_VALUE;
        while (left < right) {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));
            if (height[left] <= height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return ans;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int first = 0; first < length; ++first) {
            while (first > 0 && first < length - 2 && nums[first] == nums[first - 1]) {
                ++first;
            }
            int third = length - 1;
            int target = -nums[first];
            for (int second = first + 1; second < third; ++second) {
                while (second > first + 1 && second < length - 1 && nums[second] == nums[second - 1]) {
                    ++second;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    /**
     * 最接近的三数之和
     * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
     * <p>
     * 返回这三个数的和。
     * <p>
     * 假定每组输入只存在恰好一个解。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = 10000000;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == target) {
                    best = sum;
                    break;
                }

                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum > target) {
                    int k0 = k - 1;
                    while (k0 > j && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                }
                if (sum < target) {
                    int j0 = j + 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        for (int first = 0; first < length; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < length; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                int four = length - 1;
                int temp = target - nums[first] - nums[second];
                for (int third = second + 1; third < length; ++third) {
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }

                    while (third < four && nums[third] + nums[four] > temp) {
                        --four;
                    }
                    if (third == four) {
                        break;
                    }
                    if (nums[first] + nums[second] + nums[third] + nums[four] == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        list.add(nums[four]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            nums[left++] = nums[right++];
            while (right < nums.length && nums[right] == nums[right - 1]) {
                ++right;
            }
        }
        return left + 1;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int left = 0;
        for (int right = 0; right < length; ++right) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                ++left;
            }
        }
        return left;
    }

    public int removeElement2(int[] nums, int val) {
        int length = nums.length;
        int left = 0, right = length - 1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                --right;
            } else {
                ++left;
            }
        }
        return left;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                --j;
            }
            Swap(nums, i, j);
        }
        ReverseArr(nums, i + 1);
    }

    private void ReverseArr(int[] nums, int i) {
        int right = nums.length - 1;
        ;
        while (i < right) {
            Swap(nums, i, right);
            ++i;
            --right;
        }
    }

    private void Swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                /*mid左侧是有序数组*/
                if (nums[0] <= target && target < nums[mid]) {
                    /*target在mid左侧*/
                    right = mid - 1;
                } else {
                    /*target在mid右侧*/
                    left = mid + 1;
                }
            } else {
                /*mid右边侧是有序数组*/
                if (nums[mid] < target && target <= nums[length - 1]) {
                    /*target在mid右侧*/
                    left = mid + 1;
                } else {
                    /*target在mid左侧*/
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = leftBoundary(nums, target);
        ans[1] = rightBoundary(nums, target);
        return ans;
    }

    private int rightBoundary(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length;

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                left = mid + 1; // 注意
            }
        }
        return left - 1;
    }

    private int leftBoundary(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length;

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                /*nums[mid] > target*/
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subSudoku = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '1';
                    rows[i][index]++;
                    columns[j][index]++;
                    subSudoku[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subSudoku[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsForCombinationSum(candidates, target, ans, 0, list);
        return ans;
    }

    private void dfsForCombinationSum(int[] candidates, int target, List<List<Integer>> ans, int index, List<Integer> list) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (target > 0) {
                list.add(candidates[i]);
                dfsForCombinationSum(candidates, target - candidates[i], ans, i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsForCombinationSum2(candidates, target, 0, ans, list);
        return ans;
    }

    private void dfsForCombinationSum2(int[] candidates, int target, int index, List<List<Integer>> ans, List<Integer> list) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; ++i) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            if (target > 0) {
                list.add(candidates[i]);
                dfsForCombinationSum2(candidates, target - candidates[i], i + 1, ans, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            leftMax = Math.max(leftMax, left);
            rightMax = Math.max(rightMax, right);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int nextIndex = 0, maxPosition = 0;
        int length = nums.length;
        int step = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == nextIndex) {
                nextIndex = maxPosition;
                ++step;
            }
        }
        return step;
    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsForPermute(ans, list, nums);
        return ans;
    }

    private void dfsForPermute(List<List<Integer>> ans,
                               List<Integer> list, int[] nums) {
        /*用res.add(new linkedList(path))而不是res.add(path)。
        因为path指向的对象在不断地增加和删除元素，最后会变成空。
        得到的结果会变成[[][][][][]]所以要用new linkedList(path)拷贝一个对象加入res*/
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                dfsForPermute(ans, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfsForPermute2(ans, list, nums, visited);
        return ans;
    }

    private void dfsForPermute2(List<List<Integer>> ans,
                                List<Integer> list, int[] nums, boolean[] visited) {
        /*用res.add(new linkedList(path))而不是res.add(path)。
        因为path指向的对象在不断地增加和删除元素，最后会变成空。
        得到的结果会变成[[][][][][]]所以要用new linkedList(path)拷贝一个对象加入res*/
        if (list.size() == nums.length && !ans.contains(list)) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                dfsForPermute2(ans, list, nums, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 48. 旋转图像
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length - 1; ++i) {
            for (int j = 0; j < length - 1 - i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - j][length - 1 - i];
                matrix[length - 1 - j][length - 1 - i] = temp;
            }
        }
        for (int i = 0; i < length / 2; ++i) {
            for (int j = 0; j < length; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - i][j];
                matrix[length - 1 - i][j] = temp;
            }
        }
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int ans = nums[0], pre = 0;
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    /**
     * 54. 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        int x = 0, y = 0, index = 0;
        int top = 0, bottom = rows - 1, left = 0, right = columns - 1;
        while (index < rows * columns) {
            /*向右*/
            while (index < rows * columns && y <= right) {
                list.add(matrix[x][y]);
                ++index;
                ++y;
            }
            --y;
            ++x;
            ++top;
            /*向下*/
            while (index < rows * columns && x <= bottom) {
                list.add(matrix[x][y]);
                ++index;
                ++x;
            }
            --x;
            --y;
            --right;
            /*向左*/
            while (index < rows * columns && y >= left) {
                list.add(matrix[x][y]);
                ++index;
                --y;
            }
            ++y;
            --x;
            --bottom;
            while (index < rows * columns && x >= top) {
                list.add(matrix[x][y]);
                ++index;
                --x;
            }
            ++x;
            ++y;
            ++left;
        }
        return list;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxPosition = 0, nextPosition = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > maxPosition) {
                return false;
            }
            nextPosition = i + nums[i];
            if (nextPosition >= length - 1) {
                return true;
            }
            maxPosition = Math.max(maxPosition, nextPosition);
        }
        return true;
    }

    /**
     * 56. 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < left) {
                ans.add(new int[]{left, right});
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], right);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 57. 插入区间
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int length = intervals.length;
        int index = 0;

        /*不重合*/
        while (index < length && intervals[index][1] < newInterval[0]) {
            ans.add(new int[]{intervals[index][0], intervals[index][1]});
            ++index;
        }
        /*重合*/
        while (index < length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            ++index;
        }
        ans.add(newInterval);
        /*不重合*/
        while (index < length && intervals[index][0] >= newInterval[1]) {
            ans.add(new int[]{intervals[index][0], intervals[index][1]});
            ++index;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 59. 螺旋矩阵 II
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int index = 1;
        int x = 0, y = 0;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (index <= n * n) {
            /*向右边*/
            while (index <= n * n && y <= right) {
                ans[x][y] = index;
                index++;
                y++;
            }
            ++x;
            --y;
            ++top;
            /*向下面*/
            while (index <= n * n && x <= bottom) {
                ans[x][y] = index;
                ++index;
                ++x;
            }
            --x;
            --y;
            --right;
            /*向左*/
            while (index <= n * n && y >= left) {
                ans[x][y] = index;
                index++;
                --y;
            }
            ++y;
            --x;
            bottom--;
            /*向上*/
            while (index <= n * n && x >= top) {
                ans[x][y] = index;
                index++;
                x--;
            }
            ++x;
            y++;
            left++;
        }
        return ans;
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[rows][columns];
        dp[0][0] = 1;
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 1; j < columns; j++) {
            if (obstacleGrid[0][j] != 1) {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = 0;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[rows - 1][columns - 1];
    }

    /**
     * 64. 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[rows - 1][columns - 1];
    }

    /**
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int remainder = 1;
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int curr = carry + digits[i];
            remainder = curr % 10;
            carry = curr / 10;
            list.add(remainder);
        }
        if (carry > 0) {
            list.add(carry);
        }
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = list.get(size - 1 - i);
        }
        return ans;
    }

    /**
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean flagRow = false, flagColumn = false;
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                flagRow = true;
            }
        }
        for (int j = 0; j < columns; j++) {
            if (matrix[0][j] == 0) {
                flagColumn = true;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }


        if (flagRow) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagColumn) {
            for (int j = 0; j < columns; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 74. 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (target <= matrix[i][columns - 1]) {
                int left = 0, right = columns - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 75. 颜色分类
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                num0++;
            } else if (nums[i] == 1) {
                num1++;
            } else if (nums[i] == 2) {
                num2++;
            }
        }
        for (int i = 0; i < length; i++) {
            if (i < num0) {
                nums[i] = 0;
            } else if (i < num0 + num1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsForCombine(ans, list, n, k, 1);
        return ans;
    }

    private void dfsForCombine(List<List<Integer>> ans, List<Integer> list, int n, int k, int curr) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = curr; i <= n; i++) {
            if (!list.contains(i)) {
                list.add(i);
                dfsForCombine(ans, list, n, k, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsForSubsets(ans, list, 0, nums);
        return ans;
    }

    private void dfsForSubsets(List<List<Integer>> ans, List<Integer> list, int curr, int[] nums) {
        ans.add(new ArrayList<>(list));
        for (int i = curr; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
//                System.out.println(list);
                dfsForSubsets(ans, list, i, nums);
                list.remove(list.size()-1);
//                System.out.println(list);
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(new ShuZu().combine(4, 2));
        System.out.println(new ShuZu().subsets(new int[]{1, 2, 3}));
    }
}
