package leetcode;

import java.util.*;

/**
 * @author LYJ
 * @create 2021-06-17 14:09
 */
public class LeetCode {

    class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，
     * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        /**
         * 方法一：两层循环
         */
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] nums, int target) {
        /**
         * 方法二：哈希表
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    /**
     * 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        int temp = 0;
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;

            if (l1 != null) {
                x = l1.val;
            }
            if (l2 != null) {
                y = l2.val;
            }
            int curr = (x + y + temp) % 10;
            temp = (x + y + temp) / 10;

            node.next = new ListNode(curr);
            node = node.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (temp > 0) {
            node.next = new ListNode(temp);
        }
        return dummyHead.next;
    }

    /**
     * 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        int ans = 1;
        for (; end < length; ++end) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    /**
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int length = s.length();
        String ansString = "";
        for (int i = 0; i < length - 1; ++i) {
            String str1 = GetPalindrome(s, i, i);
            String str2 = GetPalindrome(s, i, i + 1);
            if (str1.length() < str2.length()) {
                str1 = str2;
            }
            if (str1.length() > ansString.length()) {
                ansString = str1;
            }
        }
        return ansString;
    }

    private String GetPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return s.substring(left + 1, right);
    }

    public String longestPalindrome2(String s) {
        int maxLength = 0;
        int maxRight = 0, maxLeft = 0;
        int length = s.length();
        int[][] dp = new int[length][length];

        for (int i = 0; i < length; ++i) {
            dp[i][i] = 1;
        }
        for (int right = 1; right < length; ++right) {
            for (int left = 0; left < right; ++left) {
                if (s.charAt(right) != s.charAt(left)) {
                    dp[left][right] = 0;
                } else {
                    if (right - left <= 2) {
                        dp[left][right] = 1;
                    } else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }
                if (dp[left][right] == 1) {
                    if (right - left + 1 > maxLength) {
                        maxLength = right - left + 1;
                        maxLeft = left;
                        maxRight = right;
                    }
                }
            }
        }
        return s.substring(maxLeft, maxRight + 1);
    }

    /**
     * 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int curr = x % 10;
            x = x / 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && curr > 7)) {
                return 0;
            }
            if ((ans < Integer.MIN_VALUE / 10) || (ans == Integer.MIN_VALUE / 10 && curr < -8)) {
                return 0;
            }
            ans = ans * 10 + curr;
        }
        return ans;
    }

    /**
     * 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        /**
         * 辅助数组
         */
        List<Integer> list = new ArrayList<>();
        if (x < 0) {
            return false;
        }
        while (x != 0) {
            list.add(x % 10);
            x = x / 10;
        }
        int len = list.size();
        for (int i = 0; i < len / 2; ++i) {
            if (list.get(i) != list.get(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        /**
         * 反转数字的后半部分
         */
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int cur = 0;
        while (x > cur) {
            cur = cur * 10 + x % 10;
            x = x / 10;
        }
        return x == cur || x == cur / 10;
    }

    /**
     * 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        /**
         * 暴力
         */
        int ans = 0;
        for (int i = 0; i < height.length - 1; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int curr = (j - i) * Math.min(height[i], height[j]);
                ans = Math.max(ans, curr);
            }
        }
        return ans;
    }

    public int maxArea2(int[] height) {
        /**
         * 双指针
         */
        int ans = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int curr = (right - left) * Math.min(height[left], height[right]);
            ans = Math.max(ans, curr);
            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return ans;
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     */
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) {
            return "";
        }
        String curr = strs[0];
        for (int i = 1; i < len; ++i) {
            StringBuilder builder = new StringBuilder();
            int k = 0;
            while (k < curr.length() && k < strs[i].length()) {
                if (curr.charAt(k) == strs[i].charAt(k)) {
                    ++k;
                } else {
                    break;
                }
            }
            if (k == 0) {
                return "";
            } else {
                curr = curr.substring(0, k);
            }
        }
        return curr;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int length = strs.length;
        for (int i = 0; i < strs[0].length(); ++i) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < length; ++j) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * <p>
     * 示例 2：
     * 输入：nums = []
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
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
     * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     */
    public static int threeSumClosest(int[] nums, int target) {
        int best = 10000000;
        Arrays.sort(nums);
        int length = nums.length;
        for (int first = 0; first < length; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int second = first + 1, third = length - 1;
            while (second < third) {
                int curr = nums[first] + nums[second] + nums[third];
                if (curr == target) {
                    return curr;
                }
                if (Math.abs(curr - target) < Math.abs(best - target)) {
                    best = curr;
                }
                if (curr < target) {
                    int j = second + 1;
                    while (j < third && nums[j] == nums[second]) {
                        ++j;
                    }
                    second = j;
                } else {
                    int k = third - 1;
                    while (k > second && nums[k] == nums[third]) {
                        --k;
                    }
                    third = k;
                }
            }
        }
        return best;
    }

    /**
     * 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        DFS(ans, map, new StringBuilder(), 0, digits);
        return ans;
    }

    private void DFS(List<String> ans, Map<Character, String> map, StringBuilder builder, int index, String digits) {
        if (index == digits.length()) {
            ans.add(builder.toString());
        } else {
            String s = map.get(digits.charAt(index));
            for (int i = 0; i < s.length(); ++i) {
                builder.append(s.charAt(i));
                DFS(ans, map, builder, index + 1, digits);
                builder.deleteCharAt(index);
            }
        }
    }

    /**
     * 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c和 d，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * <p>
     * 注意：答案中不可以包含重复的四元组。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        for (int first = 0; first < length - 3; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < length - 2; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                if (nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                    break;
                }
                if (nums[first] + nums[second] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = second + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[first] + nums[second] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            ++left;
                        }
                        ++left;
                        while (left < right && nums[right] == nums[right - 1]) {
                            --right;
                        }
                        --right;
                    } else if (sum < target) {
                        ++left;
                    } else {
                        --right;
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode p = dummyNode, q = dummyNode;
        for (int i = 0; i <= n; ++i) {
            p = p.next;
        }
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return dummyNode.next;
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public boolean isValid(String s) {
        boolean flag = true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (s.charAt(i) == ')' && stack.peek() != '(') {
                    return false;
                } else if (s.charAt(i) == ']' && stack.peek() != '[') {
                    return false;
                } else if (s.charAt(i) == '}' && stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        if (!stack.isEmpty()) {
            flag = false;
        }
        return flag;
    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummyNode.next;
    }

    /**
     * 22. 括号生成
     * <p>
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
     */

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        DFSTeam22(n, ans, new StringBuilder(), n, n);
        return ans;
    }

    private void DFSTeam22(int n, List<String> ans, StringBuilder builder, int left, int right) {
        if (builder.length() == 2 * n) {
            ans.add(builder.toString());
            return;
        }
        if (left > 0) {
            DFSTeam22(n, ans, builder.append('('), left - 1, right);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (left < right) {
            DFSTeam22(n, ans, builder.append(')'), left, right - 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 24. 两两交换链表中的节点
     * <p>
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead, q = dummyHead, k;

        while (q != null) {
            k = p;
            q = q.next;
            if (q == null) {
                break;
            }
            q = q.next;
            if (q == null) {
                break;
            }
            p = p.next;
            k.next = q;
            p.next = q.next;
            q.next = p;
            q = q.next;
        }

        return dummyHead.next;
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
     */
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0 || length == 1) {
            return length;
        }
        int slow = 1, fast = 1;
        for (; fast < length; ++fast) {
            if (nums[fast] == nums[fast - 1]) {
                continue;
            } else {
                nums[slow] = nums[fast];
                ++slow;
            }
        }
        return slow;
    }

    /**
     * 31. 下一个排列
     * <p>
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     */
    public void nextPermutation(int[] nums) {
        int small = nums.length - 2;
        while (small >= 0 && nums[small] >= nums[small + 1]) {
            --small;
        }
        if (small >= 0) {
            int big = nums.length - 1;
            while (small < big && nums[small] >= nums[big]) {
                --big;
            }
            Swap(nums, small, big);
        }
        Reverse(nums, small + 1);
    }

    private void Reverse(int[] nums, int left) {
        int right = nums.length - 1;
        while (left < right) {
            Swap(nums, left, right);
            ++left;
            --right;
        }
    }

    private void Swap(int[] nums, int small, int big) {
        int temp = nums[small];
        nums[small] = nums[big];
        nums[big] = temp;
    }

    /**
     * 33. 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        // 顺序遍历
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        // 二叉查找

        int length = nums.length;
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // nums[mid] < target
                if (nums[mid] < target && target <= nums[length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶：
     * <p>
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     */

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = leftBound(nums, target);
        int right = rightBound(nums, target);

        return new int[]{left, right};
    }

    private int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // nums[mid] > target
                right = mid;
            }
        }
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }

    private int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // num[mid] > target
                right = mid;
            }
        }
        if (left == nums.length) {
            return -1;
        }
        if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    /**
     * 35. 搜索插入位置
     * <p>
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 请必须使用时间复杂度为 O(log n) 的算法
     */

    public int searchInsert(int[] nums, int target) {
        // 顺序遍历
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        // 二分查找
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // nums[mid] > target
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 38. 外观数列
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        String last_ans = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder builder = new StringBuilder();
            char curr = last_ans.charAt(0);
            int num = 1;
            for (int j = 1; j < last_ans.length(); ++j) {
                if (last_ans.charAt(j) == curr) {
                    ++num;
                } else {
                    builder.append(num);
                    builder.append(curr);
                    curr = last_ans.charAt(j);
                    num = 1;
                }
            }
            builder.append(num).append(curr);
            last_ans = builder.toString();
        }
        return last_ans;
        /**
         * 递归
         * class Solution {
         *     public String countAndSay(int n) {
         *        if (n == 1) {
         *             return "1";
         *         }
         *         String last_ans = countAndSay(n - 1);
         *         StringBuilder builder = new StringBuilder();
         *         char curr=last_ans.charAt(0);
         *         int num=1;
         *         for (int i=1;i < last_ans.length(); ++i) {
         *             if (last_ans.charAt(i) == curr) {
         *                 ++num;
         *             } else {
         *                 builder.append(num);
         *                 builder.append(curr);
         *                 curr = last_ans.charAt(i);
         *                 num = 1;
         *             }
         *         }
         *         builder.append(num);
         *         builder.append(curr);
         *         return builder.toString();
         *     }
         * }
         */
    }

    /**
     * 39. 组合总和
     * <p>
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出candidates中所有可以使数字和为目标数target的唯一组合。
     * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
     * <p>
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        DFSTeam39(ans, list, candidates, target, 0);
        return ans;
    }

    private void DFSTeam39(List<List<Integer>> ans, List<Integer> list, int[] candidates, int target, int index) {
        if (target == 0) {
            if (!ans.contains(list)) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = index; i < candidates.length; ++i) {
            list.add(candidates[i]);
            DFSTeam39(ans, list, candidates, target - candidates[i], index);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     * <p>
     * 注意：解集不能包含重复的组合。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        DFSTeam40(ans, list, candidates, target, 0);

        return ans;
    }

    private void DFSTeam40(List<List<Integer>> ans, List<Integer> list, int[] candidates, int target, int index) {
        if (target == 0) {
            if (!ans.contains(list)) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = index; i < candidates.length; ++i) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                list.add(candidates[i]);
                DFSTeam40(ans, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 46. 全排列
     * <p>
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        DFSTeam46(ans, list, nums);

        return ans;
    }

    private void DFSTeam46(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                DFSTeam46(ans, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * 48. 旋转图像
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        for (int i = 0; i < rows / 2; ++i) {
            for (int j = 0; j < rows; ++j) {
                int temp = matrix[rows - 1 - i][j];
                matrix[rows - 1 - i][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = rows - 1; j > i; --j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }


    /**
     * 49. 字母异位词分组
     * <p>
     * 给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
     * <p>
     * 字母异位词指字母相同，但排列不同的字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String key = new String(word);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            String word = strs[i];
            int[] count = new int[26];
            for (int j = 0; j < word.length(); ++j) {
                ++count[word.charAt(j) - 'a'];
            }

            StringBuilder builder = new StringBuilder();
            for (int k = 0; k < 26; ++k) {
                if (count[k] > 0) {
                    builder.append('a' + k);
                    builder.append(count[k]);
                }
            }
            String key = builder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }


    /**
     * 145. 二叉树的后序遍历
     * 给定一个二叉树，返回它的 后序 遍历。
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postOrder(ans, root);
        return ans;
    }

    private void postOrder(List<Integer> ans, TreeNode root) {
        if (root != null) {
            postOrder(ans, root.left);
            postOrder(ans, root.right);
            ans.add(root.val);
        }
    }

    private List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();

        while (root != null || !stack.isEmpty()) {
            if (root == null && set.contains(stack.peek())) {
                ans.add(stack.pop().val);
            } else if (root == null) {
                set.add(stack.peek());
                root = stack.peek().right;
            } else {
                stack.add(root);
                root = root.left;
            }
        }
        return ans;
    }

    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        preOrder(ans, root);
        return ans;
    }

    private void preOrder(List<Integer> ans, TreeNode root) {
        if (root != null) {
            ans.add(root.val);
            preOrder(ans, root.left);
            preOrder(ans, root.right);
        }
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                ans.add(root.val);
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return ans;
    }

    /**
     * 94. 二叉树的中序遍历
     * <p>
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrder(ans, root);
        return ans;
    }

    private void inOrder(List<Integer> ans, TreeNode root) {
        if (root != null) {
            inOrder(ans, root.left);
            ans.add(root.val);
            inOrder(ans, root.right);
        }
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    /**
     * 50. Pow(x, n)
     * <p>
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
     */

    public double myPow(double x, int n) {
//        return n >= 0 ? Pow1(x, n) : 1.0 / Pow1(x, -n);
        return n >= 0 ? Pow2(x, n) : 1.0 / Pow2(x, -n);
    }

    private double Pow2(double x, int n) {
        double ans = 1.0;
        double degree = x;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = ans * degree;
            }
            degree = degree * degree;
            n = n / 2;
        }
        return ans;
    }

    private double Pow1(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = Pow1(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 53. 最大子序和
     * <p>
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }

        return ans;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        int x = 0, y = 0, num = 0;
        while (num < rows * cols) {
            while (num < rows * cols && y <= right) {
                ans.add(matrix[x][y]);
                ++num;
                ++y;
            }
            ++x;
            --y;
            ++top;

            while (num < rows * cols && x <= bottom) {
                ans.add(matrix[x][y]);
                ++num;
                ++x;
            }
            --y;
            --x;
            --right;

            while (num < rows * cols && y >= left) {
                ans.add(matrix[x][y]);
                ++num;
                --y;
            }
            ++y;
            --x;
            --bottom;

            while (num < rows * cols && x >= top) {
                ans.add(matrix[x][y]);
                ++num;
                --x;
            }
            ++x;
            ++y;
            ++left;
        }

        return ans;
    }


    /**
     * 55. 跳跃游戏
     * <p>
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int length = nums.length - 1;
        int maxReach = 0;
        boolean flag = false;
        for (int i = 0; i < length; ++i) {
            if (i > maxReach) {
                break;
            }
            int currReach = i + nums[i];
            if (currReach >= length) {
                flag = true;
                break;
            }
            maxReach = Math.max(maxReach, currReach);
        }
        return flag;
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，
     * 其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，
     * 该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 思路：先排序
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList();
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] ints = list.get(list.size() - 1);
            if (ints[1] < intervals[i][0]) {
                list.add(intervals[i]);
            } else {
                list.get(list.size() - 1)[1] = Math.max(ints[1], intervals[i][1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <p>
     * 思路：先求出链表长度length, 再求出 t = k % length
     * 把后 t 个结点挪到链表的开始
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        int length = 1;
        while (temp.next != null) {
            ++length;
            temp = temp.next;
        }
        // temp指向了最后一个结点
        k = k % length;
        if (k == 0) {
            return head;
        } else {
            ListNode curr = head;
            for (int i = 1; i < length - k; i++) {
                curr = curr.next;
            }
            ListNode newHead = curr.next;
            curr.next = null;
            temp.next = head;
            return newHead;
        }
    }

    /**
     * 62. 不同路径
     * <p>
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 64. 最小路径和
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }


    /**
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        List<Integer> list = new ArrayList<>();
        int a = 1; // 进位数
        for (int i = length - 1; i >= 0; --i) {
            int digit = digits[i];
            list.add((digit + a) % 10);
            a = (digit + a) / 10;
        }
        if (a != 0) {
            list.add(a);
        }
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = list.get(size - i - 1);
        }
        return ans;
    }

    /**
     * 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 平方根 。
     * <p>
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * <p>
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        int ans = -1;
        int left = 1, right = x - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                ans = mid;
                break;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }


    /**
     * 83. 删除排序链表中的重复元素
     * <p>
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * <p>
     * 返回同样按升序排列的结果链表。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = head, q = p.next;
        while (q != null) {

            while (q != null && q.val == p.val) {
                q = q.next;
            }
            p.next = q;
            if (q != null) {
                p = q;
                q = q.next;
            }
        }
        return head;
    }


    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null && p.next.val < x) {
            p = p.next;
        }
        if (p.next == null) {
            return dummyHead.next;
        }
        ListNode q = p;
        while (q.next != null) {
            if (q.next.val >= x) {
                q = q.next;
            } else {
                ListNode node = new ListNode(q.next.val);
                node.next = p.next;
                p.next = node;
                p = p.next;
                q.next = q.next.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode partition2(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }


    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
     * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[index--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }


    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        DFS90(nums, ans, list, 0, visited);
        return ans;
    }

    private void DFS90(int[] nums, List<List<Integer>> ans, List<Integer> list, int index, boolean[] visited) {

        if (!ans.contains(list)) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = index; i < nums.length; i++) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                DFS90(nums, ans, list, i, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 91. 解码方法
     * <p>
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     * <p>
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * <p>
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     * <p>
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * <p>
     * 题目数据保证答案肯定是一个 32 位 的整数。
     */
    public int numDecodings(String s) {
        HashMap<Integer, Integer> memoization = new HashMap<>();
        return getAns(s, 0, memoization);
    }

    private int getAns(String s, int start, HashMap<Integer, Integer> memoization) {
        if (start == s.length()) {
            return 1;
        }
        if (s.charAt(start) == '0') {
            return 0;
        }
        //判断之前是否计算过
        int m = memoization.getOrDefault(start, -1);
        if (m != -1) {
            return m;
        }
        int ans1 = getAns(s, start + 1, memoization);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) {
                ans2 = getAns(s, start + 2, memoization);
            }
        }
        //将结果保存
        memoization.put(start, ans1 + ans2);
        return ans1 + ans2;
    }

    /*方法二：动态规划*/
    public int numDecodings2(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        for (int i = 1; i <= length; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length];
    }

    /**
     * 求二叉树的最大深度
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth(TreeNode root) {
        // write code here
        if (root == null)
            return 0;
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ++ans;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null)
                    queue.offer(poll.left);

                if (poll.right != null)
                    queue.offer(poll.right);
            }
        }
        return ans;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }


    /**
     * 剑指 Offer 24. 反转链表
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     * @param head
     * @return
     */

    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return JudgeIsSymmetric(root.left, root.right);
    }

    private boolean JudgeIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val && JudgeIsSymmetric(left.left, right.right)
                && JudgeIsSymmetric(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode q1 = queue.poll();
            TreeNode q2 = queue.poll();
            if (q1 == null && q2 == null)
                continue;
            if (q1 == null || q2 == null || q1.val != q2.val)
                return false;
            queue.offer(q1.left);
            queue.offer(q2.right);
            queue.offer(q1.right);
            queue.offer(q2.left);
        }
        return true;
    }

    /**
     * 237. 删除链表中的节点
     * <p>
     * 请编写一个函数，用于 删除单链表中某个特定节点 。
     * 在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
     * <p>
     * 题目数据保证需要删除的节点 不是末尾节点 。
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 268. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        for (int element : nums) {
            if (element != index) {
                break;
            }
            ++index;
        }
        return index;
    }

    public int missingNumber2(int[] nums) {
        // 位运算
        int length = nums.length;
        int ans = nums[0];
        for (int i = 1; i < length; i++) {
            ans = ans ^ nums[i];
        }
        for (int i = 0; i <= length; i++) {
            ans = ans ^ i;
        }
        return ans;
    }

    public int missingNumber3(int[] nums) {
        // 数学
        int length = nums.length;
        int total = length * (length + 1) / 2;
        for (int i = 0; i < length; i++) {
            total = total - nums[i];
        }
        return total;
    }

    /**
     * 299. 猜数字游戏
     * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
     * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
     * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛），
     * 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
     */
    public static String getHint(String secret, String guess) {
        int ans1 = 0, ans2 = 0;
        int length = secret.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            Character c = guess.charAt(i);
            if (secret.charAt(i) == c) {
                ++ans1;
                int curr = map.get(c);
                map.put(c, curr - 1);
            }
        }
        for (int i = 0; i < length; i++) {
            Character c = guess.charAt(i);
            if (c != secret.charAt(i)) {
                if (map.containsKey(c)) {
                    int curr = map.get(c);
                    if (curr > 0) {
                        ++ans2;
                    }
                    map.put(c, curr - 1);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(ans1);
        builder.append("A");
        builder.append(ans2);
        builder.append("B");
        return builder.toString();
    }

    /**
     * 319. 灯泡开关
     * <p>
     * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。
     * 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
     * <p>
     * 找出并返回 n 轮后有多少个亮着的灯泡。
     */
    public int bulbSwitch(int n) {
        int ans = 0;
        boolean[] bulb = new boolean[n];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if ((j + 1) % i == 0) {
                    bulb[j] = !bulb[j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (bulb[i]) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 367. 有效的完全平方数
     * <p>
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     */
    public boolean isPerfectSquare(int num) {
/*        内置函数
        int x = (int) Math.sqrt(num);
        return x * x == num;*/
        // 二分查找
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square > num) {
                right = mid - 1;
            } else if (square < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 375. 猜数字大小 II
     * <p>
     * 我们正在玩一个猜数游戏，游戏规则如下：
     * <p>
     * 我从1到 n 之间选择一个数字。
     * 你来猜我选了哪个数字。
     * 如果你猜到正确的数字，就会 赢得游戏 。
     * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
     * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
     * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int currCost = Math.max(dp[i][k - 1], dp[k + 1][j]) + k;
                    minCost = Math.min(currCost, minCost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n];
    }

    /**
     * 397. 整数替换
     * <p>
     * 给定一个正整数 n ，你可以做如下操作：
     * 如果 n 是偶数，则用 n / 2替换 n 。
     * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
     * n 变为 1 所需的最小替换次数是多少？
     */
    public static int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }
        return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
        /**
         *     Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
         *
         *     public int integerReplacement(int n) {
         *         if (n == 1) {
         *             return 0;
         *         }
         *         if (!memo.containsKey(n)) {
         *             if (n % 2 == 0) {
         *                 memo.put(n, 1 + integerReplacement(n / 2));
         *             } else {
         *                 memo.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
         *             }
         *         }
         *         return memo.get(n);
         *     }
         */
    }

    /**
     * 520. 检测大写字母
     * <p>
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * <p>
     * 全部字母都是大写，比如 "USA" 。
     * 单词中所有字母都不是大写，比如 "leetcode" 。
     * 如果单词不只含有一个字母，只有首字母大写，比如"Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     */
    public boolean detectCapitalUse(String word) {
        int length = word.length();
        if (length == 1) {
            return true;
        }
        char c = word.charAt(0);
        if (c >= 'a' && c <= 'z') {
            // 后面只能是小写
            for (int i = 1; i < length; i++) {
                if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                    return false;
                }
            }
        } else {
            char c1 = word.charAt(1);
            if (c1 >= 'a' && c1 <= 'z') {
                // 后面只能是小写
                for (int i = 2; i < length; i++) {
                    if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                        return false;
                    }
                }
            } else {
                // 后面只能是大写
                for (int i = 2; i < length; i++) {
                    if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 563. 二叉树的坡度
     * <p>
     * 给定一个二叉树，计算 整个树 的坡度 。
     * <p>
     * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
     * 整个树 的坡度就是其所有节点的坡度之和。
     */
    private static int slope = 0;

    public int findTilt(TreeNode root) {
        DFS520(root);
        return slope;
    }

    private int DFS520(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = DFS520(root.left);
        int right = DFS520(root.right);
        slope += Math.abs(left - right);
        return left + right + root.val;
    }

    /**
     * 594. 最长和谐子序列
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     */
    public static int findLHS(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int lastNum = nums[0];
        int lastLength = 1;
        int currNum, currLength;

        for (int i = 1; i < nums.length; i++) {
            currNum = nums[i];
            currLength = map.get(currNum);
            if (currNum - lastNum == 1) {
                ans = Math.max(ans, lastLength + currLength);
            }
            lastNum = currNum;
            lastLength = currLength;
        }

        return ans;
    }

    public static int findLHS2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int curr : map.keySet()) {
            if (map.containsKey(curr + 1)) {
                ans = Math.max(ans, map.get(curr) + map.get(curr + 1));
            }
        }
        return ans;
    }


    /**
     * 575. 分糖果
     * <p>
     * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     * <p>
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的最多种类数。
     */
    public int distributeCandies1(int[] candyType) {
        int len = candyType.length;
        Arrays.sort(candyType);
        int sum = 1;
        for (int i = 1; i < len; i++) {
            if (candyType[i] != candyType[i - 1]) {
                ++sum;
            } else {
                continue;
            }
        }
        return Math.min(sum, len / 2);
    }

    public int distributeCandies2(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        int length = candyType.length;
        for (int candy : candyType) {
            set.add(candy);
        }
        return Math.min(set.size(), length / 2);
    }

    /**
     * 598. 范围求和 II
     * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
     * <p>
     * 操作用二维数组表示，其中的每个操作用一个含有两个正整数a 和 b 的数组表示，含义是将所有符合0 <= i < a 以及 0 <= j < b 的元素M[i][j]的值都增加 1。
     * <p>
     * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
     */
    public int maxCount(int m, int n, int[][] ops) {
        int length = ops.length;
        int maxRow = m;
        int maxCol = n;
        for (int i = 0; i < length; i++) {
            int row = ops[i][0];
            int col = ops[i][1];
            maxRow = Math.min(maxRow, row);
            maxCol = Math.min(maxCol, col);
        }
        if (maxRow == 0) {
            maxRow = 1;
        }
        if (maxCol == 0) {
            maxCol = 1;
        }
        return maxRow * maxCol;
    }

    /**
     * 688. 骑士在棋盘上的概率
     * <p>
     * 在一个n x n的国际象棋棋盘上，一个骑士从单元格 (row, column)开始，并尝试进行 k 次移动。
     * 行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {

        int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
        double dp[][][] = new double[k + 1][n][n];
        for (int step = 0; step <= k; ++step) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int ni = i + dir[0], nj = j + dir[1];
                            if (ni >= 0 & ni < n && nj >= 0 & nj < 0) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        } else {
            return null;
        }
    }

    /**
     * 717. 1比特与2比特字符
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length;
        int index = 0;
        while (index <= length - 2) {
            if (bits[index] == 1) {
                index += 2;
            } else {
                ++index;
            }
        }
        return index == length - 1;
    }

    /**
     * 859. 亲密字符串
     * <p>
     * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
     * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
     * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     */
    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if (++count[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
        }
        int first = -1, second = -1, num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                ++num;
                if (num == 1) {
                    first = i;
                }
                if (num == 2) {
                    second = i;
                }
            }
        }
        if (num == 2 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 969. 煎饼排序
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int n = arr.length; n > 1; n--) {
            int index = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == n - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, n - 1);
            ret.add(index + 1);
            ret.add(n);
        }
        return ret;
    }

    public void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    /**
     * 1004. 最大连续1的个数 III
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * <p>
     * 返回仅包含 1 的最长（连续）子数组的长度。
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int length = nums.length;
        int left = 0, right = left;
        int ans = 0;
        int zeroNum = 0;
        while (right < length) {

            if (nums[right] == 0) {
                ++zeroNum;
            }
            if (zeroNum > k) {
                while (nums[left] == 1) {
                    ++left;
                }
                ++left;
                --zeroNum;
            }
            ans = Math.max(ans, right - left + 1);
            ++right;

        }
        return ans;
    }

    /**
     * 1189. “气球” 的最大数量
     * 给你一个字符串text，你需要使用 text 中的字母来拼凑尽可能多的单词"balloon"（气球）。
     * <p>
     * 字符串text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词"balloon"。
     *
     * @param text
     * @return
     */
    public static int maxNumberOfBalloons(String text) {
        int[] a = {1, 1, 2, 2, 1};
        int[] b = new int[5];
        int length = text.length();
        for (int i = 0; i < length; ++i) {
            if (text.charAt(i) == 'a') {
                b[0]++;
            } else if (text.charAt(i) == 'b') {
                b[1]++;
            } else if (text.charAt(i) == 'l') {
                b[2]++;
            } else if (text.charAt(i) == 'o') {
                b[3]++;
            } else if (text.charAt(i) == 'n') {
                b[4]++;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            ans = Math.min(ans, b[i] / a[i]);
        }
        return ans;
    }

    /**
     * 1218. 最长定差子序列
     * <p>
     * 给你一个整数数组arr和一个整数difference，请你找出并返回 arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
     * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
     */
    public static int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int element : arr) {
            int curr = map.getOrDefault(element - difference, 0) + 1;
            map.put(element, curr);
            ans = Math.max(ans, map.get(element));
        }
        return ans;
    }

    /**
     * 1447. 最简分数
     * 给你一个整数 n ，
     * 请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。
     * 分数可以以 任意 顺序返回
     * <p>
     * 1 <= n <= 100
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(j, i) == 1) {
                    ans.add(i + "/" + j);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }


    /**
     * 1791. 找出星型图的中心节点
     * <p>
     * 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
     */
    public int findCenter(int[][] edges) {
        int length = edges.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            map.put(edge[0], map.getOrDefault(edge[0], 0) + 1);
            map.put(edge[1], map.getOrDefault(edge[1], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == length) {
                ans = key;
            }
        }
        return ans;
    }

    /**
     * 1984. 学生分数的最小差值
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k。
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     * 返回可能的 最小差值 。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int minimumDifference(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i + k <= nums.length; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }

    /**
     * 2006. 差的绝对值为 K 的数对数目
     * 给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。
     * <p>
     * |x|的值定义为：
     * <p>
     * 如果x >= 0，那么值为x。
     * 如果x < 0，那么值为-x。
     */
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    public int countKDifference2(int[] nums, int k) {
        int ans = 0, length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            ans += map.getOrDefault(nums[i] - k, 0) + map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ans;
    }

    /**
     * 917. 仅仅反转字母
     * <p>
     * 给你一个字符串 s ，根据下述规则反转字符串：
     * <p>
     * 所有非英文字母保留在原有位置。
     * 所有英文字母（小写或大写）位置反转。
     * 返回反转后的 s 。
     *
     * @param s
     * @return
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) { // 判断左边是否扫描到字母
                left++;
            }
            while (right > left && !Character.isLetter(s.charAt(right))) { // 判断右边是否扫描到字母
                right--;
            }
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                ++left;
                --right;
            }
        }
        return new String(chars);
    }

    /**
     * 1706. 球会落何处
     *
     * @param grid
     * @return
     */
    public int[] findBall(int[][] grid) {
        int length = grid[0].length;
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            int col = i;
            for (int[] row : grid) {
                int dir = row[col];
                col += dir;  // 移动球
                if (col < 0 || col >= length || dir != row[col]) {
                    col = -1;
                    break;
                }
            }
            ans[i] = col;
        }
        return ans;
    }

    /**
     * 537. 复数乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String complexNumberMultiply(String num1, String num2) {
        String[] complex1 = num1.split("\\+|i");
        String[] complex2 = num2.split("\\+|i");
        int A = Integer.parseInt(complex1[0]);
        int B = Integer.parseInt(complex1[1]);
        int C = Integer.parseInt(complex2[0]);
        int D = Integer.parseInt(complex2[1]);

        return String.format("%d+%di", A * C - B * D, A * D + B * C);
    }

    /**
     * 2016. 增量元素之间的最大差值
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        int ans = -1, minValue = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > minValue) {
                ans = Math.max(ans, nums[i] - minValue);
            } else {
                minValue = nums[i];
            }
        }
        return ans;
    }

    /**
     * 553. 最优除法
     *
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return String.valueOf(nums[0]);
        }
        if (length == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]);
        builder.append("/(");
        for (int i = 1; i < length - 1; i++) {
            builder.append(nums[i]);
            builder.append("/");
        }
        builder.append(nums[length - 1]);
        builder.append(")");
        return builder.toString();
    }

    /**
     * 258. 各位相加
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        while (num >= 10) {
            int curr = num;
            int ans = 0;
            while (curr > 0) {
                ans += curr % 10;
                curr /= 10;
            }
            num = ans;
        }
        return num;
    }

    /**
     * 2104. 子数组范围和
     *
     * @param nums
     * @return
     */
    public long subArrayRanges(int[] nums) {
        int length = nums.length;
        long ans = 0;
        for (int i = 0; i < length; i++) {
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                max = Math.max(nums[j], max);
                min = Math.min(nums[j], min);
                ans += max - min;
            }
        }
        return ans;
    }

    /**
     * 2104. 子数组范围和
     *
     * @param nums
     * @return
     */
    public long subArrayRanges2(int[] nums) {
        int length = nums.length;
        int[] minLeft = new int[length];
        int[] minRight = new int[length];
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();

        for (int i = 0; i < length; i++) {
            while (!minDeque.isEmpty() && nums[minDeque.peek()] > nums[i]) {
                minDeque.pop();
            }
            minLeft[i] = minDeque.isEmpty() ? -1 : minDeque.peek();
            minDeque.push(i);

            while (!maxDeque.isEmpty() && nums[maxDeque.peek()] <= nums[i]) {
                maxDeque.pop();
            }
            maxLeft[i] = maxDeque.isEmpty() ? -1 : maxDeque.peek();
            maxDeque.push(i);
        }

        minDeque.clear();
        maxDeque.clear();

        for (int i = length - 1; i >= 0; i--) {
            while (!minDeque.isEmpty() && nums[minDeque.peek()] >= nums[i]) {
                minDeque.pop();
            }
            minRight[i] = minDeque.isEmpty() ? length : minDeque.peek();
            minDeque.push(i);

            while (!maxDeque.isEmpty() && nums[maxDeque.peek()] < nums[i]) {
                maxDeque.pop();
            }
            maxRight[i] = maxDeque.isEmpty() ? length : maxDeque.peek();
            maxDeque.push(i);
        }

        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < length; i++) {
            sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
            sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
        }
        return sumMax - sumMin;
    }

    /**
     * 2100. 适合打劫银行的日子
     *
     * @param security
     * @param time
     * @return
     */
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 1; i < length; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            if (security[length - 1 - i] <= security[length - i]) {
                right[length - 1 - i] = right[length - i] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 504. 七进制数
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        int curr = num < 0 ? -num : num;
        List<Integer> list = new ArrayList<>();
        while (curr >= 7) {
            list.add(curr % 7);
            curr = curr / 7;
        }
        list.add(curr);
        StringBuilder builder = new StringBuilder();
        if (num < 0) {
            builder.append("-");
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            builder.append(list.get(size - 1 - i));
        }
        return builder.toString();
    }

    /**
     * 590. N 叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        postOrderxxxx(ans, root);
        return ans;
    }

    private void postOrderxxxx(List<Integer> ans, Node root) {
        if (root != null) {
            for (Node child : root.children) {
                postOrderxxxx(ans, child);
            }
            ans.add(root.val);
        }
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public leetcode.TreeNode lowestCommonAncestor(leetcode.TreeNode root, leetcode.TreeNode p, leetcode.TreeNode q) {
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }


    /**
     * 599. 两个列表的最小索引总和
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<Integer, List<String>> map = new HashMap<>();
        int minIndexSum = Integer.MAX_VALUE;
        int length1 = list1.length;
        int length2 = list2.length;
        for (int i = 0; i < length1; i++) {
            String s = list1[i];
            for (int j = 0; j < length2; j++) {
                if (s.equals(list2[j])) {
                    int currIndexSum = i + j;
                    List<String> list = map.getOrDefault(currIndexSum, new ArrayList<String>());
                    list.add(s);
                    map.put(currIndexSum, list);
                    minIndexSum = Math.min(minIndexSum, currIndexSum);
                }
            }
        }
        if (map.containsKey(minIndexSum)) {
            List<String> strings = map.get(minIndexSum);
            return strings.toArray(new String[]{});
        } else {
            return new String[]{};
        }
    }

    public String[] findRestaurant2(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        List<String> ans = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                Integer integer = map.get(list2[i]);
                int curr = integer + i;
                if (curr < minIndexSum) {
                    ans.clear();
                    ans.add(list2[i]);
                    minIndexSum = curr;
                } else if (curr == minIndexSum) {
                    ans.add(list2[i]);
                }
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

    /**
     * 720. 词典中最长的单词
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });
        StringBuilder builder = new StringBuilder("");
        Set<String> set = new HashSet<>();
        set.add("");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                builder.delete(0, builder.length());
                builder.append(word);
            }
        }
        return builder.toString();
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeReverse(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeReverse(int[] preorder, int preStart, int preEnd,
                                      int[] inorder, int inStart, int inEnd) {
        if (preStart == preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                index = i;
                break;
            }
        }
        int leftNum = index - inStart;
        root.left = buildTreeReverse(preorder, preStart + 1, preStart + leftNum + 1, inorder, inStart, index);
        root.right = buildTreeReverse(preorder, preStart + leftNum + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTreeInAndPost(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTreeInAndPost(int[] inorder, int inStart, int inEnd,
                                        int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = 0;
        for (int i = 0; i <= inEnd; i++) {
            if (inorder[i] == postorder[postEnd]) {
                index = i;
                break;
            }
        }
        root.left = buildTreeInAndPost(inorder, 0, index - 1, postorder, postStart, postStart + index - inStart - 1);
        root.right = buildTreeInAndPost(inorder, index + 1, inEnd, postorder, postStart + index - inStart, postEnd);
        return root;
    }

    /**
     * 606. 根据二叉树创建字符串
     *
     * @param root
     * @return
     */
    public String tree2str(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preOrderTree2Str(root, builder);
        return builder.toString().substring(1, builder.length() - 1);
    }

    private void preOrderTree2Str(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right != null) {
            builder.append("(");
            builder.append(root.val);
            builder.append("()");
            preOrderTree2Str(root.right, builder);
            builder.append(")");
        } else {
            builder.append("(");
            builder.append(root.val);
            preOrderTree2Str(root.left, builder);
            preOrderTree2Str(root.right, builder);
            builder.append(")");
        }
    }

    public String tree2str2(TreeNode root) {
        /*遍历*/
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        Set<TreeNode> set = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (set.contains(peek)) {
                // 已经被遍历过
                if (peek != root) {
                    builder.append(")");
                }
                stack.pop();
            } else {
                set.add(peek);
                if (peek != root) {
                    builder.append("(");
                }
                builder.append(peek.val);
                if (peek.left == null && peek.right != null) {
                    builder.append("()");
                }
                // 先进右孩子 是因为需要根据堆先进后出的性质保证先序遍历
                if (peek.right != null) {
                    stack.add(peek.right);
                }
                if (peek.left != null) {
                    stack.add(peek.left);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 2038. 如果相邻两个颜色均相同则删除当前颜色
     *
     * @param colors
     * @return
     */
    public boolean winnerOfGame(String colors) {
        int[] ans = new int[]{0, 0};
        char curr = 'c';
        int num = 0;
        for (int i = 0; i < colors.length(); i++) {
            char c = colors.charAt(i);
            if (c != curr) {
                curr = c;
                num = 1;
            } else {
                ++num;
                if (num >= 3) {
                    ans[c - 'A']++;
                }
            }
        }
        return ans[0] > ans[1];
    }

    /**
     * 661. 图片平滑器
     *
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = 0;
                int plus = 0;
                for (int row = Math.max(0, i - 1); row <= Math.min(i + 1, m - 1); row++) {
                    for (int col = Math.max(0, j - 1); col <= Math.min(n - 1, j + 1); col++) {
                        plus += img[row][col];
                        num++;
                    }
                }
                ans[i][j] = plus / num;
            }
        }
        return ans;
    }

    /**
     * 172. 阶乘后的零
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        if (n == 0) {
            return 0;
        }
        int mul = multiply(n);
        int ans = 0;
        while (mul > 0) {
            if (mul % 10 != 0) {
                break;
            } else {
                ans++;
            }
            mul /= 10;
        }
        return ans;
    }

    private int multiply(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * multiply(n - 1);
        }
    }

    /**
     * 682. 棒球比赛
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            if ("C".equals(op)) {
                list.remove(list.size() - 1);
            } else if ("D".equals(op)) {
                list.add(2 * list.get(list.size() - 1));
            } else if ("+".equals(op)) {
                Integer integer1 = list.get(list.size() - 1);
                Integer integer2 = list.get(list.size() - 2);
                list.add(integer1 + integer2);
            } else {
                list.add(Integer.valueOf(op));
            }
        }

        int ans = 0;
        for (Integer integer : list) {
            ans += integer;
        }
        return ans;
    }

    /**
     * 2028. 找出缺失的观测数据
     *
     * @param rolls
     * @param mean
     * @param n
     * @return
     */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = mean * (n + m);
        for (int roll : rolls) {
            sum -= roll;
        }

        if (sum < n || sum > 6 * n) {
            return new int[0];
        }
        int[] ans = new int[n];
        int chu = sum / n, yu = sum % n;
        for (int i = 0; i < n; i++) {
            ans[i] = chu + (i < yu ? 1 : 0);
        }
        return ans;
    }

    /**
     * 693. 交替位二进制数
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        String s = Integer.toBinaryString(n);
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return false;
            } else {
                c = s.charAt(i);
            }
        }
        return true;
    }


    /**
     * 2024. 考试的最大困扰度
     *
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(LongestAnswer(answerKey, k, 'T'), LongestAnswer(answerKey, k, 'F'));
    }

    private int LongestAnswer(String answerKey, int k, char c) {
        int num = 0;
        int ans = 0;
        for (int left = 0, right = 0; right < answerKey.length(); right++) {
            num += answerKey.charAt(right) != c ? 1 : 0;
            while (num > k) {
                num -= answerKey.charAt(left) != c ? 1 : 0;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * 728. 自除数
     *
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivide(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean isSelfDivide(int num) {
        List<Integer> list = new ArrayList<>();
        int temp = num;
        while (temp > 0) {
            list.add(temp % 10);
            temp /= 10;
        }
        for (int i : list) {
            if (i == 0 || num % i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 954. 二倍数对数组
     *
     * @param arr
     * @return
     */
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : arr) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        if (cnt.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }

        List<Integer> vals = new ArrayList<Integer>();
        for (int x : cnt.keySet()) {
            vals.add(x);
        }
        Collections.sort(vals, (a, b) -> Math.abs(a) - Math.abs(b));

        for (int x : vals) {
            if (cnt.getOrDefault(2 * x, 0) < cnt.get(x)) { // 无法找到足够的 2x 与 x 配对
                return false;
            }
            cnt.put(2 * x, cnt.getOrDefault(2 * x, 0) - cnt.get(x));
        }
        return true;
    }

    /**
     * 796. 旋转字符串
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int length = s.length();
        for (int i = 1; i <= length; i++) {
            if (s.equals(goal)) {
                return true;
            }
            s = s.substring(1) + s.substring(0, 1);
        }
        return false;
    }

    /**
     * 429. N 叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                list.add(poll.val);
                for (Node child : poll.children) {
                    queue.offer(child);
                }
                ans.add(list);
            }
        }
        return ans;
    }

    /**
     * 780. 到达终点
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (sx == tx && sy == ty) {
            return true;
        } else if (sx == tx) {
            return (ty > sy) && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return (tx > sx) && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }

    /**
     * 804. 唯一摩尔斯密码词
     *
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] mouse = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                builder.append(mouse[c - 'a']);
            }
            String string = builder.toString();
            if (!set.contains(string)) {
                set.add(string);
            }
        }
        return set.size();
    }

    /**
     * 806. 写字符串需要的行数
     *
     * @param widths
     * @param s
     * @return
     */
    public int[] numberOfLines(int[] widths, String s) {
        int rows = 1, column = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            column += widths[c - 'a'];
            if (column > 100) {
                column = widths[c - 'a'];
                ++rows;
            }
        }
        return new int[]{rows, column};
    }

    /**
     * 819. 最常见的单词
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> list = Arrays.asList(banned);
        paragraph = paragraph.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (c >= 'a' && c <= 'z') {
                builder.append(c);
            } else {
                String string = builder.toString();
                if (string.length() > 0 && !list.contains(string)) {
                    map.put(string, map.getOrDefault(string, 0) + 1);
                }
                builder.delete(0, builder.length());
            }
        }
        String string = builder.toString();
        if (string.length() > 0 && !list.contains(string)) {
            map.put(string, map.getOrDefault(string, 0) + 1);
        }

        String ans = "";
        int num = 0;
        for (String s : map.keySet()) {
            Integer integer = map.get(s);
            if (integer > num) {
                ans = s;
                num = integer;
            }
        }

        return ans;
    }

    /**
     * 386. 字典序排数
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i = 1; i <= n; i++) {
            ans.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                ++number;
            }
        }
        return ans;
    }

    /**
     * 821. 字符的最短距离
     *
     * @param s
     * @param c
     * @return
     */
    public int[] shortestToChar(String s, char c) {
        int length = s.length();
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == c) {
                ans[i] = 0;
            } else {
                int left = i, right = i;
                while (left >= 0) {
                    if (s.charAt(left) == c) {
                        break;
                    }
                    --left;
                }
                int ans1 = left >= 0 ? Math.abs(left - i) : Integer.MAX_VALUE;
                while (right < length) {
                    if (s.charAt(right) == c) {
                        break;
                    }
                    ++right;
                }
                int ans2 = right < length ? Math.abs(right - i) : Integer.MAX_VALUE;
                ans[i] = Math.min(ans1, ans2);
            }
        }
        return ans;
    }

    /**
     * 824. 山羊拉丁文
     *
     * @param sentence
     * @return
     */
    public String toGoatLatin(String sentence) {
        String[] string = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length; i++) {
            String s = string[i];
            char c = s.charAt(0);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                builder.append(s);
                builder.append("ma");
            } else {
                builder.append(s.substring(1));
                builder.append(c);
                builder.append("ma");
            }
            for (int j = 0; j <= i; j++) {
                builder.append("a");
            }
            if (i != string.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    /**
     * 396. 旋转函数
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        /**
         *         超时
         *         int length = nums.length;
         *         if (length == 1) {
         *             return 0;
         *         }
         *         int ans = Integer.MIN_VALUE;
         *         List<Integer> list = new ArrayList<>();
         *         for (int i : nums) {
         *             list.add(i);
         *         }
         *         for (int i = 1; i <= length; i++) {
         *             int curr = 0;
         *             for (int j = 0; j < length; j++) {
         *                 curr += j * list.get(j);
         *             }
         *             ans = Math.max(ans, curr);
         *             Integer integer = list.remove(length - 1);
         *             list.add(0, integer);
         *         }
         *         return ans;
         */
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int F = 0, sum = 0;
        for (int i = 0; i < length; i++) {
            F += i * nums[i];
            sum += nums[i];
        }
        int ans = F;
        for (int i = 1; i < length; i++) {
            F += sum - length * nums[length - i];
            ans = Math.max(ans, F);
        }
        return ans;
    }

    /**
     * 868. 二进制间距
     *
     * @param n
     * @return
     */
    public int binaryGap(int n) {
        String string = Integer.toBinaryString(n);
        int ans = 0;
        int lastPosition = 0, currPosition = 0;
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) == '1') {
                currPosition = i;
                ans = Math.max(ans, currPosition - lastPosition);
                lastPosition = currPosition;
            }
        }
        return ans;
    }

    /**
     * 905. 按奇偶排序数组
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[right] % 2 == 1) {
                --right;
            }
            while (left < right && nums[left] % 2 == 0) {
                ++left;
            }
            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        return nums;
    }

    /**
     * 908. 最小差值 I
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.max(0, max - min - 2 * k);
    }


    /**
     * 1305. 两棵二叉搜索树中的所有元素
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> list1 = inorderTraversal(root1);
        List<Integer> list2 = inorderTraversal(root2);

        int length1 = list1.size();
        int length2 = list2.size();

        int i = 0, j = 0;
        while (i < length1 && j < length2) {
            if (list1.get(i) <= list2.get(j)) {
                ans.add(list1.get(i));
                ++i;
            } else {
                ans.add(list2.get(j));
                ++j;
            }
        }
        while (i < length1) {
            ans.add(list1.get(i++));
        }
        while (j < length2) {
            ans.add(list2.get(j++));
        }

        return ans;
    }

    /**
     * 1823. 找出游戏的获胜者
     *
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int curr = 0, size = list.size();
        while (size > 1) {
            int index = (curr + k - 1) % size;
            list.remove(index);
            size--;
            curr = index % size;
        }
        return list.get(0);
    }

    /**
     * 713. 乘积小于 K 的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int length = nums.length, ans = 0;
        for (int i = 0; i < length; i++) {
            int curr = 1;
            for (int j = i; j < length; j++) {
                curr *= nums[j];
                if (curr < k) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 433. 最小基因变化
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        String[] gen = {"A", "C", "G", "T"};
        Set<String> visited = new HashSet<>();
        Set<String> bankSet = new HashSet<>();
        Deque<String> deque = new LinkedList<>();
        for (String s : bank) {
            bankSet.add(s);
        }
        deque.offer(start);
        visited.add(start);
        int step = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String poll = deque.poll();
                /* 如果和end相同就返回结果 程序结束 */
                if (poll.equals(end)) {
                    return step;
                }
                /* 从第0位开始替换 枚举所有可能的结果 如果发现与基因库相同就入队 */
                for (int j = 0; j < poll.length(); j++) {
                    StringBuilder builder = new StringBuilder(poll);
                    for (int k = 0; k < gen.length; k++) {
                        String replaced = builder.replace(j, j + 1, gen[k]).toString();
                        /* 该字符串没有尝试过 并且基因库中存在 入队 */
                        if (!visited.contains(replaced) && bankSet.contains(replaced)) {
                            deque.offer(replaced);
                            visited.add(replaced);
                        }
                    }
                }
            }
            ++step;
        }
        return -1;
    }

    /**
     * 442. 数组中重复的数据
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        /**
         *         O(n)的空间复杂度
         *         Map<Integer, Integer> map = new HashMap<>();
         *         for (int num: nums) {
         *             map.put(num, map.getOrDefault(num, 0) + 1);
         *         }
         *         List<Integer> ans = new ArrayList<>();
         *         for (Integer integer : map.keySet()) {
         *             if (map.get(integer) == 2) {
         *                 ans.add(integer);
         *             }
         *         }
         *         return ans;
         */
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            num = Math.abs(num); // 有的元素已经被变成了负数
            if (nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1]; // 第一次遇见
            } else {
                ans.add(num); // 第二次遇见
            }
        }
        return ans;
    }

    /**
     * 942. 增减字符串匹配
     *
     * @param s
     * @return
     */
    public int[] diStringMatch(String s) {
        int length = s.length();
        int left = 0, right = length;
        int[] ans = new int[length + 1];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                ans[i] = left++;
            }
            if (c == 'D') {
                ans[i] = right--;
            }
        }
        ans[length] = left;
        return ans;
    }

    /**
     * 944. 删列造序
     *
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int ans = 0;
        int rows = strs.length;
        int cols = strs[0].length();
        for (int i = 0; i < cols; i++) {
            for (int j = 1; j < rows; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 面试题 01.05. 一次编辑
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int length1 = first.length(), length2 = second.length();
        if (length1 == length2) {
            int place = 0;
            int i = 0, j = 0;
            /* 替换一个字符 */
            while (i < length1) {
                if (first.charAt(i) != second.charAt(j)) {
                    ++place;
                }
                if (place > 1) {
                    return false;
                }
                ++i;
                ++j;
            }
            return true;
        } else {
            /* 插入或者删除一个字符 */
            if (length1 - length2 == 1) {
                return oneInsert(second, first);
            } else if (length1 - length2 == -1) {
                return oneInsert(first, second);
            } else {
                return false;
            }
        }
    }

    private boolean oneInsert(String shortString, String longString) {
        int shortLength = shortString.length(), longLength = longString.length();
        int index1 = 0, index2 = 0;
        while (index1 < shortLength && index2 < longLength) {
            /* 短的字符串只有字符相等的情况下才加1 */
            if (shortString.charAt(index1) == longString.charAt(index2)) {
                ++index1;
            }
            ++index2;
            if (index2 - index1 > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 961. 在长度 2N 的数组中找出重复 N 次的元素
     *
     * @param nums
     * @return
     */
    public int repeatedNTimes(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (map.get(num) == length / 2) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    /**
     * 965. 单值二叉树
     *
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        int value = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val != value) {
                    return false;
                } else {
                    if (node.left != null)
                        queue.add(node.left);
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 467. 环绕字符串中唯一的子字符串
     *
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        int[] arr = new int[26];
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if ((i > 0) && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                ++count;
            } else {
                count = 1;
            }
            arr[p.charAt(i) - 'a'] = Math.max(arr[p.charAt(i) - 'a'], count);
        }
        return Arrays.stream(arr).sum();
    }

    /**
     * 413. 等差数列划分
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        if (length == 1 || length == 2)
            return 0;
        int sub = nums[0] - nums[1], t = 0, ans = 0; // sub表示数组相邻元素差值
        for (int i = 2; i < length; i++) {
            if (nums[i - 1] - nums[i] == sub) {
                ++t; // t表示以num[i]结尾的等差数组的数目
            } else {
                sub = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }

    /**
     * 446. 等差数列划分 II - 子序列
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices2(int[] nums) {
        int length = nums.length;
        int ans = 0;
        if (length < 3)
            return ans;
        Map<Long, Integer>[] dp = new HashMap[length];
        for (int i = 0; i < length; i++)
            dp[i] = new HashMap<>();
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                long d = 1L * nums[i] - nums[j];
                Integer integer = dp[j].getOrDefault(d, 0);
                ans += integer;
                dp[i].put(d, dp[i].getOrDefault(d, 0) + integer + 1);
            }
        }
        return ans;
    }

    /**
     * 468. 验证IP地址
     *
     * @param queryIP
     * @return
     */
    public String validIPAddress(String queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }

        if (isIPv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv6(String queryIP) {
        int sum = 0;
        for (int i = 0; i < queryIP.length(); i++) {
            if (queryIP.charAt(i) == ':')
                ++sum;
        }
        if (sum != 7)
            return false;
        String[] split = queryIP.split(":");
        int length = split.length;
        if (length != 8)
            return false;
        for (int i = 0; i < 8; i++) {
            String s = split[i];
            int size = s.length();
            if (size > 4 || size == 0)
                return false;
            for (int j = 0; j < size; j++) {
                char c = s.charAt(j);
                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')
                        || (c >= 'A' && c <= 'F')))
                    return false;
            }
        }
        return true;
    }

    private boolean isIPv4(String queryIP) {
        int sum = 0;
        for (int i = 0; i < queryIP.length(); i++) {
            if (queryIP.charAt(i) == '.')
                ++sum;
        }
        if (sum != 3) {
            return false;
        }
        String[] split = queryIP.split("\\.");
        int length = split.length;
        if (length != 4)
            return false;
        for (int i = 0; i < 4; i++) {
            String s = split[i];
            if (s.length() > 3 || s.length() == 0)
                return false;
            if (s.length() > 1 && s.charAt(0) == '0')
                return false;
            int curr = 0;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c < '0' || c > '9')
                    return false;
                curr = curr * 10 + (c - '0');
            }
            if (curr > 255)
                return false;
        }
        return true;
    }

    /**
     * 1022. 从根到叶的二进制数之和
     *
     * @param root
     * @return
     */
    private static int ans = 0;

    public static int sumRootToLeaf(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        dfsForSumRootToLeaf(root, builder);
        return ans;
    }

    private static void dfsForSumRootToLeaf(TreeNode root, StringBuilder builder) {
        builder.append(root.val);
        if (root.left == null && root.right == null) {
            // 叶子节点
            ans += Integer.parseInt(builder.toString(), 2);
            System.out.println("build: " + builder);
            return;
        }
        if (root.left != null) {
            dfsForSumRootToLeaf(root.left, builder);
            builder.deleteCharAt(builder.length() - 1);
        }

        if (root.right != null) {
            dfsForSumRootToLeaf(root.right, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 473. 火柴拼正方形
     *
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0)
            return false;
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        int[] edges = new int[4];
        return dfsForMakeSqure(0, matchsticks, edges, sum / 4);
    }

    private boolean dfsForMakeSqure(int index, int[] matchsticks, int[] edges, int ans) {
        if (index == matchsticks.length)
            return true;
        for (int i = 0; i < 4; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= ans && dfsForMakeSqure(index + 1, matchsticks, edges, ans))
                return true;
            edges[i] -= matchsticks[index];
        }
        return false;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val == key) {
            if (root.left == null && root.right == null)
                return null;
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }
            root.right = deleteNode(root.right, node.val);
            node.right = root.right;
            node.left = root.left;
            return node;
        }
        return root;
    }

    /**
     * 829. 连续整数求和
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int bound = 2 * n;
        for (int k = 1; k * (k + 1) <= bound; k++) {
            if (isKConsecutive(n, k)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isKConsecutive(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }

    /**
     * 929. 独特的电子邮件地址
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            StringBuilder builder = new StringBuilder();
            String[] split = email.split("@");
            String s = split[0];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '+')
                    break;
                else if (c == '.')
                    continue;
                else
                    builder.append(c);
            }
            builder.append("@");
            builder.append(split[1]);
            String string = builder.toString();
            if (!set.contains(string)) {
                set.add(string);
            }
        }
        return set.size();
    }

    /**
     * 1037. 有效的回旋镖
     *
     * @param points
     * @return
     */
    public boolean isBoomerang(int[][] points) {
        return (points[1][1] - points[0][1]) * (points[2][0] - points[1][0])
                != (points[2][1] - points[1][1]) * (points[1][0] - points[0][0]);
    }

    /**
     * 1051. 高度检查器
     *
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int length = heights.length;
        int[] copy = Arrays.copyOf(heights, length);
        Arrays.sort(copy);
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (heights[i] != copy[i])
                ++ans;
        }
        return ans;
    }

    /**
     * 532. 数组中的 k-diff 数对
     *
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        int ans = 0, length = nums.length;
        Arrays.sort(nums);
        for (int slow = 0; slow < length; slow++) {
            if (slow == 0 || nums[slow] != nums[slow - 1]) {
                int fast = slow + 1;
                while (fast < length && nums[fast] < nums[slow] + k) {
                    ++fast;
                }
                if (fast < length && nums[fast] == nums[slow] + k)
                    ++ans;
            }
        }
        return ans;
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int ans = haystack.indexOf(needle);
        return ans >= 0 ? ans : -1;
    }

    /**
     * 1408. 数组中的字符串匹配
     *
     * @param words
     * @return
     */
    public List<String> stringMatching(String[] words) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[i].contains(words[j]) && !list.contains(words[j])) {
                    list.add(words[j]);
                }
            }
        }
        return list;
    }

    /**
     * 1422. 分割字符串的最大得分
     *
     * @param s
     * @return
     */
    public int maxScore(String s) {
        int ans = 0;
        int oneTotalNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                oneTotalNum++;
            }
        }
        int leftZeroNum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeroNum++;
            } else {
                oneTotalNum--;
            }
            ans = Math.max(ans, leftZeroNum + oneTotalNum);
        }
        return ans;
    }

    /**
     * 1455. 检查单词是否为句中其他单词的前缀
     *
     * @param sentence
     * @param searchWord
     * @return
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        int length = searchWord.length();
        String[] s = sentence.trim().split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() < length) {
                continue;
            }
            if (s[i].substring(0, length).equals(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 1464. 数组中两元素的最大乘积
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        return (nums[length - 1] - 1) * (nums[length - 2] - 1);
    }

    /**
     * 1582. 二进制矩阵中的特殊位置
     *
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int cnt1 = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    cnt1++;
                }
            }
            if (i == 0) {
                cnt1--;
            }
            if (cnt1 > 0) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1) {
                        mat[0][j] += cnt1;
                    }
                }
            }
        }
        int sum = 0;
        for (int num : mat[0]) {
            if (num == 1) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 1598. 文件夹操作日志搜集器
     */
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    /**
     * 669. 修剪二叉搜索树
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /**
     * 1608. 特殊数组的特征值
     *
     * @param nums
     * @return
     */
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 1624. 两个相同字符之间的最长子字符串
     *
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int ans = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                ans = Math.max(i - map.get(c) - 1, ans);
            } else {
                map.put(c, i);
            }
        }
        return ans;
    }

    /**
     * 1652. 拆炸弹
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        if (k == 0) {
            return new int[n];
        }
        int[] res = new int[n];
        int[] newCode = new int[n * 2];
        System.arraycopy(code, 0, newCode, 0, n);
        System.arraycopy(code, 0, newCode, n, n);
        code = newCode;
        int l = k > 0 ? 1 : n + k;
        int r = k > 0 ? k : n - 1;
        int w = 0;
        for (int i = l; i <= r; i++) {
            w += code[i];
        }
        for (int i = 0; i < n; i++) {
            res[i] = w;
            w -= code[l];
            w += code[r + 1];
            l++;
            r++;
        }
        return res;
    }

    /**
     * 1694. 重新格式化电话号码
     *
     * @param number
     * @return
     */
    public static String reformatNumber(String number) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < number.length(); ++i) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }

        int n = digits.length();
        int pt = 0;
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n > 4) {
                ans.append(digits.substring(pt, pt + 3) + "-");
                pt += 3;
                n -= 3;
            } else {
                if (n == 4) {
                    ans.append(digits.substring(pt, pt + 2) + "-" + digits.substring(pt + 2, pt + 4));
                } else {
                    ans.append(digits.substring(pt, pt + n));
                }
                break;
            }
        }
        return ans.toString();
    }

    /**
     * 1784. 检查二进制字符串字段
     *
     * @param s
     * @return
     */
    public static boolean checkOnesSegment(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ++num;
                if (i > 0 && s.charAt(i - 1) == '1') {
                    --num;
                }
                if (num > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 921. 使括号有效的最少添加
     *
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int ans = 0;
        int leftCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    ans++;
                }
            }
        }
        ans += leftCount;
        return ans;
    }

    /**
     * 1800. 最大升序子数组和
     *
     * @param nums
     * @return
     */
    public int maxAscendingSum(int[] nums) {
        int ans = nums[0];
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                temp += nums[i];
            } else {
                temp = nums[i];
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    /**
     * 1441. 用栈操作构建数组
     *
     * @param target
     * @param n
     * @return
     */
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<String>();
        int prev = 0;
        for (int number : target) {
            for (int i = 0; i < number - prev - 1; i++) {
                res.add("Push");
                res.add("Pop");
            }
            res.add("Push");
            prev = number;
        }
        return res;
    }

    /**
     * 886. 可能的二分法
     *
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0 && !dfs(i, 1, color, g)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int curnode, int nowcolor, int[] color, List<Integer>[] g) {
        color[curnode] = nowcolor;
        for (int nextnode : g[curnode]) {
            if (color[nextnode] != 0 && color[nextnode] == color[curnode]) {
                return false;
            }
            if (color[nextnode] == 0 && !dfs(nextnode, 3 ^ nowcolor, color, g)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1768. 交替合并字符串
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < length1 && index < length2) {
            builder.append(word1.charAt(index));
            builder.append(word2.charAt(index));
            ++index;
        }
        if (index < length1) {
            builder.append(word1.substring(index, length1));
        }
        if (index < length2) {
            builder.append(word2.substring(index, length2));
        }
        return builder.toString();
    }

    /**
     * 1773. 统计匹配检索规则的物品数量
     *
     * @param items
     * @param ruleKey
     * @param ruleValue
     * @return
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int num = 0;
        for (List<String> item : items) {
            if (("type".equals(ruleKey) && item.get(0).equals(ruleValue))
                    || ("color".equals(ruleKey) && item.get(1).equals(ruleValue))
                    || ("name".equals(ruleKey) && item.get(2).equals(ruleValue))) {
                ++num;
            }
        }
        return num;
    }

    /**
     * 784. 字母大小写全排列
     *
     * @param s
     * @return
     */
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        dfsForLetterCasePermutation(s.toCharArray(), 0, ans);
        return ans;
    }

    public void dfsForLetterCasePermutation(char[] arr, int pos, List<String> res) {
        while (pos < arr.length && Character.isDigit(arr[pos])) {
            pos++;
        }
        if (pos == arr.length) {
            res.add(new String(arr));
            return;
        }
        arr[pos] ^= 32;
        dfsForLetterCasePermutation(arr, pos + 1, res);
        arr[pos] ^= 32;
        dfsForLetterCasePermutation(arr, pos + 1, res);
    }

    /**
     * 1678. 设计 Goal 解析器
     */
    public String interpret(String command) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                builder.append("G");
            } else if (command.charAt(i) == '(' && command.charAt(i) == ')') {
                builder.append("o");
                ++i;
            } else {
                builder.append("al");
                i += 3;
            }
        }
        return builder.toString();
    }

    /**
     * 1704. 判断字符串的两半是否相似
     *
     * @param s
     * @return
     */
    public boolean halvesAreAlike(String s) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');
        int length = s.length();
        int num = 0;
        int index = 0;
        for (; index < length / 2; ++index) {
            char c = s.charAt(index);
            if (list.contains(c)) {
                ++num;
            }
        }
        for (; index < length; ++index) {
            char c = s.charAt(index);
            if (list.contains(c)) {
                --num;
            }
        }
        return num == 0;
    }

    /**
     * 1732. 找到最高海拔
     *
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        int length = gain.length;
        int[] height = new int[length + 1];
        height[0] = 0;
        int ans = 0;
        for (int i = 1; i <= length; i++) {
            height[i] = height[i - 1] + gain[i - 1];
            ans = Math.max(ans, height[i]);
        }
        return ans;
    }

    /**
     * 1752. 检查数组是否经排序和轮转得到
     *
     * @param nums
     * @return
     */
    public boolean check(int[] nums) {
        int temp = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                ++temp;
                continue;
            }
            if (nums[i] >= nums[i - 1]) {
                continue;
            } else {
                return false;
            }
        }
        return temp < 1 || (temp == 1 && nums[nums.length - 1] > nums[0]);
    }

    /**
     * 1779. 找到最近的有相同 X 或 Y 坐标的点
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int distance = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < points.length; i++) {
            int temp = Integer.MAX_VALUE;
            if (points[i][0] == x) {
                temp = Math.abs(points[i][1] - y);
            }

            if (points[i][1] == y) {
                temp = Math.abs(points[i][0] - x);
            }

            if (temp < distance) {
                index = i;
                distance = temp;
            }
        }
        return index;
    }


    /**
     * 1796. 字符串中第二大的数字
     *
     * @param s
     * @return
     */
    public int secondHighest(String s) {

        int maxValue = -1;
        int secondValue = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int tempo = c - '0';
                if (tempo > maxValue) {
                    secondValue = maxValue;
                    maxValue = tempo;
                } else if (tempo == maxValue) {
                    continue;
                } else if (tempo > secondValue) {
                    secondValue = tempo;
                }
            }
        }
        return secondValue;
    }

    /**
     * 1827. 最少操作使数组递增
     *
     * @param nums
     * @return
     */
    public static int minOperations(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                continue;
            }
            int temp = Math.abs(nums[i] - nums[i - 1]) + 1;
            ans += temp;
            nums[i] = nums[i - 1] + 1;
        }
        return ans;
    }

    /**
     * 1739. 放置盒子
     *
     * @param n
     * @return
     */
    public int minimumBoxes(int n) {
        int cur = 1, i = 1, j = 1;
        while (n > cur) {
            n -= cur;
            i++;
            cur += i;
        }
        cur = 1;
        while (n > cur) {
            n -= cur;
            j++;
            cur++;
        }
        return (i - 1) * i / 2 + j;
    }

    /**
     * 2351. 第一个出现两次的字母
     *
     * @param s
     * @return
     */
    public char repeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        char c = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (set.contains(s.charAt(i))) {
                c = s.charAt(i);
                break;
            } else {
                set.add(s.charAt(i));
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2, 4, 1};
        System.out.println(minOperations(nums));
    }
}
