/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

import indianaJones.Item;
import indianaJones.Knapsack;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Krisi
 */
public class DynamicProgramming implements Knapsack {

    private int maxWeight;
    private Vector<Item> items;
    
    private static Cell[][] table;
    private LinkedList<Item> itemsToTake;

    public DynamicProgramming(Vector<Item> items, int maxWeight) {
        table = new Cell[items.size() + 1][maxWeight + 1];
        this.maxWeight = maxWeight;
        this.items = items;
        itemsToTake = new LinkedList<>();
    }

    @Override
    public LinkedList<Item> getBestSolution() {
        makeTable(this.items, maxWeight, items.size());
        makeListOfItemsToTake();

        return this.itemsToTake;
    }

    private int makeTable(Vector<Item> items, int weight, int n) {
        if (weight <= 0 || n <= 0) {
            return 0;
        }

        if (table[n][weight] != null) {
            return table[n][weight].getValue();
        }

        if (weight < items.get(n - 1).getWeight()) {
            table[n - 1][weight - 1] = new Cell(makeTable(items, weight, n - 1), false);
        } else {

            int takePlusPrevious = items.get(n - 1).getValue() + makeTable(items, weight - items.get(n - 1).getWeight(), n - 1);
            int notTaken = makeTable(items, weight, n - 1);
            if (takePlusPrevious > notTaken) {
                table[n - 1][weight - 1] = new Cell(takePlusPrevious, true);
            } else {
                table[n - 1][weight - 1] = new Cell(notTaken, false);
            }
        }
        return table[n - 1][weight - 1].getValue();

    }

    private void showTable() {
        for (int i = 0; i < items.size(); i++) {

            for (int j = 0; j < maxWeight; j++) {
                if (table[i][j] != null) {
                    System.out.print(table[i][j].isIsObjectTaken());
                } else {
                    System.out.print("-----");
                }
                if (j < maxWeight - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
    }

    @Override
    public void displayMoreInfo() {
        System.out.println("You can take item for maximum value of:" + table[items.size() - 1][maxWeight - 1].getValue());
        showTable();
    }

    private void makeListOfItemsToTake() {
        int j = maxWeight - 1;
        for (int i = items.size() - 1; i >= 0; i--) {
            if (j < 0) {
                break;
            }
            if (table[i][j].isIsObjectTaken() == true) {
                this.itemsToTake.add(items.get(i));
                j -= items.get(i).getWeight();
            }

        }
    }
}
