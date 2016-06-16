/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indianaJones;

import java.util.LinkedList;

/**
 *
 * @author Krisi
 */
public interface Knapsack {

    /**
     *
     * @return the list that gives maximum profit
     */
    public LinkedList<Item> getBestSolution();

    public void displayMoreInfo();
}
