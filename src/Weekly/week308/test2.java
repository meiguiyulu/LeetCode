package Weekly.week308;

import java.util.Stack;

/**
 * @Author LiuYunJie
 * @Date 2022/9/2 21:02
 **/
public class test2 {

    /**
     * 2390. 从字符串中移除星号
     *
     * @param s
     * @return
     */
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

}
