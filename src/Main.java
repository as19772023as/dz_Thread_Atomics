import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final AtomicInteger ATOMIC_3 = new AtomicInteger(0);
    private static final AtomicInteger ATOMIC_4 = new AtomicInteger(0);
    private static final AtomicInteger ATOMIC_5 = new AtomicInteger(0);
    private static final int NAMBER_World = 100_000;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[NAMBER_World];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread1 = new Thread(() -> {
            for (String text : sorttingByLenght(texts, 3)) {
                if (IntStream.range(0, text.length() / 2)
                        .noneMatch(i -> text.charAt(i) != text.charAt(text.length() - i - 1))) {
                    ATOMIC_3.getAndIncrement();
                    continue;
                }
                if (isAlphabeticOrder(text)) {
                    ATOMIC_3.getAndIncrement();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String text : sorttingByLenght(texts, 4)) {
                if (IntStream.range(0, text.length() / 2)
                        .noneMatch(i -> text.charAt(i) != text.charAt(text.length() - i - 1))) {
                    ATOMIC_4.getAndIncrement();
                    continue;
                }
                if (isAlphabeticOrder(text)) {
                    ATOMIC_4.getAndIncrement();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String text : sorttingByLenght(texts, 5)) {
                if (IntStream.range(0, text.length() / 2)
                        .noneMatch(i -> text.charAt(i) != text.charAt(text.length() - i - 1))) {
                    ATOMIC_5.getAndIncrement();
                    continue;
                }
                if (isAlphabeticOrder(text)) {
                    ATOMIC_5.getAndIncrement();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.printf("Красивых слов с длиной 3: " + ATOMIC_3.get() + " штук" +
                "\nКрасивых слов с длиной 4: " + ATOMIC_4.get() + " штук" +
                "\nКрасивых слов с длиной 5: " + ATOMIC_5.get() + " штук");

    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static List<String> sorttingByLenght(String[] texts, int namderLenght) {
        List<String> list = Arrays.stream(texts)
                .filter(str -> str.length() == namderLenght)
                .distinct()
                .collect(Collectors.toList());
        return list;
    }

    public static boolean isAlphabeticOrder(String s) {
        int n = s.length();
        char c[] = new char[n];
        for (int i = 0; i < n; i++) {
            c[i] = s.charAt(i);
        }
        Arrays.sort(c);
        for (int i = 0; i < n; i++)
            if (c[i] != s.charAt(i))
                return false;
        return true;
    }
}