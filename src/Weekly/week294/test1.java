package Weekly.week294;

/**
 * @Author LiuYunJie
 * @Date 2022/6/2 20:12
 **/

/**
 * 字母在字符串中的百分比
 * <p>
 * 给你一个字符串 s 和一个字符 letter ，返回在 s 中等于 letter 字符所占的 百分比 ，向下取整到最接近的百分比。
 */
public class test1 {

    int percentageLetter(String s, char letter) {
        int denominator = s.length(); // 分母
        int molecule = 0; // 分子
        for (int i = 0; i < denominator; i++) {
            if (s.charAt(i) == letter)
                ++molecule;
        }
        return molecule * 100 / denominator;
    }

    public static void main(String[] args) {
        System.out.println(123);
    }

}
