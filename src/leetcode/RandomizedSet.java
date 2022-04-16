package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {

    List<Integer> array;
    Map<Integer, Integer> map;

    public RandomizedSet() {
        array = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        int size = array.size();
        map.put(val, size);
        array.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        /**
         * 这段代码的意思是将array最后一个元素放到原先val存在的地方
         * 这样只需要删除array最后一个位置的元素并且修改map中相应的下标值即可
         */
        int index = map.get(val);
        int lastElement = array.get(array.size() - 1);
        array.set(index, lastElement);
        array.remove(array.size() - 1);
        map.put(lastElement, index);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        double random = Math.random();
        return array.get((int) (random * array.size()));
    }


    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        System.out.println(set.insert(1));
        System.out.println(set.remove(2));
        System.out.println(set.insert(2));
        System.out.println(set.getRandom());
        System.out.println(set.remove(1));
        System.out.println(set.insert(2));
        System.out.println(set);
        System.out.println(set.getRandom());
    }
}
