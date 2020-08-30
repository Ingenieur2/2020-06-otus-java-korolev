package ru.package01;

public class TestLogging implements MyClassInterface {
    public TestLogging(int param) {

    }

    public TestLogging(int param1, int param2) {

    }

    @Log
    @Override
    public void calcLogarithm(int param) {
        double result = Math.log(param);
        System.out.println("log( " + param + " ) = " + result);
    }

    @Log
    @Override
    public void calcExp(int param) {
        double result = Math.exp(param);
        System.out.println("exp( " + param + " ) = " + result);
    }

    @Log
    @Override
    public void calcMultiply(int param1, int param2) {
        int result = param1 * param2;
        System.out.println("( " + param1 + " * " + param2 + " ) = " + result);
    }
}

