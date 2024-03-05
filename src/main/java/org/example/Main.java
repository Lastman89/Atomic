package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[1000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        AtomicInteger three = new AtomicInteger();
        AtomicInteger four = new AtomicInteger();
        AtomicInteger five = new AtomicInteger();
        CheckName count = new CheckName();

        Thread threadFirst = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                int checkValue = count.counter(texts[i]);
                switch (checkValue) {
                    case 3:
                        three.getAndIncrement();
                        break;
                    case 4:
                        four.getAndIncrement();
                        break;
                    case 5:
                        five.getAndIncrement();
                        break;
                }
            }
        }
        );
        Thread threadSecond = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                int checkValue = count.counterPolindrom(texts[i]);
                switch (checkValue) {
                    case 3:
                        three.getAndIncrement();

                        break;
                    case 4:
                        four.getAndIncrement();
                        break;
                    case 5:
                        five.getAndIncrement();
                        break;
                }

            }

        }
        );

        Thread threadThree = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                int checkValue = count.counterIncreas(texts[i]);
                switch (checkValue) {
                    case 3:
                        three.getAndIncrement();
                        break;
                    case 4:
                        four.getAndIncrement();
                        break;
                    case 5:
                        five.getAndIncrement();
                        break;
                }
            }

        }
        );
        threadFirst.start();
        threadSecond.start();
        threadThree.start();

        threadFirst.join();
        threadSecond.join();
        threadThree.join();


        System.out.println("Красивых слов с длиной 3: " + three + " шт.");
        System.out.println("Красивых слов с длиной 4: " + four + " шт.");
        System.out.println("Красивых слов с длиной 5: " + five + " шт.");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}