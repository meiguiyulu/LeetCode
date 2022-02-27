package leetcode;

public class ParkingSystem {

    private int big;
    private int medium;
    private int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.small = small;
        this.medium = medium;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
            if (big >= 1) {
                --big;
                return true;
            } else {
                return false;
            }
        }
        if (carType == 2) {
            if (medium >= 1) {
                --medium;
                return true;
            } else {
                return false;
            }
        }
        if (carType == 3) {
            if (small >= 1) {
                --small;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
