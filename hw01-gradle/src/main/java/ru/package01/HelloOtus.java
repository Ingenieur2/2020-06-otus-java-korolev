/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.package01;



import static com.google.common.base.Objects.equal;


/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./L01-gradle/build/libs/gradleHelloWorld-0.1.jar
 *
 * To unzip the jar:
 * unzip -l L01-gradle.jar
 * unzip -l gradleHelloWorld-0.1.jar
 *
 */
public class HelloOtus {
    public static void main(String... args) {
        int[] array1 = {1, 3 ,5, 9};
        int[] array2 = {1, 7 ,5, 6};
        int[] array3 = new int[0];
        boolean flag1 = equal(array1, array2);  // this method equals two arrays
        boolean flag2 = equal(array1, array3);  // this method equals two arrays


        System.out.println(flag1);
        System.out.println(flag2);
    }
}
