package utils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Utils {
    public static AtomicInteger ATOMIC_3 = new AtomicInteger(0);
    private static AtomicInteger ATOMIC_4 = new AtomicInteger(0);
    private static AtomicInteger ATOMIC_5 = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }

    public static boolean isSameChar(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != text.charAt(i - 1))
                return false;
        }
        return true;
    }

    public static boolean isAlphabeticOrder(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1))
                return false;
        }
        return true;
    }

    public static AtomicInteger getAtomic3() {
        return ATOMIC_3;
    }

    public static void setAtomic3(AtomicInteger atomic3) {
        ATOMIC_3 = atomic3;
    }

    public static AtomicInteger getAtomic4() {
        return ATOMIC_4;
    }

    public static void setAtomic4(AtomicInteger atomic4) {
        ATOMIC_4 = atomic4;
    }

    public static AtomicInteger getAtomic5() {
        return ATOMIC_5;
    }

    public static void setAtomic5(AtomicInteger atomic5) {
        ATOMIC_5 = atomic5;
    }


    public static void incrementCounter(int textLength) {
        if (textLength == 3) {
            ATOMIC_3.getAndIncrement();
        } else if (textLength == 4) {
            ATOMIC_4.getAndIncrement();
        } else {
            ATOMIC_5.getAndIncrement();
        }

    }
}
