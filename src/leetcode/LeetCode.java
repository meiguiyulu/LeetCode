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

    class TreeNode {
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
        if (k == 0)
            return head;
        else {
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
            if (obstacleGrid[i][0] == 1)
                dp[i][0] = 0;
            else
                dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                dp[0][j] = 0;
            else
                dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
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
        if (x==0 || x==1)
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
     * 求二叉树的最大深度
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth (TreeNode root) {
        // write code here
        if (root == null)
            return 0;
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ++ans;
            for (int i=0;i<size;i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null)
                    queue.offer(poll.left);

                if (poll.right != null)
                    queue.offer(poll.right);
            }
        }
        return ans;
    }

    public int maxDepth2 (TreeNode root) {
        if (root==null)
            return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right))+1;
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


    public static void main(String[] args) {
        int[] nums = new int[]{0};
        System.out.println(canJump(nums));
    }

}






