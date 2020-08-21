package ru.package01;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunnerTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ArithmeticException {
        int numberOfPassedTests = 0;
        int numberOfFailedTests = 0;
        final int NUMBER_OF_TESTS = 100;

        Class<?> classString = Class.forName("ru.package01.CalculationResultTest");
        System.out.println("canonicalName: " + classString.getCanonicalName());
        System.out.println("--- all methods:");

        Method[] methodsAll = classString.getDeclaredMethods();   //получаем объявленные методы

        int namesLength = methodsAll.length;
        String names[][] = new String[namesLength][10];         //создаем новый 2-х мерный массив для данных, где в 1-ом столбце название методов, в следующих - соответствующие методы

        for (int i = 0; i < namesLength; i++) {
            names[i][0] = methodsAll[i].getName();              //запись i-ого метода в i-ую строку
            System.out.print(names[i][0] + ",  ");

            Annotation[] annotations = methodsAll[i].getDeclaredAnnotations();      //получение 1-мерного массива аннотаций для i-ого метода
            int numberOfAnnotations = annotations.length;             //число аннотаций для i-ого метода

            if (numberOfAnnotations == 0) {                      //для обхода nullPointerException - если метод в тесте есть, но стоит без аннотации, то присваиваем ему значение not-null
                names[i][1] = "0";
            }
            for (int j = 0; j < numberOfAnnotations; j++) {
                names[i][j + 1] = annotations[j].toString();        //запись j-той аннотации в элемент [i, j+1]
                System.out.print(names[i][j + 1] + ",  ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------------------------");

        for (int numberOfTests = 0; numberOfTests < NUMBER_OF_TESTS; numberOfTests++) {
            for (int i = 0; i < namesLength; i++) {
                Annotation[] annotations = methodsAll[i].getDeclaredAnnotations();
                int numberOfAnnotations = annotations.length;

                for (int j = 0; j < numberOfAnnotations; j++) {
                    if (names[i][j + 1].contains("Test1()")) {          //поиск аннотации Test1
                        double degree = Math.random() * 1000;
                        double number = Math.random() * 1000;
                        CalculationResultTest object1 = new CalculationResultTest(degree, number);
                        System.out.println(methodsAll[i].toString());
                        for (int i1 = 0; i1 < namesLength; i1++) {
                            for (int j1 = 0; j1 < numberOfAnnotations; j1++) {              //выполнение аннотации Before для i-ого метода
                                if (names[i1][j1 + 1].contains("Before()")) {
                                    methodsAll[i1].invoke(object1);
                                    j1 = numberOfAnnotations;
                                }
                            }
                        }
                        try {
                            var result1 = methodsAll[i].invoke(object1, degree, number);    //выполнение аннотации Test1 для i-ого метода
                            System.out.println(result1.toString());
                            if (result1.toString().contains("NaN") || (result1.toString().contains("Infinity"))) {
                                throw new ArithmeticException();
                            }
                            numberOfPassedTests++;
                        } catch (Exception e) {
                            System.out.println("ARITHMETIC EXCEPTION");
                            numberOfFailedTests++;
                        }

                        for (int i2 = 0; i2 < namesLength; i2++) {
                            for (int j2 = 0; j2 < numberOfAnnotations; j2++) {              //выполнение аннотации After для i-ого метода
                                if (names[i2][j2 + 1].contains("After()")) {
                                    methodsAll[i2].invoke(object1);
                                    j2 = numberOfAnnotations;
                                }
                            }
                        }
                        j = numberOfAnnotations;
                    }
                }
            }
        }
        System.out.println("Number of passed tests: " + numberOfPassedTests);
        System.out.println("Number of failed tests: " + numberOfFailedTests);

    }
}
