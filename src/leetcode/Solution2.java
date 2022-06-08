package leetcode;

import java.util.Random;

/**
 * @Author LiuYunJie
 * @Date 2022/6/5 15:41
 **/
public class Solution2 {

    private double pointX;
    private double pointY;
    private double radius;
    private Random random;

    public Solution2(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.pointX = x_center;
        this.pointY = y_center;
        random = new Random();
    }

    public double[] randPoint() {
        double x = random.nextDouble(pointX - radius, pointX + radius);
        double absX = Math.abs(x - pointX);
        double Y2 = radius * radius - absX * absX;
        int nextInt = random.nextInt(1);
        double y = 0;
        if (nextInt == 0) {
            y = pointY + Math.sqrt(Y2);
        } else {
            y = pointY - Math.sqrt(Y2);
        }
        return new double[]{x, y};
    }

}
