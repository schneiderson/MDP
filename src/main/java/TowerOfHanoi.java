public class TowerOfHanoi implements MDP{

    private int[][] transitionTable = new int[][]{
            {0, 0, 0, 0, 7, 8},
            {0, 9, 11, 0, 0, 0},
            {0, 0, 0, 9, 0, 10},
            {7, 0, 12, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {8, 10, 0, 0, 0, 0},
            {0, 4, 12, 1, 0, 8},
            {0, 10, 6, 1, 7, 0},
            {2, 0, 11, 0, 3, 10},
            {8, 0, 6, 9, 3, 0},
            {2, 9, 0, 0, 12, 5},
            {7, 4, 0, 11, 0, 5}};

    private int[][] rewardTable = new int[][]{
            {0, 0, 0, 0, -1, -1},
            {0, -1, -1, 0, 0, 0},
            {0, 0, 0, -1, 0, -1},
            {-1, 0, -1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {-1, -1, 0, 0, 0, 0},
            {0, -11, -1, -1, 0, -1},
            {0, -1, -11, -1, -1, 0},
            {-11, 0, -1, 0, -1, -1},
            {-1, 0, -11, -1, -1, 0},
            {-11, -1, 0, 0, -1, 100},
            {-1, -11, 0, -1, 0, 100}};

    private int[] actions = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private double[] utilities = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private double discountRate = 0.9;
    private double mistakeRate = 0.1;

    public int numberOfActions(){
        return transitionTable[0].length;
    }

    public int numberOfStates(){
        return transitionTable.length;
    }

    public int getTransition(int state, int action){
        return transitionTable[state][action];
    }

    public int getReward(int state, int action){
        return rewardTable[state][action];
    }

    public int[] getActions() {
        return actions;
    }

    public int getAction(int state){
        return actions[state];
    }

    public double[] getUtilities() {
        return utilities;
    }

    public double getUtility(int state){
        return utilities[state];
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getMistakeRate() {
        return mistakeRate;
    }

    public void setUtility(int state, double utility) {
        utilities[state] = utility;
    }

    public void setAction(int state, int action){
        actions[state] = action;
    }

}
