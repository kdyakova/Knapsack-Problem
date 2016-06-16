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
public class Translator {

    String itemPattern = "\\w+\\s+\\d+\\s+\\d+";

    public boolean isAnItem(String line) {
        return line.matches(itemPattern);
    }

    public String getItemName(String line) {
        return getWordAt(line, 1);
    }

    public int getItemValue(String line) {
        return Integer.parseInt(getWordAt(line, 2));
    }

    public int getItemWeight(String line) {
        return Integer.parseInt(getWordAt(line, 3));
    }

    private String getWordAt(String line, int position) {
        String[] words = line.split("\\s+");
        return words[position - 1];
    }

}
