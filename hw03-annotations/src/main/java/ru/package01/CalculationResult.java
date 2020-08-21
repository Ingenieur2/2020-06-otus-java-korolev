package ru.package01;

public class CalculationResult implements Result {
    private double number;
    private double degree;

    public CalculationResult(double number1, double degree1) {
        this.degree = number1;
        this.number = degree1;
    }

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
