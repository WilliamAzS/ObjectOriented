/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
import java.util.Random;

public class RevealDigitCard extends PowerUp {
    public RevealDigitCard() {
        this.name = "Reveal Digit";
    }

    @Override
    public void use(Game game, Player player) {
        int pos = new Random().nextInt(game.target.length());
        System.out.println("Power-Up: One digit revealed → Position " + (pos + 1) + " is '" + game.target.charAt(pos) + "'");
    }
}
