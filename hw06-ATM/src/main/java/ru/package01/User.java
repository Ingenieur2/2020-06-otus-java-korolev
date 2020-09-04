package ru.package01;

import java.util.Scanner;


public class User {

    void checkData() {
        boolean flag = true;

        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter number 1 to add money, number 2 to take money, number 3 to get information:  ");
        String param1 = sc1.next();

        while (flag == true) {
            switch (param1) {
                case "1": {
                    System.out.println("You can add banknotes");
                    System.out.print("Enter banknote:  ");
                    int param2 = sc1.nextInt();
                    CheckBanknote.checkMoney(param2);


                    flag = false;
                    break;
                }
                case "2": {
                    System.out.println("You can take banknotes");
                    int param2 = sc1.nextInt();
                    CheckBanknote.checkMoney(param2);

                    flag = false;
                    break;
                }
                case "3": {
                    System.out.println("You can get information");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("go back");
                    flag = true;
                    System.out.print("Enter number 1 to add money, number 2 to take money, number 3 to get information:  ");
                    param1 = sc1.next();
                }
            }
        }
        String param2 = sc1.next();


    }
}