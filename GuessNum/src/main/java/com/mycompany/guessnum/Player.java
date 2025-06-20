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
    String name;
    int maxTries;
    String target;
    List<PowerUp> powerUps = new ArrayList<>();
    private int score = 0;
    private int bestScore = 0;

    public Player(String name, int maxTries, String target) {
        this.name = name;
        this.maxTries = maxTries;
        this.target = target;
        this.powerUps.add(randomPowerUp());
        this.powerUps.add(randomPowerUp());
    }

    private PowerUp randomPowerUp() {
        int choice = new Random().nextInt(3);
        return switch (choice) {
            case 0 -> new RevealDigitCard();
            case 1 -> new RangeHintCard();
            default -> new RemoveDigitCard();
        };
    }

    public void showPowerUps() {
        if (powerUps.isEmpty()) {
            System.out.println("No power-ups left.");
            return;
        }
        System.out.println("Available Power-Ups:");
        for (int i = 0; i < powerUps.size(); i++) {
            System.out.println((i + 1) + ". " + powerUps.get(i).name);
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

    public void calculateScore(int attempts) {
        this.score = Math.max(0, 100 - 10 * (attempts - 1));
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int score) {
        this.bestScore = score;
    }
}