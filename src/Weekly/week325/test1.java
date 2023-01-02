package Weekly.week325;

/**
 * @Author LiuYunJie
 * @Date 2023/1/2 15:54
 **/
public class test1 {

    /**
     * 2515. 到目标字符串的最短距离L
     *
     * @param words
     * @param target
     * @param startIndex
     * @return
     */
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for (int step = 0, l = startIndex, r = startIndex; step < n; l = (--l + n) % n, r = ++r % n, step++)
            if (words[l].equals(target) || words[r].equals(target))
                return step;
        return -1;
    }

}
