package ru.package01;

import java.util.Scanner;

public class DemoLogging {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter number 1:  ");
        int param1 = sc1.nextInt();
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter number 2:  ");
        int param2 = sc2.nextInt();

        MyClassInterface testLogging = Ioc.createMyClass();
        testLogging.calcLogarithm(param1);
        testLogging.calcExp(param1);
        testLogging.calcMultiply(param1, param2);
    }
}
