package ru.package01;

import java.util.Scanner;

public class DemoATM {
    public final static String TEXT_FOR_USERS = "Enter 0 - end the session, 1 - to add money, 2 - to take money, 3 - to get information:  ";

    public static void main(String[] args) {
        boolean flag = true;
        var atm = new CurrentStateATM();
        atm.giveInfo();

        while (flag == true) {
            Scanner sc1 = new Scanner(System.in);
            System.out.print(TEXT_FOR_USERS);
            String param1 = sc1.next();
            switch (param1) {
                case "1": {
                    atm.addBanknote();
                    break;
                }
                case "2": {
                    atm.giveBanknotes();
                    break;
                }
                case "3": {
                    atm.giveInfo();
                    break;
                }
                case "0": {
                    atm.endATMSession();
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Go back");
                }
            }
        }
    }
}
