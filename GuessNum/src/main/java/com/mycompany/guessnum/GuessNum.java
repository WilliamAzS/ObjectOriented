/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.guessnum;

/**
 *
 * @author mclr
 */
import java.util.Scanner;
import java.util.Random;

public class GuessNum {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Welcome to Guess the Number Game ===");
        System.out.println("Choose difficulty level:");
        System.out.println("1. Easy (1 - 50)");
        System.out.println("2. Medium (1 - 100)");
        System.out.println("3. Hard (1 - 1000)");
        System.out.print("Enter your choice (1-3): ");

        int maxNumber = 100; // default
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                maxNumber = 50;
                break;
            case 2:
                maxNumber = 100;
                break;
            case 3:
                maxNumber = 1000;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Medium (1-100)");
        }

        int hiddenNumber = random.nextInt(maxNumber) + 1;
        int maxTries = 7;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Start guessing the number between 1 and " + maxNumber + ". You have " + maxTries + " tries!");

        while (attempts < maxTries) {
            System.out.print("Attempt " + (attempts + 1) + ": ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == hiddenNumber) {
                System.out.println("Correct! You guessed the number in " + attempts + " tries.");
                guessedCorrectly = true;
                break;
            } else if (guess < hiddenNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Game over! The number was: " + hiddenNumber);
        }

        scanner.close();
    }
}
