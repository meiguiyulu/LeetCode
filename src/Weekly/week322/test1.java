package Weekly.week322;

/**
 * @Author LiuYunJie
 * @Date 2022/12/9 22:56
 **/
public class test1 {

    /**
     * 2490. 回环句
     * @param sentence
     * @return
     */
    public boolean isCircularSentence(String sentence) {
        String[] s = sentence.trim().split(" ");
        int length = s.length;
        for (int i =0;i<length-1;i++) {
            String s1 = s[i];
            String s2 = s[i + 1];
            if (s1.charAt(s1.length() - 1) != s2.charAt(0)) {
                return false;
            }
        }
        if (s[length-1].charAt(s[length - 1].length() - 1) != s[0].charAt(0)) {
            return false;
        }
        return true;
    }

}
