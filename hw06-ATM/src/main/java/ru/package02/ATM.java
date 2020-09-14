package ru.package02;

import java.util.*;

public class ATM implements ATMInterface {
    private int count = 10;
    private List<Cell> atm = new ArrayList<>();

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

            int[] givingBanknotes = new int[Nominals.values().length];
            for (int i = 0; i < atm.size(); i++) {
                givingBanknotes[i] = param2 / atm.get(i).getNominal().getValue();
                if (givingBanknotes[i] > atm.get(i).getValue()) {
                    givingBanknotes[i] = atm.get(i).getValue();
                }
                param2 = param2 - (givingBanknotes[i] * atm.get(i).getNominal().getValue());
            }
            if (param2 == 0) {
                for (int i = 0; i < givingBanknotes.length; i++) {
                    atm.get(i).decreaseCount(givingBanknotes[i]);
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
                if (param2 == atm.get(i).getNominal().getValue()) {
                    System.out.println("You added: " + param2);
//                    atm.get(i).setCount(atm.get(i).getCount()+1);
                    atm.get(i).increaseCount();
                    //atm.get(i).count++;
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
            System.out.print(atm.get(i).getNominal() + " - ");
            System.out.println(atm.get(i).getValue());
            SummaryCount = SummaryCount + atm.get(i).getNominal().getValue() * atm.get(i).getValue();
        }
        System.out.println("Currently available is " + SummaryCount);
    }

    @Override
    public void endATMSession() {
        System.out.println("End the session");
    }
}
