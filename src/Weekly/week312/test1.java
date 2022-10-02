package Weekly.week312;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author LiuYunJie
 * @Date 2022/10/2 16:20
 **/
public class test1 {

    /**
     * 2418. 按身高排序
     *
     * @param names
     * @param heights
     * @return
     */
    public String[] sortPeople(String[] names, int[] heights) {
        int length = names.length;
        Map<Integer, String> map = new HashMap<>();
        Integer[] temp = new Integer[length];
        for (int i = 0; i < length; i++) {
            map.put(heights[i], names[i]);
            temp[i] = heights[i];
        }

        Arrays.sort(temp, ((o1, o2) -> o2 - o1));

        String[] ans = new String[length];
        for (int i = 0; i < length; i++) {
            ans[i] = map.get(temp[i]);
        }
        return ans;
    }

}
