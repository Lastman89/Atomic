package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        int three = 0;
        int four = 0;
        int five = 0;
        CheckName count = new CheckName();

        for (int i = 0; i < texts.length; i++) {
            AtomicInteger countFirst = new AtomicInteger();
            AtomicInteger countSecond = new AtomicInteger();
            AtomicInteger countThree = new AtomicInteger();
            int counterI = i;


            Thread threadFirst = new Thread(() -> {
                countFirst.set(count.counter(texts[counterI]));

            }
            );
            Thread threadSecond = new Thread(() -> {
                countSecond.set(count.counterPolindrom(texts[counterI]));
            }
            );

            Thread threadThree = new Thread(() -> {
                countThree.set(count.counterIncreas(texts[counterI]));
            }
            );
            threadFirst.start();
            threadSecond.start();
            threadThree.start();

            threadFirst.join();
            threadSecond.join();
            threadThree.join();

            if (texts[i].length() == countFirst.get() || texts[i].length() == countSecond.get() || texts[i].length() - 1 == countThree.get()) {
                switch (texts[i].length()) {
                    case 3:
                        three++;
                        break;
                    case 4:
                        four++;
                        break;
                    case 5:
                        five++;
                        break;
                }
            }
        }

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