package ru.package01;

import java.util.HashMap;
import java.util.Map;

public class AutomatedTellerMachine implements FunctionInterface {

    public int countOfBanknotes = 100;
    public Nominals nominals;
    Map<Nominals, Integer> currentState= new HashMap<>();


    public AutomatedTellerMachine() {

    }

//    public AutomatedTellerMachine(Map<Nominals, Integer> currentState) {
//        this.currentState = currentState;
//    }

    public Map getFirstState() {

        for (Nominals nominals : Nominals.values()) {
            currentState.put(nominals, countOfBanknotes);

        }
        for (Map.Entry<Nominals, Integer> entry : currentState.entrySet()) {

            entry.setValue(countOfBanknotes);
            System.out.println("number of " + entry.getKey() + "-banknotes is " + countOfBanknotes);
        }
        return currentState;
    }


    public Map getState() {
        System.out.println(currentState);
        return currentState;
    }

    @Override
    public void addMoney(int banknote) {
        currentState.replace(nominals, countOfBanknotes, countOfBanknotes++);
    }

    @Override
    public void giveInformation() {

    }

    @Override
    public void takeMoney(int banknote) {
        currentState.replace(nominals, countOfBanknotes, countOfBanknotes--);
    }
}
