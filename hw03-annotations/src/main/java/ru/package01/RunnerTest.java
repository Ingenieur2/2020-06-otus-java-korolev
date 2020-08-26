package ru.package01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static ru.package01.CollectionsForTesting.CLASS_NAME;

public class RunnerTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        long beginTime = System.currentTimeMillis();
        int numberOfPassedTests = 0;
        int numberOfFailedTests = 0;
        final int NUMBER_OF_TESTS = 10000000;

        CollectionsForTesting collectionsForTesting = new CollectionsForTesting();

        System.out.println("List of methods with annotation BEFORE" + collectionsForTesting.beforeList);
        System.out.println("List of methods with annotation TEST1" + collectionsForTesting.testList);
        System.out.println("List of methods with annotation AFTER" + collectionsForTesting.afterList);

        int beforeListLength = collectionsForTesting.beforeList.size();
        int testListLength = collectionsForTesting.testList.size();
        int afterListLength = collectionsForTesting.afterList.size();

        for (int i = 0; i < NUMBER_OF_TESTS; i++) {

            Class<?> classString = Class.forName(CLASS_NAME);               //создание объекта класса CalculationResultTest
            Constructor<?> constructor = classString.getConstructor();
            var object1 = constructor.newInstance();

            for (int j = 0; j < beforeListLength; j++) {        //выполнение аннотации BEFORE для j-ого метода
                collectionsForTesting.beforeList.get(j).invoke(object1);
            }
            for (int j = 0; j < testListLength; j++) {

                try {
                    var result1 = collectionsForTesting.testList.get(j).invoke(object1);    //выполнение аннотации Test1 для j-ого метода
                    System.out.println(result1.toString());
                    if (result1.toString().contains("NaN") || (result1.toString().contains("Infinity"))) {
                        throw new ArithmeticException();
                    }
                    numberOfPassedTests++;
                } catch (Exception e) {
                    System.out.println("ARITHMETIC EXCEPTION");
                    numberOfFailedTests++;
                }
            }
            for (int j = 0; j < afterListLength; j++) {                 //выполнение аннотации BEFORE для j-ого метода
                collectionsForTesting.afterList.get(j).invoke(object1);
            }
        }
        System.out.println("Number of passed tests: " + numberOfPassedTests);
        System.out.println("Number of failed tests: " + numberOfFailedTests);
        System.out.println("Calculation time:" + (System.currentTimeMillis() - beginTime) / 1000);
    }
}
