/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
public class RangeHintCard extends PowerUp {
    public RangeHintCard() {
        this.name = "Range Hint";
    }

    @Override
    public void use(Game game, Player player) {
        int targetNum = Integer.parseInt(game.target);
        System.out.println("Power-Up: The target number is between " + (targetNum - 10) + " and " + (targetNum + 10));
    }
}
