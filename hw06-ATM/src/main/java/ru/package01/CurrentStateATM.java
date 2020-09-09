package ru.package01;

import java.util.Scanner;

public class CurrentStateATM implements FunctionATMInterface {

    int[][] currentState = new int[7][2];

    public CurrentStateATM() {
        currentState[0][0] = 5000;
        currentState[1][0] = 2000;
        currentState[2][0] = 1000;
        currentState[3][0] = 500;
        currentState[4][0] = 200;
        currentState[5][0] = 100;
        currentState[6][0] = 50;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 1; j < currentState[i].length; j++) {
                currentState[i][j] = 100;
            }
        }
    }

    @Override
    public void giveInfo() {
        System.out.println("You can get information");
        System.out.println("Current state: ");
        for (int[] arr : currentState) {
            for (int arr1 : arr) {
                System.out.print(arr1 + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void addBanknote() {
        int param2;
        System.out.println("You can add banknotes");
        do {
            System.out.print("Enter banknote or press 0 to interrupt:  ");
            Scanner sc1 = new Scanner(System.in);
            param2 = sc1.nextInt();
            for (int i = 0; i < currentState.length; i++) {
                if (param2 == currentState[i][0]) {
                    System.out.println("You added: " + param2);
                    currentState[i][1] = currentState[i][1] + 1;
                    i = currentState.length;
                }
            }
        } while (param2 != 0);
    }

    @Override
    public void endATMSession() {
        System.out.println("End the session");
    }

    @Override
    public void giveBanknotes() {
        int param2;
        System.out.println("You can take banknotes");
        do {
            System.out.print("Enter the amount of money or press 0 to interrupt:  ");
            Scanner sc1 = new Scanner(System.in);
            param2 = sc1.nextInt();
            int takenSum = param2;

            int[] givingSumm = new int[7];
            for (int i = 0; i < givingSumm.length; i++) {
                givingSumm[i] = param2 / currentState[i][0];
                if (givingSumm[i] > currentState[i][1]) {
                    givingSumm[i] = currentState[i][1];
                }
                param2 = param2 - (givingSumm[i] * currentState[i][0]);
            }
            if (param2 == 0) {
                for (int i = 0; i < givingSumm.length; i++) {
                    currentState[i][1] = currentState[i][1] - givingSumm[i];
                }
                System.out.println("You took " + takenSum + ". Good luck!");
            } else {
                System.out.println("ERROR giving money.");
            }
        }
        while (param2 != 0);
    }
}
