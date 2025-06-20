/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
import java.util.*;

public class RemoveDigitCard extends PowerUp {
    public RemoveDigitCard() {
        this.name = "Remove Wrong Digit";
    }

    @Override
    public void use(Game game, Player player) {
        List<Character> wrongDigits = new ArrayList<>();
        for (char d = '0'; d <= '9'; d++) {
            if (!game.target.contains(String.valueOf(d))) {
                wrongDigits.add(d);
            }
        }

        if (!wrongDigits.isEmpty()) {
            char removed = wrongDigits.get(new Random().nextInt(wrongDigits.size()));
            System.out.println("Power-Up: Digit '" + removed + "' is NOT in the target number.");
        } else {
            System.out.println("All digits are in the target number!");
        }
    }
}
