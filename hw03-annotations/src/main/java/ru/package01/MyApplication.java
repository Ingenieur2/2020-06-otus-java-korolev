package ru.package01;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number:  ");
        double number = sc.nextDouble();
        System.out.println("Enter degree:  ");
        double degree = sc.nextDouble();

        CalculationResult calculationResult = new CalculationResult(number, degree);

        System.out.println("Your result of calculating exponentiation: " + calculationResult.result());
        System.out.println("Your result is of calculating angle: " + calculationResult.calcArcTangent());
        System.out.println("Your result of division is: " + calculationResult.calcDivision());
    }
}