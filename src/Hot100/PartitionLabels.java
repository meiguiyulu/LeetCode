package Hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionLabels {

    /**
     * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，
     * 但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
     * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     * 返回一个表示每个字符串片段的长度的列表
     */
    public List<Integer> partitionLabels(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, chars[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}
