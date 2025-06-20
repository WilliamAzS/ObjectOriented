/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
import java.io.*;
import java.util.*;

public class Game {
    String target;
    int maxTries;

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine();

        System.out.println("Select Difficulty: 0 = Easy, 1 = Medium, 2 = Hard");
        int difficulty = sc.nextInt(); sc.nextLine();

        generateTarget(difficulty);
        Player player = new Player(playerName, maxTries, target);

        System.out.println("\nWelcome, " + player.getName() + "! Try to guess the number.");

        int tries = 0;
        while (tries < player.getMaxTries()) {
            System.out.println("\nAttempt " + (tries + 1) + " of " + maxTries);
            System.out.print("Enter your guess (or type 'power' to use a power-up): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("power")) {
                player.showPowerUps();
                System.out.print("Choose power-up number to use: ");
                int puChoice = sc.nextInt(); sc.nextLine();
                player.usePowerUp(puChoice, this);
                continue;
            }

            if (!input.matches("\\d{" + target.length() + "}")) {
                System.out.println("Please enter a valid " + target.length() + "-digit number.");
                continue;
            }

            tries++;
            if (input.equals(target)) {
                System.out.println("🎉 Congratulations! You've guessed the number in " + tries + " tries.");
                saveProgress(player);
                return;
            } else {
                System.out.println("Clue: " + NumberClue.getClue(input, target));
            }
        }

        System.out.println("\n❌ You've used all attempts. The number was: " + target);
        saveProgress(player);
    }

    private void generateTarget(int difficulty) {
        int numDigits = 3 + difficulty;
        int num = (int) (Math.random() * Math.pow(10, numDigits));
        this.target = String.format("%0" + numDigits + "d", num);
        this.maxTries = difficulty == 0 ? 15 : (difficulty == 1 ? 12 : 10);
    }

    private void saveProgress(Player player) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(player.getName() + "_save.dat"))) {
            out.writeObject(player);
            System.out.println("Progress saved.");
        } catch (IOException e) {
            System.out.println("Error saving progress: " + e.getMessage());
        }
    }
}

