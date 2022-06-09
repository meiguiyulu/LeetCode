package Weekly.week295;

/**
 * @Author LiuYunJie
 * @Date 2022/6/9 19:45
 **/

import java.util.Scanner;

/**
 * 价格减免
 */
public class test2 {

    public static String discountPrices(String sentence, int discount) {
        double price = discount / 100.0;
        StringBuilder builder = new StringBuilder();
        String[] s = sentence.split(" ");
        for (String s1 : s) {
            if (s1.lastIndexOf('$') == 0) {
                builder.append('$');
                String substring = s1.substring(1);
                Double aDouble = Double.valueOf(substring) * (1 - price);
                String format = String.format("%.2f", aDouble);
                builder.append(format);
            } else {
                builder.append(s1);
            }
            builder.append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        int discount = sc.nextInt();
        System.out.println(discountPrices(next, discount));
    }

}
