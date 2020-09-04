package ru.package02;

import java.util.HashMap;
import java.util.Map;

public class CurrentState {
    int numberOfBanknotes=100;
    Map currentState;


    public CurrentState(Map currentState) {
        this.currentState = currentState;
    }

    public Map getState() {
        Map<ru.package01.Nominals, Integer> currentState = new HashMap<>();

        for (Map.Entry<ru.package01.Nominals, Integer> entry : currentState.entrySet()) {
            entry.setValue(numberOfBanknotes);
            System.out.println("number of " + entry.getKey() + "-banknotes is " + numberOfBanknotes);
        }
        return currentState;
    }
}
