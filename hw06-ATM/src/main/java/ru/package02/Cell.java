package ru.package02;

public class Cell implements GetValueInterface {
    private int count;
    private final Nominals nominal;

    Cell(Nominals nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    Nominals getNominal() {
        return nominal;
    }

    int increaseCount() {
        count = count + 1;
        return count;
    }

    int decreaseCount(int givingBanknotes) {
        count = count - givingBanknotes;
        return count;
    }

    @Override
    public int getValue() {
        return count;
    }
}
