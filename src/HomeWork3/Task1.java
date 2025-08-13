package HomeWork3;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input letter --> ");

        String input = scanner.nextLine();
        char letter = input.charAt(0);

        String keyb = "qwertyuiopasdfghjklzxcvbnm";

        int index = keyb.indexOf(letter);
        int leftIndex = (index - 1 + keyb.length()) % keyb.length();
        char leftLetter = keyb.charAt(leftIndex);

        System.out.println("Letter from the right side --> " + leftLetter);
    }
}
