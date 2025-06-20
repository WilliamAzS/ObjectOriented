/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
public class NumberClue {
    public static String getClue(String guess, String target) {
        int correct = 0;
        int misplaced = 0;
        boolean[] checked = new boolean[target.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == target.charAt(i)) {
                correct++;
                checked[i] = true;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) != target.charAt(i)) {
                for (int j = 0; j < target.length(); j++) {
                    if (!checked[j] && guess.charAt(i) == target.charAt(j)) {
                        misplaced++;
                        checked[j] = true;
                        break;
                    }
                }
            }
        }

        return correct + " correct, " + misplaced + " misplaced.";
    }
}
