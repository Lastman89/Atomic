package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[1000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        AtomicInteger Three = new AtomicInteger();
        AtomicInteger Four = new AtomicInteger();
        AtomicInteger Five = new AtomicInteger();
        System.out.println(Arrays.toString(texts));
        // Создаём пул потоков по доступному количеству ядер
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (String text : texts) {
            Runnable task = () -> {
                for (int i = 0; i < text.length(); i++) {
                    int count = 0;
                    int counSecond = 0;
                    for (int j = 0; j < texts[i].length(); j++) {
                        if (texts[i].charAt(j) == texts[i].charAt((texts[i].length() - 1) - j)) {
                            count++;
                        }
                        if (j < texts[i].length() - 1) {
                            if (texts[i].charAt(j) <= texts[i].charAt(j + 1)) {
                                counSecond++;
                            }
                        }

                    }
                    if (texts[i].length() == count || texts[i].length() - 1 == counSecond) {
                        switch (texts[i].length()) {
                            case 3:
                                Three.getAndIncrement();
                            case 4:
                                Four.getAndIncrement();
                            case 5:
                                Five.getAndIncrement();
                        }
                    }
                }
            };
            executor.submit(task);
        }

        System.out.println("Красивых слов с длиной 3: " + Three + " шт.");
        System.out.println("Красивых слов с длиной 4: " + Four + " шт.");
        System.out.println("Красивых слов с длиной 5: " + Five + " шт.");
        executor.shutdown();

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