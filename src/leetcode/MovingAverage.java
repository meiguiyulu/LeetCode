package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LiuYunJie
 * @Date 2022/7/16 13:50
 **/
public class MovingAverage {

    private int size = 0;
    private double sum = 0.0;
    private int nowSize = 0;
    private int left = 0;
    private List<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.size = size;
        list = new ArrayList<>();
    }

    public double next(int val) {
        list.add(val);
        sum += val;
        if (nowSize == size) {
            sum -= list.get(left++);
        } else {
            ++nowSize;
        }
        return sum / nowSize;
    }

}
