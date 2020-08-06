package ru.package01;

import java.util.*;

public class Generics {

    public static void main(String... args) {
        List<Integer> list1 = new DIYArrayList<>();
        System.out.print("--Creating new list 1----");
        for (int i = 0; i < 6; i++) {
            list1.add((int) (Math.random() * 100));
            System.out.print(list1.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.print("--Creating new list 2----");

        List<Integer> list2 = new DIYArrayList<>();
        for (int i = 0; i < 9; i++) {
            list2.add((int) (Math.random() * 100));
            System.out.print(list2.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.println("------Adding elements to list 1------");

        Collections.addAll(list1, 32, 75, 10);   //Adding elements is working CORRECTLY

        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.println("------Sorting elements of list 1------");

        Collections.sort(list1);    //Sorting elements is working CORRECTLY
        Collections.sort(list2);    //Sorting elements is working CORRECTLY

        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.println("------Copying elements of list 1 into list 2------");

        Collections.copy(list2, list1);     //Copying elements is working CORRECTLY
        System.out.print("--List 1----");
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.print("--List 2----");
        for (int i = 0; i < list2.size(); i++) {
            System.out.print(list2.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.println("----------------------------------------");
        System.out.print("--List 3----");
        List<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list3.add((int) (Math.random() * 100));
            System.out.print(list3.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.print("--List 4----");
        List<Integer> list4 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list4.add((int) (Math.random() * 100));
            System.out.print(list4.get(i) + ", ");
        }
        System.out.println(" ");
        System.out.println("--Copying elements of list 3 into list 4----");
        Collections.copy(list4, list3); //Copying elements of ARRAYLISTS 3 and 4
        for (int i = 0; i < 20; i++) {
            System.out.print(list3.get(i) + ", ");
        }
    }
}
