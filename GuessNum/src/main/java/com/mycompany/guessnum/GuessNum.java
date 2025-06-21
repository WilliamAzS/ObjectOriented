/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.guessnum;

/**
 *
 * @author mclr
 */
import java.util.Scanner;
<<<<<<< HEAD


import java.util.Random;

=======
import java.util.Random;
>>>>>>> upstream/main

public class GuessNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎮 Welcome to Guess the Number!");
        System.out.println("Choose Difficulty:");
        System.out.println("0 - Easy (3 digits, 15 tries)");
        System.out.println("1 - Medium (4 digits, 12 tries)");
        System.out.println("2 - Hard (5 digits, 10 tries)");

        int difficulty;
        while (true) {
            System.out.print("Enter difficulty (0/1/2): ");
            String input = sc.nextLine();
            if (input.matches("[012]")) {
                difficulty = Integer.parseInt(input);
                break;
            } else {
                System.out.println("❌ Invalid input. Please enter 0, 1, or 2.");
            }
        }

        Game game = new Game(difficulty);
        game.start();
    }
}