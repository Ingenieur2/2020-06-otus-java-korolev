package ru.package02;

import ru.package01.Nominals;

public class Banknote implements BanknoteInterface{
    int currentBanknote;
    public Banknote(int currentBanknote){
        this.currentBanknote = currentBanknote;
    }

    @Override
    public void checkBanknote(int currentBanknote) {
        for (int i = 0; i < Nominals.values().length ; i++){
            int value = Integer.parseInt(String.valueOf(Nominals.values()[i].getNominal()));
            if (value==currentBanknote) {
                System.out.println("You 0000000000000    " + currentBanknote);
            }
        }
    }
}
