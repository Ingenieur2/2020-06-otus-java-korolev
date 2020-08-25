package ru.package01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CollectionsForTesting {
    public static final String CLASS_NAME = "ru.package01.CalculationResultTest";

    public Class<?> classString = Class.forName(CLASS_NAME);
    private Method[] methodsAll = classString.getDeclaredMethods();   //получаем объявленные методы
    List<Method> beforeList = new ArrayList<>();
    List<Method> testList = new ArrayList<>();
    List<Method> afterList = new ArrayList<>();

    public CollectionsForTesting() throws ClassNotFoundException {

        int namesLength = methodsAll.length;

        for (int i = 0; i < namesLength; i++) {
            Annotation[] annotations = methodsAll[i].getDeclaredAnnotations();
            int numberOfAnnotations = annotations.length;             //число аннотаций для i-ого метода
            for (int j = 0; j < numberOfAnnotations; j++) {
                if (annotations[j].toString().contains("Before")) {     //запись в отдельный список методов, помеченных аннотацией "Before"
                    beforeList.add(methodsAll[i]);
                }
                if (annotations[j].toString().contains("Test1")) {      //запись в отдельный список методов, помеченных аннотацией "Test1"
                    testList.add(methodsAll[i]);
                }
                if (annotations[j].toString().contains("After")) {      //запись в отдельный список методов, помеченных аннотацией "After"
                    afterList.add(methodsAll[i]);
                }
            }
        }

    }

}

