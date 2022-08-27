package Weekly.week307;

/**
 * @Author LiuYunJie
 * @Date 2022/8/27 10:47
 **/
public class test1 {

    /**
     * 2383. 赢得比赛需要的最少训练时长
     *
     * @param initialEnergy
     * @param initialExperience
     * @param energy
     * @param experience
     * @return
     */
    public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        int length = energy.length;
        int totalEnergy = 0;
        for (int i = 0; i < length; i++) {
            totalEnergy += energy[i];
            ans += initialExperience > experience[i] ? 0 :
                    experience[i] - initialExperience + 1;
            initialExperience += experience[i];
        }

        if (totalEnergy - initialEnergy >= 0) {
            return ans + totalEnergy - initialEnergy + 1;
        } else {
            return ans;
        }
    }

    public static void main(String[] args) {
        int initialEnergy = 5, initialExperience = 3;
        int[] energy = new int[]{1,5};
        int[] experience = new int[]{2,5};
        System.out.println(minNumberOfHours(initialEnergy, initialExperience, energy, experience));
    }

}
