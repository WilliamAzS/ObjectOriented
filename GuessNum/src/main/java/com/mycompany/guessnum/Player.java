/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private final String name;
    private final int maxTries;
    private final String target;
    private final List<PowerUp> powerUps;

    public Player(String name, int maxTries, String target) {
        this.name = name;
        this.maxTries = maxTries;
        this.target = target;
        this.powerUps = new ArrayList<>();
        powerUps.add(randomPowerUp());
        powerUps.add(randomPowerUp());
    }

    private PowerUp randomPowerUp() {
        int choice = new Random().nextInt(3);
        return switch (choice) {
            case 0 -> new RevealDigitCard();
            case 1 -> new RangeHintCard();
            default -> new RemoveDigitCard();
        };
    }

    public String getName() {
        return name;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public void showPowerUps() {
        System.out.println("Available Power-Ups:");
        for (int i = 0; i < powerUps.size(); i++) {
            System.out.println((i + 1) + ". " + powerUps.get(i).getName());
        }
    }

    public void usePowerUp(int index, Game game) {
        if (index < 1 || index > powerUps.size()) {
            System.out.println("Invalid power-up choice.");
            return;
        }
        powerUps.get(index - 1).use(game, this);
        powerUps.remove(index - 1);
    }
}
