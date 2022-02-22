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
        while (i >= 0 && nums[i] > nums[i + 1]) {
            --i;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >=0 && nums[j] <= nums[i]) {
                --j;
            }
            Swap(nums, i, j);
        }
        ReverseArr(nums, i+1);
    }

    private void ReverseArr(int[] nums, int i) {
        int right = nums.length - 1;;
        while (i <= right) {
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


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));
        new ShuZu().fourSum(new int[]{2, 2, 2, 2, 2}, 8);
        new ShuZu().removeDuplicates(new int[]{1, 1, 2});
        new ShuZu().removeElement(new int[]{1}, 1);
    }
}
