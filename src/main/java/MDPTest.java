import Algorithms.PolicyIteration;
import Algorithms.ValueIteration;
import MDP.MDP;
import MDP.TowerOfHanoi;

import java.util.ArrayList;

public class MDPTest {

    public static void main(String[] args){


        /**********************
        * * VALUE ITERATION * *
        ***********************/

        double precision = 0.001;                                       // set precision
        ValueIteration myVIter = new ValueIteration(precision);         // create value iteration instance
        MDP mdp = new TowerOfHanoi();                                   // create tower of hanoi instance
        int iterationsVi = myVIter.startIteration(mdp);                 // start value iteration

        int[] bestActionsVI = mdp.getActions();                         // get final policy
        double[] bestUtilitiesVI = mdp.getUtilities();                  // get final utilities
        ArrayList<double[]> allUtilitiesVI = myVIter.getUtilities();    // get utilities from each iteration
        ArrayList<int[]> allActionsVI = myVIter.actions;                // get actions selected in each iteration



        /***********************
        * * POLICY ITERATION * *
        ************************/

        PolicyIteration polIt = new PolicyIteration();                  // create policy iteration instance
        mdp = new TowerOfHanoi();                                       // create tower of hanoi instance
        int iterationsPi = polIt.startIteration(mdp);                   // start policy iteration

        int[] bestActionsPI = mdp.getActions();                         // get final policy
        double[] bestUtilitiesPI = mdp.getUtilities();                  // get final utilities
        ArrayList<double[]> allUtilitiesPI = polIt.getUtilities();      // get utilities from each iteration
        ArrayList<int[]> allActionsPI = polIt.actions;                  // get actions selected in each iteration




        /***********************
         * *  PRINT RESULTS  * *
         ************************/

        System.out.println("\n~~~~~~ITERATIONS~~~~~~~~\n");
        System.out.println("----Value Iteration----\n");
        for(int i = 0; i < allUtilitiesVI.size(); i++){
            System.out.println("\n - Utilities Iteration " + i);
            for(int j = 0; j < allUtilitiesVI.get(i).length; j++){
            	System.out.println("State: " + j + "\t action: " + allActionsVI.get(i)[j] + "\t utility: " + allUtilitiesVI.get(i)[j] );
            }
        }

        System.out.println("\n----Policy Iteration----\n");
        for(int i = 0; i < allUtilitiesPI.size(); i++){
            System.out.println("\n - Utilities Iteration " + i);
            for(int j = 0; j < allUtilitiesPI.get(i).length; j++){
                System.out.println("State: " + j + "\t action: " + allActionsPI.get(i)[j] + "\t utility: " + + allUtilitiesPI.get(i)[j]);
            }
        }


        System.out.println("\n\n---------------------------");
        System.out.println("\n~~~~  FINAL RESULTS   ~~~~~~\n");
        System.out.println("----------------------------\n");

        System.out.println("\n--- Value Iteration ---\n");

        System.out.println("Iterations: " + iterationsVi + "\n");

        System.out.println("Result");
        for(int i = 0; i < bestActionsVI.length; i++){
            System.out.println("State: " + i + "\t action: " + bestActionsVI[i] + "\t utility: " + bestUtilitiesVI[i] );
        }



        System.out.println("\n--- Policy Iteration ---\n");

        System.out.println("Iterations: " + iterationsPi + "\n");

        for(int i = 0; i < bestActionsPI.length; i++){
            System.out.println("State: " + i + "\t action: " + bestActionsPI[i] + "\t  utility: " + bestUtilitiesPI[i] );
        }


    }
}
