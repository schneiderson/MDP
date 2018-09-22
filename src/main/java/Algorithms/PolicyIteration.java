package Algorithms;

import MDP.MDP;
import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;

public class PolicyIteration {

    public int startIteration(MDP mdp){
        mdp.initRandomPolicy();

        boolean changed;
        int iteration = 0;

        do{
            // calculate utility values of all states given policy
            SimpleMatrix rewards = new SimpleMatrix(mdp.numberOfStates(), 1);
            SimpleMatrix utilityWeights = new SimpleMatrix(mdp.numberOfStates(), mdp.numberOfStates());

            for(int s = 0; s < mdp.numberOfStates(); s++) {

                if (s != 4) { // skip terminal state
                    int action = mdp.getAction(s);
                    int reward = mdp.getReward(s, action);
                    rewards.set(s, 0, reward);

                    // correct action
                    int s_p = mdp.getTransition(s, action);
                    if(s_p > 0){
                        double factor = action == mdp.getAction(s) ? 1 - mdp.getMistakeRate() : mdp.getMistakeRate();
                        double utilityWeight = mdp.getDiscountRate() * (1 - mdp.getMistakeRate()) * -1;
                        utilityWeights.set(s, s_p - 1, utilityWeight);
                    }

                    // mistake action
                    int s_pp = mdp.getTransition(s, action);
                    if(s_pp > 0){
                        double utilityWeight = mdp.getDiscountRate() * mdp.getMistakeRate() * -1;
                        utilityWeights.set(s, s_pp - 1, utilityWeight);
                    }

                }
                utilityWeights.set(s, s, 1.0);
            }

            SimpleMatrix utilities;
            try {
                utilities = utilityWeights.solve(rewards);
            } catch ( SingularMatrixException e ) {
                throw new IllegalArgumentException("Singular matrix");
            }

            for (int s = 0; s < mdp.numberOfStates(); s++) {
                mdp.setUtility(s, utilities.get(s,0));
            }

            // check for action yielding higher utility
            changed = false;
            for(int s = 0; s < mdp.numberOfStates(); s++){
                if(s != 4) { // skip terminal state
                    ArrayList<Integer> validActions = mdp.getValidActions(s);

                    double maxUtility = mdp.getUtility(s);
                    int betterAction = mdp.getAction(s);

                    for (int a: validActions) {
                        int mistakeAction = mdp.getMistakeAction(s, a);
                        double utility = mdp.getReward(s, a) + mdp.getDiscountRate() * (
                                (1 - mdp.getMistakeRate()) * mdp.getUtility(mdp.getTransition(s, a) - 1)
                                        + mdp.getMistakeRate() * mdp.getUtility(mdp.getTransition(s, mistakeAction) - 1));
                        if (utility > maxUtility) {
                            maxUtility = utility;
                            betterAction = a;
                        }

                    }
                    if(betterAction != mdp.getAction(s)){
                        mdp.setAction(s, betterAction);
                        changed = true;     // if a better action was found, continue iteration, otherwise stop
                    }
                }
            }
            iteration++;

        }while (changed);

        return iteration;
    }
}
