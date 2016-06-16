/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

/**
 *
 * @author Krisi
 */
public class Cell {
    private int value;
    private boolean isObjectTaken;

    public Cell(int value, boolean isObjectTaken) {
        this.value = value;
        this.isObjectTaken = isObjectTaken;
    }

    public int getValue() {
        return value;
    }

    public boolean isIsObjectTaken() {
        return isObjectTaken;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIsObjectTaken(boolean isObjectTaken) {
        this.isObjectTaken = isObjectTaken;
    }
    
    
}
