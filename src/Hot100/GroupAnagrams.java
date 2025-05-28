package Hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] counts = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                counts[strs[i].charAt(j) - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (counts[j] > 0) {
                    builder.append('a' + j);
                    builder.append(counts[j]);
                }
            }
            List<Integer> orDefault = resultMap.getOrDefault(builder.toString(), new ArrayList<>());
            orDefault.add(i);
            resultMap.put(builder.toString(), orDefault);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> value : resultMap.values()) {
            List<String> list = new ArrayList<>();
            value.forEach(num -> list.add(strs[num]));
            ans.add(list);
        }
        return ans;
    }

}
