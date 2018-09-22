import Algorithms.PolicyIteration;
import Algorithms.ValueIteration;
import MDP.MDP;
import MDP.TowerOfHanoi;

public class MDPTest {

    public static void main(String[] args){
        System.out.println("-----------------------");
        System.out.println("----Value Iteration----");
        System.out.println("-----------------------");

        ValueIteration myVIter = new ValueIteration(0.001);
        MDP mdp = new TowerOfHanoi();
        int iterations = myVIter.startIteration(mdp);

        System.out.println("Iterations: " + iterations);

        int[] bestActions = mdp.getActions();
        double[] bestUtilities = mdp.getUtilities();

        System.out.println("Result");
        for(int i = 0; i < bestActions.length; i++){
            System.out.println("State: " + i + " ---  action: " + bestActions[i] + " ---  utility: " + bestUtilities[i] );
        }

        System.out.println("------------------------");
        System.out.println("----Policy Iteration----");
        System.out.println("------------------------");

        PolicyIteration polIt = new PolicyIteration();
        mdp = new TowerOfHanoi();
        iterations = polIt.startIteration(mdp);

        System.out.println("Iterations: " + iterations);

        bestActions = mdp.getActions();
        bestUtilities = mdp.getUtilities();

        System.out.println("Result");
        for(int i = 0; i < bestActions.length; i++){
            System.out.println("State: " + i + " ---  action: " + bestActions[i] + " ---  utility: " + bestUtilities[i] );
        }




    }
}
