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

    public Game(int difficulty) {
        generateTarget(difficulty);
    }

    private void generateTarget(int difficulty) {
        int numDigits = 3 + difficulty;
        int num = (int) (Math.random() * Math.pow(10, numDigits));
        this.target = String.format("%0" + numDigits + "d", num);
        this.maxTries = difficulty == 0 ? 15 : (difficulty == 1 ? 12 : 10);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine();

        Player player = loadProgress(playerName);
        if (player == null) {
            player = new Player(playerName, maxTries, target);
        } else {
            System.out.println("👋 Welcome back, " + player.name + "! Best score so far: " + player.getBestScore());
        }

        int tries = 0;
        while (tries < player.maxTries) {
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
                System.out.println("🎉 Correct! You guessed it in " + tries + " tries.");
                player.calculateScore(tries);
                System.out.println("🎯 Score: " + player.getScore());

                if (player.getScore() > player.getBestScore()) {
                    player.setBestScore(player.getScore());
                    System.out.println("💾 New high score saved!");
                    saveProgress(player);
                }

                updateLeaderboard(player.name, player.getScore());
                return;
            } else {
                System.out.println("Clue: " + NumberClue.getClue(input, target));
            }
        }

        System.out.println("\n❌ You've used all attempts. The number was: " + target);
        player.calculateScore(tries);
        System.out.println("🎯 Score: " + player.getScore());
        if (player.getScore() > player.getBestScore()) {
            player.setBestScore(player.getScore());
            System.out.println("💾 New high score saved!");
        }
        saveProgress(player);
        updateLeaderboard(player.name, player.getScore());
    }

    private void saveProgress(Player player) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(player.name + "_save.dat"))) {
            out.writeObject(player);
        } catch (IOException e) {
            System.out.println("Error saving progress: " + e.getMessage());
        }
    }

    public Player loadProgress(String playerName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playerName + "_save.dat"))) {
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private void updateLeaderboard(String name, int score) {
        try {
            File file = new File("leaderboard.txt");
            List<String> lines = new ArrayList<>();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            }

            lines.add(name + "," + score);
            lines.sort((a, b) -> Integer.compare(
                    Integer.parseInt(b.split(",")[1]),
                    Integer.parseInt(a.split(",")[1])
            ));

            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < Math.min(lines.size(), 5); i++) {
                writer.println(lines.get(i));
            }
            writer.close();

            System.out.println("\n🏆 Leaderboard:");
            for (int i = 0; i < Math.min(lines.size(), 5); i++) {
                String[] parts = lines.get(i).split(",");
                System.out.println((i + 1) + ". " + parts[0] + " - " + parts[1] + " pts");
            }

        } catch (IOException e) {
            System.out.println("Error writing leaderboard: " + e.getMessage());
        }
    }
}