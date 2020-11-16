package ru.package01;


import java.util.ArrayList;
import java.util.List;

public class DemoSequenceOfNumbers {
    static final List<String> threadsNameList = new ArrayList<>();
    static final List<Thread> threadsList = new ArrayList<>();


    public static void main(String[] args) {
        threadsNameList.add("first__1");
        threadsNameList.add("second____2");

        // Here could be any number of threads

//        threadsNameList.add("third________3");
//        threadsNameList.add("fourth__________4");
//        threadsNameList.add("fifth_______________5");
        DemoSequenceOfNumbers sequence = new DemoSequenceOfNumbers();
        for (int j = 0; j < threadsNameList.size(); j++) {
            int finalJ = j;
            Thread currentThread = new Thread(() -> {
                sequence.action(threadsNameList.get(finalJ));
            });
            currentThread.start();
        }
    }

    private synchronized void action(String message) {
        int i = 1;
        int currentThreadNumber = 0;
        int switchPoint1 = 1;
        int switchPoint2 = 10;
        int delta = 1;

        while (i <= switchPoint2) {
            try {
                sleep();
                while (threadsNameList.get(currentThreadNumber).equals(message)) {
                    this.wait();
                }
                message = threadsNameList.get(currentThreadNumber);
                currentThreadNumber++;
                System.out.println(message + ": i = " + i);
                while (currentThreadNumber == threadsNameList.size()) {
                    currentThreadNumber = 0;
                    i = i + delta;
                    if ((i == switchPoint1) || (i == switchPoint2)) {
                        delta = delta * (-1);
                    }
                }
                notify();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
