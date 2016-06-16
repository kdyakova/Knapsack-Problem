package branchAndBound;

import indianaJones.Item;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Krisi
 */
class Node implements Comparable<Node> {

    private Vector<Item> items;
    private int maxWeight;

    private List<Boolean> path;
    private int value;
    private int weight;
    private double upperBound;

    public Node(Vector<Item> items, int maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;

        this.path = new LinkedList<>();
        this.value = 0;
        this.weight = 0;
        this.upperBound = colculateUpperBound(items, items.size() - 1, this.value, this.weight);
    }

    public Node(Node root, boolean pathWay) {

        this.items = root.getItems();
        this.maxWeight = root.getMaxWeight();

        this.path = new LinkedList<>();
        this.path.addAll(root.getPath());
        this.path.add(pathWay);
        this.value = root.getValue();
        this.weight = root.getWeight();
        int index = items.size() - this.path.size();
        if (pathWay) {
            this.value += items.elementAt(index).getValue();
            this.weight += items.elementAt(index).getWeight();
        }
        index--;
        if (index >= 0 && this.weight <= maxWeight) {
            this.upperBound = colculateUpperBound(items, index, this.value, this.weight);
        }
        if (index == -1) {
            this.upperBound = value;
        }

    }

    private double colculateUpperBound(Vector<Item> items, int index, int startValue, int startWeight) {

        int currentValue = startValue;
        int currentWeight = startWeight;

        while (currentWeight <= maxWeight && index >= 0) {
            currentValue += items.elementAt(index).getValue();
            currentWeight += items.elementAt(index).getWeight();
            index--;
        }
        if (currentWeight < maxWeight && index >= 0) {
            currentValue += ((maxWeight - currentWeight) / currentWeight) * items.elementAt(index).getValue();
        }
        return currentValue;
    }

    public List<Boolean> getPath() {
        return path;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public double getUpperBound() {
        return upperBound;
    }

    @Override
    public int compareTo(Node anotherNode) {
        if (this.upperBound < anotherNode.getUpperBound()) {
            return -1;
        } else if (this.upperBound == anotherNode.getUpperBound()) {
            return 0;
        }
        return 1;
    }
}
