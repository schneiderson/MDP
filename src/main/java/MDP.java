import java.util.ArrayList;
import java.util.Random;

public interface MDP {

    /* Getter */
    int[] getActions();

    double[] getUtilities();

    double getDiscountRate();

    double getMistakeRate();

    int getTransition(int state, int action);

    int getReward(int state, int action);

    double getUtility(int state);

    int getAction(int state);

    int numberOfActions();

    int numberOfStates();

    /* Setter */
    void setUtility(int state, double utility);

    void setAction(int state, int action);

    /* Default Methods */

    default double calcUtility(int state, int action) {
        int mistakeAction = getMistakeAction(state, action);
        int nextState = getTransition(state, action) - 1;
        int nextMistakeState = getTransition(state, mistakeAction) - 1;
        double futureReward = getDiscountRate() * getUtility(nextState) + getMistakeRate() * getUtility(nextMistakeState);
        return calcReward(state, action) + getDiscountRate() * futureReward;
    }

    default double calcReward(int state, int action) {
        int mistakeAction = getMistakeAction(state, action);
        return (1 - getMistakeRate()) * getReward(state, action) + getMistakeRate() * getReward(state, mistakeAction);
    }

    default int getMistakeAction(int state, int action) {
        int offset;
        offset = action < 3 ? 0 : 3;
        int result = 0;
        for (int i = offset; i < offset + 3; i++) if (i != action && getTransition(state, i) != 0) result = i;
        return result;
    }

    default ArrayList<Integer> getValidActions(int state) {
        ArrayList<Integer> validActions = new ArrayList<>();
        for (int a = 0; a < numberOfActions(); a++) {
            if (getTransition(state, a) != 0) {
                validActions.add(a);
            }
        }
        return validActions;
    }

    default void initRandomPolicy() {
        for (int s = 0; s < numberOfStates(); s++) {
            ArrayList<Integer> validActions = getValidActions(s);
            if(validActions.size() > 0){
                setAction(s, validActions.get(new Random().nextInt(validActions.size())));
            } else {
                setAction(s, -1);
            }
        }
    }
}
