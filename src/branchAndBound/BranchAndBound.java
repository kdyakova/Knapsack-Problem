/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package branchAndBound;

import indianaJones.Item;
import indianaJones.Knapsack;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Krisi
 */
public class BranchAndBound implements Knapsack {

    private int maxWeight;
    private Vector<Item> items;
    
    
    private int maxValueThatYouCanGet;
    private List<Boolean> bestPath;
    private LinkedList<Item> itemsToTake;

    public BranchAndBound(Vector<Item> items, int maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;
        bestPath = new LinkedList<>();
        itemsToTake = new LinkedList<>();
        maxValueThatYouCanGet = 0;
        items.sort(null);
//        Collections.sort(items);
    }

    @Override
    public LinkedList<Item> getBestSolution() {
        findPath();
        makeListOfItemsToTake();
        return itemsToTake;
    }

    private void findPath() {
        Vector<Node> activeNodes = new Vector<>();
        Vector<Node> leafs = new Vector<>();

        Node root = new Node(items, maxWeight);

        activeNodes.add(root);
        while (true) {

            newBranch(root, true, activeNodes, leafs);
            newBranch(root, false, activeNodes, leafs);

            activeNodes.remove(root);

            activeNodes.sort(null);
            leafs.sort(null);

            if (!leafs.isEmpty()) {
                if (activeNodes.isEmpty()) {
                    break;
                }
                if (activeNodes.lastElement().compareTo(leafs.lastElement()) == -1) {
                    break;
                }
            }
            root = activeNodes.lastElement();
        }
        this.maxValueThatYouCanGet = leafs.lastElement().getValue();
        this.bestPath = leafs.lastElement().getPath();
    }

    private Node newBranch(Node root, boolean way, Vector<Node> activeNodes, Vector<Node> leafs) {

        Node branch = new Node(root, way);
        if (branch.getWeight() <= maxWeight) {
            if (branch.getPath().size() < items.size()) {
                activeNodes.add(branch);
            } else {
                leafs.add(branch);
            }
        }
        return branch;
    }

    @Override
    public void displayMoreInfo() {
        System.out.println("You can take item for maximum value of:" + maxValueThatYouCanGet);
        showPath();
    }

    private void makeListOfItemsToTake() {

        for (int i = 0; i < items.size(); i++) {
            if (bestPath.get(items.size() - 1 - i)) {
                itemsToTake.add(items.get(i));
            }
        }
    }

    private void showPath() {
        System.out.println("The items had been reordered by their ratio. For thas order respectively this is the best way to choose items\n");
        System.out.print("ITEM(RATIO)\t\tIS_TAKEN");
        for (int i = 0; i < items.size(); i++) {
            System.out.print("\n"+items.get(i).getName()+'('+items.get(i).getRatio()+")\t\t"+bestPath.get(i));
        }
    }

}
