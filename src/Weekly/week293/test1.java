package Weekly.week293;

/**
 * @Author LiuYunJie
 * @Date 2022/5/24 14:35
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2273. 移除字母异位词后的结果数组
 */
public class test1 {

    public static List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        String last = "";
        for (int i=0;i<words.length;i++) {
            char[] chars = words[i].toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (!s.equals(last)) {
                ans.add(words[i]);
                last = s;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"abba","baba","bbaa","cd","cd"};
        System.out.println(removeAnagrams(strings));
    }

}
