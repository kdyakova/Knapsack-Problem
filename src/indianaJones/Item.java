/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indianaJones;

/**
 *
 * @author Krisi
 */
public class Item implements  Comparable<Item>{

    private String name;
    private int value;
    private int weight;
    private double ratio;

    public Item(String itemInfo) {
        Translator translator = new Translator();

        this.name = translator.getItemName(itemInfo);
        this.value = translator.getItemValue(itemInfo);
        this.weight = translator.getItemWeight(itemInfo);

        this.ratio = (double) value / weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public int compareTo(Item anotherItem) {
        if (this.ratio < anotherItem.getRatio()) {
            return -1;
        } else if (this.ratio == anotherItem.getRatio()) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return name;
    }

}
