package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import  static utils.Utils.*; //  если пакет то импорт можно, если без пакета - это по умолчанию
// и только через ИМЯ Класса.


public class Main {
    private static final int NAMBER_World = 100_000;

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[NAMBER_World];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindrom = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) && isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        palindrom.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isSameChar(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread alphabet = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isAlphabeticOrder(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        alphabet.start();

        palindrom.join();
        sameChar.join();
        alphabet.join();

        System.out.printf("Красивых слов с длиной 3: " + ATOMIC_3.get() + " штук" +
                "\nКрасивых слов с длиной 4: " + Utils.getAtomic4().get() + " штук" +
                "\nКрасивых слов с длиной 5: " + Utils.getAtomic5().get() + " штук");

    }
}