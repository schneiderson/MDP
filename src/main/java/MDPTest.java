import Algorithms.PolicyIteration;
import Algorithms.ValueIteration;
import MDP.MDP;
import MDP.TowerOfHanoi;

import java.util.ArrayList;

public class MDPTest {

    public static void main(String[] args){

        /* VALUE ITERATION */

        ValueIteration myVIter = new ValueIteration(0.001);
        MDP mdp = new TowerOfHanoi();
        int iterationsVi = myVIter.startIteration(mdp);

        int[] bestActionsVI = mdp.getActions();
        double[] bestUtilitiesVI = mdp.getUtilities();
        ArrayList<double[]> allUtilitiesVI = myVIter.getUtilities();


        /* POLICY ITERATION */

        PolicyIteration polIt = new PolicyIteration();
        mdp = new TowerOfHanoi();
        int iterationsPi = polIt.startIteration(mdp);

        int[] bestActionsPI = mdp.getActions();
        double[] bestUtilitiesPI = mdp.getUtilities();
        ArrayList<double[]> allUtilitiesPI = myVIter.getUtilities();



        /* PRINT RESULTS */

        System.out.println("\n~~~~~~ITERATIONS~~~~~~~~\n");
        System.out.println("----Value Iteration----\n");
        for(int i = 0; i < allUtilitiesVI.size(); i++){
            System.out.println("\n - Utilities Iteration " + i);
            for(int j = 0; j < allUtilitiesVI.get(i).length; j++){
                System.out.println("State " + j + ": " + allUtilitiesVI.get(i)[j]);
            }
        }

        System.out.println("\n----Policy Iteration----\n");
        for(int i = 0; i < allUtilitiesPI.size(); i++){
            System.out.println("\n - Utilities Iteration " + i);
            for(int j = 0; j < allUtilitiesPI.get(i).length; j++){
                System.out.println("State " + j + ": " + allUtilitiesPI.get(i)[j]);
            }
        }


        System.out.println("\n\n-----------------------");
        System.out.println("\n~~~~   RESULTS   ~~~~~~\n");
        System.out.println("-----------------------\n");

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
