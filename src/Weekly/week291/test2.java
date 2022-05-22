package Weekly.week291;

/**
 * @Author LiuYunjie
 * @Date 2022/5/21 20:28
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 必须拿起的最小连续卡牌数
 * 给你一个整数数组 cards ，其中 cards[i] 表示第 i 张卡牌的 值 。如果两张卡牌的值相同，则认为这一对卡牌 匹配 。
 * <p>
 * 返回你必须拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。如果无法得到一对匹配的卡牌，返回 -1 。
 */
public class test2 {
    public static void main(String[] args) {
        int[] cards1 = new int[]{3, 4, 2, 3, 4, 7};
        int[] cards2 = new int[]{1, 0, 5, 3};
        System.out.println(minimumCardPickup(cards1));
        System.out.println(minimumCardPickup(cards2));
    }

    public static int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            int card = cards[i];
            if (map.containsKey(card)) {
                Integer integer = map.get(card);
                ans = Math.min(ans, i - integer + 1);
            }
            map.put(card, i);
        }

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        return ans;
    }

}
