package indianaJones;

import branchAndBound.BranchAndBound;
import dynamicProgramming.DynamicProgramming;
import java.util.Vector;

/**
 *
 * @author Krisi
 */
public class IndianaJones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] strr = {
            "chocolate 24 12",
            "gold-Horn 13 7",
            "Skyrim 23 11",
            "Skyrim1 15 8",
            "Skyrim2 16 9"
        };
        
        String[] str = {"chocolate 10 2",
            "gold-Horn 100 10",
            "Skyrim 99999 5",
            "pen 1 1"};
        Vector<Item> items = new Vector<>();
        for (String str1 : strr) {
            items.add(new Item(str1));
        }

        int maxWeight = 26;
        Knapsack sack1 = new DynamicProgramming(items, maxWeight);
        System.out.println("DynamicProgramming");
        System.out.println("You shoud take:\t" + sack1.getBestSolution().toString());
        System.out.println("");
        sack1.displayMoreInfo();

        System.out.println("\n\n\nBranchAndBound:");
        Knapsack sack2 = new BranchAndBound(items, maxWeight);
        System.out.println("You shoud take:\t" + sack2.getBestSolution().toString());
        System.out.println("");
        sack2.displayMoreInfo();

    }

}
