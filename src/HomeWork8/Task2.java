package HomeWork8;

import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        s = s.toLowerCase();
        t = t.toLowerCase();

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку s: ");
        String s = scanner.nextLine();

        System.out.print("Введите строку t: ");
        String t = scanner.nextLine();

        boolean result = areAnagrams(s, t);
        System.out.println(result);
        scanner.close();
    }
}
