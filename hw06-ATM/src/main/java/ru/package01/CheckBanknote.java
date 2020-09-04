package ru.package01;

public class CheckBanknote {
    static boolean checkMoney(int banknote) {

        for (int i = 0; i < Nominals.values().length; i++) {

            int value = Integer.parseInt(String.valueOf(Nominals.values()[i].getNominal()));

            if (value == banknote) {
                System.out.println("You added: " + banknote);

                return true;
            }

        }
        System.out.println("WRONG BANKNOTE");

        return false;
    }

}


