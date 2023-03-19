package Weekly.week336;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2023/3/19 10:24
 **/
public class test1 {

    /**
     * 2586. 统计范围内的元音字符串数
     *
     * @param words
     * @param left
     * @param right
     * @return
     */
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int ans = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }
}
