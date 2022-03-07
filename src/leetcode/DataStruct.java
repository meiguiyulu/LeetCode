package leetcode;

import javax.swing.*;
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class DataStruct {

    /**
     * 217. 存在重复元素
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int x = m - 1, y = n - 1;
        while (x >= 0 && y >= 0) {
            if (nums1[x] >= nums2[y]) {
                nums1[index--] = nums1[x--];
            } else {
                nums1[index--] = nums2[y--];
            }
        }
        while (x >= 0) {
            nums1[index--] = nums1[x--];
        }
        while (y >= 0) {
            nums1[index--] = nums2[y--];
        }
    }

    /**
     * 350. 两个数组的交集 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (Integer integer : map1.keySet()) {
            if (map2.containsKey(integer)) {
                int size = Math.min(map1.get(integer), map2.get(integer));
                for (int i = 0; i < size; i++) {
                    list.add(integer);
                }
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
        List<Integer> list = new ArrayList<>();
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                ++index1;
            } else if (nums1[index1] > nums2[index2]) {
                ++index2;
            } else {
                list.add(nums1[index1]);
                ++index1;
                ++index2;
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int ans = 0, minValue = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else {
                ans = Math.max(ans, prices[i] - minValue);
            }
        }
        return ans;
    }

    /**
     * 566. 重塑矩阵
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int rows = mat.length, columns = mat[0].length;
        if (rows * columns == r * c) {
            int[][] ans = new int[r][c];
            int x = 0, y = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    ans[x][y] = mat[i][j];
                    ++y;
                    if (y >= c) {
                        ++x;
                        y = 0;
                    }
                }
            }
            return ans;
        } else {
            return mat;
        }
    }

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        /*for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 1) {
                list.add(1);
                ans.add(list);
            } else if (i == 2) {
                list.add(1);
                list.add(1);
                ans.add(list);
            } else {
                list.add(1);
                List<Integer> list1 = ans.get(ans.size() - 1);
                for (int j = 1; j < i - 1; j++) {
                    list.add(list1.get(j-1)+list1.get(j));
                }
                list.add(1);
                ans.add(list);
            }
        }*/
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    List<Integer> integerList = ans.get(ans.size() - 1);
                    list.add(integerList.get(j - 1) + integerList.get(j));
                }
            }
            ans.add(list);
        }
        return ans;
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
                if (board[i][j] != '.') {
                    int index = board[i][j] - '1';
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
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;
        List<Integer> rowsList = new ArrayList<>();
        List<Integer> columnsList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    if (!rowsList.contains(i)) {
                        rowsList.add(i);
                    }
                    if (!columnsList.contains(j)) {
                        columnsList.add(j);
                    }
                }
            }
        }
        for (int ros : rowsList) {
            for (int i = 0; i < columns; i++) {
                matrix[ros][i] = 0;
            }
        }
        for (int column : columnsList) {
            for (int i = 0; i < rows; i++) {
                matrix[i][column] = 0;
            }
        }
    }

    /**
     * 387. 字符串中的第一个唯一字符
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                /*不是唯一字符*/
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }
        int ans = -1;
        for (Character c : map.keySet()) {
            if (map.get(c) >= 0) {
                ans = map.get(c);
                break;
            }
        }
        return ans;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            int integer = map.getOrDefault(c, 0);
            if (integer > 0) {
                map.put(c, integer - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
            b[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 环形链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newList = new ListNode(-1);
        ListNode curr = newList;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        while (list1 != null) {
            curr.next = list1;
            list1 = list1.next;
            curr = curr.next;
        }
        while (list2 != null) {
            curr.next = list2;
            list2 = list2.next;
            curr = curr.next;
        }
        return newList.next;
    }

    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curr = dummyHead;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 83. 删除排序链表中的重复元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curr = head, fast = head;
        while (fast != null) {
            while (fast != null && fast.val == curr.val) {
                fast = fast.next;
            }
            curr.next = fast;
            curr = curr.next;
        }
        if (curr != null) {
            curr.next = null;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println(new DataStruct().generate(5));
    }

}
