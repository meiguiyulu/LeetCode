package leetcode;

import java.util.*;
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


    /**
     * 1822. 数组元素积的符号
     *
     * @param nums
     * @return
     */
    public int arraySign(int[] nums) {
        int positive = 0;// 负数
        for (int num : nums) {
            if (num < 0) {
                ++positive;
            } else if (num == 0) {
                return 0;
            }
        }
        if (positive % 2 == 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 1502. 判断能否形成等差数列
     *
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int ans = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != ans) {
                return false;
            }
        }
        return true;
    }

    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        while (n > 9) {
            int ans = 0;
            while (n > 0) {
                ans += Math.pow(n % 10, 2);
                n = n / 10;
            }
            n = ans;
        }
        return n == 1;
    }

    /**
     * 1790. 仅执行一次字符串交换能否使两个字符串相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean areAlmostEqual(String s1, String s2) {
        int[] a = new int[26];
        int[] b = new int[26];
        int length = s1.length();
        int num = 0;
        for (int i = 0; i < length; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            a[c1 - 'a']++;
            b[c2 - 'a']++;
            if (c1 != c2) {
                ++num;
            }
        }
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (a[i] > 0) {
                builder1.append((char) (i + 'a'));
                builder1.append(a[i]);
            }
            if (b[i] > 0) {
                builder2.append((char) (i + 'a'));
                builder2.append(b[i]);
            }
        }
        if (builder1.toString().equals(builder2.toString())) {
            return num == 0 || num == 2;
        } else {
            return false;
        }
    }

    /**
     * 589. N 叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        NTreeProOrder(ans, root);
        return ans;
    }

    private void NTreeProOrder(List<Integer> ans, Node root) {
        while (root != null) {
            ans.add(root.val);
            for (Node child : root.children) {
                NTreeProOrder(ans, child);
            }
        }
    }


    /**
     * 496. 下一个更大元素 I
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int length = nums2.length;
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int currNums1 = nums1[i];
            int j = 0;
            while (nums2[j] != currNums1) {
                j++;
            }
            int index = j + 1;
            while (index < length && nums2[index] <= currNums1) {
                ++index;
            }
            if (index < length) {
                ans[i] = nums2[index];
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums1.length];
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num > stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 1232. 缀点成线
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1588. 所有奇数长度子数组的和
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        int length = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; ++i) {
            int dis = 2;
            ans += arr[i];
            map.put(arr[i], arr[i]);
            int j = i;
            while (j + dis < length) {
                int curr = map.get(arr[i]) + arr[++j] + arr[++j];
                map.put(arr[i], curr);
                ans += curr;
            }
        }
        return ans;
    }

    /**
     * 283. 移动零
     * <p>
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        int length = nums.length;
        while (right < length) {
            while (right < length && nums[right] == 0) {
                ++right;
            }
            if (right < length) {
                nums[left++] = nums[right++];
            }
        }
        while (left < length) {
            nums[left++] = 0;
        }
    }

    /**
     * 1672. 最富有客户的资产总量
     *
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (int i = 0; i < accounts.length; i++) {
            int curr = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                curr += accounts[i][j];
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    /**
     * 1572. 矩阵对角线元素的和
     *
     * @param mat
     * @return
     */
    public int diagonalSum(int[][] mat) {
        int length = mat.length;
        int ans = 0;
        for (int i = 0; i < length; ++i) {
            ans = ans + mat[i][i] + mat[i][length - 1 - i];
        }
        if (length % 2 == 1) {
            return ans - mat[(length - 1) / 2][(length - 1) / 2];
        } else {
            return ans;
        }
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
        int height = mat.length, width = mat[0].length;
        if (height * width == r * c) {
            int[][] ans = new int[r][c];
            int indexX = 0, indexY = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    ans[indexX][indexY++] = mat[i][j];
                    if (indexY >= c) {
                        indexY = 0;
                        ++indexX;
                    }
                }
            }
            return ans;
        } else {
            return mat;
        }
    }

    /**
     * 1768. 交替合并字符串
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Math.max(len1, len2); ++i) {
            if (i < len1) {
                builder.append(word1.charAt(i));
            }
            if (i < len2) {
                builder.append(word2.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 1678. 设计 Goal 解析器
     *
     * @param command
     * @return
     */
    public String interpret(String command) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < command.length(); ++i) {
            if (command.charAt(i) == 'G') {
                builder.append("G");
            } else if (command.charAt(i) == '(' && command.charAt(i + 1) == ')') {
                builder.append("o");
                ++i;
            } else {
                builder.append("al");
                i = i + 3;
            }
        }
        return builder.toString();
    }

    /**
     * 389. 找不同
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
            b[t.charAt(i) - 'a']++;
        }
        b[t.charAt(t.length() - 1) - 'a']++;
        char ans = 0;
        for (int i = 0; i < 26; i++) {
            if (b[i] > a[i]) {
                ans = (char) (i + 'a');
            }
        }
        return ans;
    }

    public char findTheDifference2(String s, String t) {
        char ans1 = 0, ans2 = 0;
        for (char c : s.toCharArray()) {
            ans1 += c;
        }
        for (char c : t.toCharArray()) {
            ans2 += c;
        }
        return (char) (ans2 - ans1);
    }

    /**
     * 709. 转换成小写字母
     *
     * @param s
     * @return
     */
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return new String(chars);
    }

    /**
     * 1309. 解码字母到整数映射
     *
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            if (i >= 10) {
                map.put(i + "#", Character.toString(i - 1 + 'a'));
            } else {
                map.put(Integer.toString(i), Character.toString(i - 1 + 'a'));
            }
        }
        StringBuilder builder = new StringBuilder();
        int left = -1, right = 0, length = s.length();
        while (right < length) {
            while (right < length && s.charAt(right) != '#') {
                ++right;
            }
            if (right < length) {
                int index = right - 2;
                for (int i = left + 1; i < index; ++i) {
                    builder.append(map.get(Character.toString(s.charAt(i))));
                }
                String value = s.charAt(index) + Character.toString(s.charAt(index + 1)) + "#";
                builder.append(map.get(value));
            } else {
                for (int i = left + 1; i < right; i++) {
                    builder.append(map.get(String.valueOf(s.charAt(i))));
                }
            }
            left = right;
            ++right;
        }
        return builder.toString();
    }

    /**
     * 1290. 二进制链表转整数
     *
     * @param head
     * @return
     */
    public int getDecimalValue(LeetCode.ListNode head) {
        List<Integer> list = new ArrayList<>();
        int ans = 0;
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            ans += list.get(i) * Math.pow(2, size - 1 - i);
        }
        return ans;
    }

    /**
     * 876. 链表的中间结点
     *
     * @param head
     * @return
     */
    public LeetCode.ListNode middleNode(LeetCode.ListNode head) {
        int len = 0;
        LeetCode.ListNode curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        for (int i = 0; i < len / 2; ++i) {
            head = head.next;
        }
        return head;
    }

    public LeetCode.ListNode middleNode2(LeetCode.ListNode head) {
        /*快慢指针*/
        LeetCode.ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(LeetCode.TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        Deque<LeetCode.TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; ++i) {
                LeetCode.TreeNode node = deque.poll();
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }
            height++;
        }
        return height;
    }

    public int maxDepth2(LeetCode.TreeNode root) {
        /*迭代*/
        if (root == null)
            return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(LeetCode.TreeNode root) {
        /*层次遍历*/
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Deque<LeetCode.TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            for (int i = deque.size(); i > 0; --i) {
                LeetCode.TreeNode node = deque.poll();
                if (node.left != null) {
                    if (node.left.left == null && node.left.right == null) {
                        ans += node.left.val;
                    } else {
                        deque.offer(node.left);
                    }
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
        }
        return ans;
    }

    public int sumOfLeftLeaves2(LeetCode.TreeNode root) {
        /*深度优先*/
        return root == null ? 0 : dfsForLeftLeave(root);
    }

    private int dfsForLeftLeave(LeetCode.TreeNode root) {
        int ans = 0;
        if (root.left != null) {
            /*判断是不是叶子节点*/
            if (root.left.left == null && root.left.right == null) {
                ans += root.left.val;
            } else {
                ans += dfsForLeftLeave(root.left);
            }
        }
        if (root.right != null) {
            ans += dfsForLeftLeave(root.right);
        }
        return ans;
    }

    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++a[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < t.length(); ++i) {
            ++b[t.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 217. 存在重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.get(num) > 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(average(new int[]{4000, 3000, 1000, 2000}));
        System.out.println(largestPerimeter(new int[]{3, 2, 3, 4}));
        System.out.println(nearestValidPoint(3, 4, new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}));
        new ProgrammingAbility().areAlmostEqual("bank", "kanb");
        System.out.println(new ProgrammingAbility().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        System.out.println(new ProgrammingAbility().freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }


}
