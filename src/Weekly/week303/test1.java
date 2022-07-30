package Weekly.week303;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2022/7/30 13:11
 **/
public class test1 {

    /**
     * 2351. 第一个出现两次的字母
     * <p>
     * 给你一个由小写英文字母组成的字符串 s ，请你找出并返回第一个出现 两次 的字母。
     * 注意：
     * 如果 a 的 第二次 出现比 b 的 第二次 出现在字符串中的位置更靠前，则认为字母 a 在字母 b 之前出现两次。
     * s 包含至少一个出现两次的字母。
     *
     * @param s
     * @return
     */
    public char repeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        int index = 0;
        for (; index < s.length(); index++) {
            if (set.contains(s.charAt(index))) {
                break;
            } else {
                set.add(s.charAt(index));
            }
        }
        return s.charAt(index);
    }

}
