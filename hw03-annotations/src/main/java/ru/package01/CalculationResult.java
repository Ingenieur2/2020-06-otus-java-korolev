package ru.package01;

public class CalculationResult implements Result {
    private double number;
    private double degree;

    @Override
    public double result() {
        return Math.pow(degree, number);
    }

    @Override
    public double calcArcTangent() {
        return Math.atan(degree / number);
    }

    @Override
    public double calcDivision() {
        return degree / number;
    }
}
