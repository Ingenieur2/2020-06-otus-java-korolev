package ru.package02;

import java.util.*;

public class ATM implements FunctionInterface {
    int count = 10;
    List<Cell> atm = new ArrayList<>();

    ATM() {
        for (int i = 0; i < Nominals.values().length; i++) {
            atm.add(new Cell(Nominals.values()[i], count));
        }
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

            for (int i = 0; i < atm.size(); i++) {

                givingSumm[i] = param2 / atm.get(i).nominal.value;
                if (givingSumm[i] > atm.get(i).count) {
                    givingSumm[i] = atm.get(i).count;
                }
                param2 = param2 - (givingSumm[i] * atm.get(i).nominal.value);
            }
            if (param2 == 0) {
                for (int i = 0; i < givingSumm.length; i++) {
                    atm.get(i).count = atm.get(i).count - givingSumm[i];
                }
                System.out.println("You took " + takenSum + ". Good luck!");
            } else {
                System.out.println("ERROR giving money.");
            }
        }
        while (param2 != 0);
    }

    @Override
    public void addBanknote() {
        int param2;
        System.out.println("You can add banknotes");
        do {
            System.out.print("Enter banknote or press 0 to interrupt:  ");
            Scanner sc1 = new Scanner(System.in);
            param2 = sc1.nextInt();

            for (int i = 0; i < atm.size(); i++) {
                if (param2 == atm.get(i).nominal.value) {
                    System.out.println("You added: " + param2);
                    atm.get(i).count++;
                    i = atm.size();
                }
            }
        } while (param2 != 0);
    }

    @Override
    public void giveInfo() {
        int SummaryCount = 0;
        System.out.println("You can get information");
        System.out.println("Current state: ");
        for (int i = 0; i < atm.size(); i++) {
            System.out.print(atm.get(i).nominal + " - ");
            System.out.println(atm.get(i).count);
            SummaryCount = SummaryCount + atm.get(i).nominal.value * atm.get(i).count;
        }
        System.out.println("Currently available is " + SummaryCount);
    }

    @Override
    public void endATMSession() {
        System.out.println("End the session");
    }
}
