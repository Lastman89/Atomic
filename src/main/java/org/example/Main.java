package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[10000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        int Three = 0;
        int Four = 0;
        int Five = 0;
        System.out.println(Arrays.toString(texts));


        for (int i = 0; i < texts.length; i++) {
            AtomicInteger count = new AtomicInteger();
            AtomicInteger countSecond = new AtomicInteger();
            AtomicInteger countThree = new AtomicInteger();
            int counterI = i;

            for (int j = 0; j < texts[i].length(); j++) {
                //ищем слово в котором все символы одинаковы
                int counterJ = j;
                Thread treadFirst = new Thread(() -> {
                            if (texts[counterI].charAt(counterJ) == texts[counterI].charAt((texts[counterI].length() - 1))) {
                                count.getAndIncrement();

                            }
                        }
                        );
                //ищем полиндромы
                Thread treadSecond = new Thread(() -> {
                    if (texts[counterI].charAt(counterJ) == texts[counterI].charAt((texts[counterI].length() - 1) - counterJ)) {
                        countThree.getAndIncrement();
                        if (((counterJ == ((texts[counterI].length() - 1) - counterJ) && (texts[counterI].length() - 1) % 2 == 0) || (counterJ == ((texts[counterI].length()) - counterJ) && (texts[counterI].length() - 1) % 2 != 0))
                                && texts[counterI].charAt(counterJ) == texts[counterI].charAt((texts[counterI].length() - 1))) {
                            countThree.getAndDecrement();
                        }

                    }
                }
                );
                //ищем слово где буквы в порядке возрастания
                Thread treadThree = new Thread(() -> {
                    if (counterJ < texts[counterI].length() - 1) {
                        if (texts[counterI].charAt(counterJ) <= texts[counterI].charAt(counterJ + 1)) {
                            countSecond.getAndIncrement();
                        }
                    }
                }
                );
                treadFirst.start();
                treadSecond.start();
                treadThree.start();

                treadFirst.join();
                treadSecond.join();
                treadThree.join();

            }

            if (texts[i].length() == count.get() || texts[i].length() - 1 == countSecond.get() || texts[i].length()  == countThree.get()) {
                switch (texts[i].length()) {
                    case 3:
                        Three++;
                        break;
                    case 4:
                        Four++;
                        break;
                    case 5:
                        Five++;
                        break;
                }
            }
        }

        System.out.println("Красивых слов с длиной 3: " + Three + " шт.");
        System.out.println("Красивых слов с длиной 4: " + Four + " шт.");
        System.out.println("Красивых слов с длиной 5: " + Five + " шт.");

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