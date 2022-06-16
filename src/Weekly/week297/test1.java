package Weekly.week297;

/**
 * @Author LiuYunJie
 * @Date 2022/6/15 17:47
 **/

/**
 * 2303. 计算应缴税款总额
 */
public class test1 {

    public static double calculateTax(int[][] brackets, int income) {
        double ans = 0;
        int lastUpper = 0;
        for (int[] bracket : brackets) {
            if (income == 0) break;
            int upper = bracket[0], percent = bracket[1], price = upper - lastUpper;
            if (price <= income) {
                ans += 1.0 * price * percent / 100;
                income -= price;
            } else {
                ans += 1.0 * income * percent / 100;
                income = 0;
            }
            lastUpper = upper;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] brackets = new int[][]{{3, 50}, {7, 10}, {12, 25}};
        int income = 10;
        System.out.println(calculateTax(brackets, income));
    }
}
