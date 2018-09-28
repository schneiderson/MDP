package Algorithms;

import MDP.MDP;

import java.util.ArrayList;

public class ValueIteration {

    private double precision;
    public ArrayList<double[]> utilities = new ArrayList<double[]>();
    public ArrayList<int[]> actions = new ArrayList<int[]>();

    public ValueIteration(double precision) {
        if (precision <= 0) {
            this.precision = 0.001;
            System.out.println("Invalid parameter. The number of iterations have been set to the default value.");
        } else {
            this.precision = precision;
        }
    }

    public int startIteration(MDP mdp) {
        double dirac;
        double newValue;
        double max;
        int iteration = 0;
        do {
            dirac = 0;
            for (int state = 0; state < 12; state++) {
                if (state != 4) //end state
                {
                    max = -Double.MAX_VALUE;
                    for (int action = 0; action < 6; action++) {
                        if (mdp.getTransition(state, action) != 0) // valid action in the current state
                        {
                            newValue = mdp.calcUtility(state, action);
                            max = Math.max(newValue, max);
                        }
                    }
                    dirac = Math.max(Math.abs(max - mdp.getUtility(state)), dirac);
                    mdp.setUtility(state, max);
                }
            }
            utilities.add(iteration, mdp.getUtilities().clone());

            for (int state = 0; state < 12; state++) {
                int bestAction = -1;
                max = -Double.MAX_VALUE;
                for (int action = 0; action < 6; action++) {
                    if (mdp.getTransition(state, action) != 0) // valid action in the current state
                    {
                        newValue = mdp.calcUtility(state, action);
                        if (newValue > max) {
                            max = newValue;
                            bestAction = action;
                        }
                    }
                }
                mdp.setAction(state, bestAction);
            }

            actions.add(iteration, mdp.getActions().clone());
            iteration++;
        } while (dirac > precision);

        return iteration;
    }

    public ArrayList<double[]> getUtilities() {
        return utilities;
    }
}