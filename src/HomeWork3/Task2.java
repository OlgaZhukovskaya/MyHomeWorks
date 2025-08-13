package HomeWork3;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string with arrows --> ");
        String sequence = scanner.nextLine();

        int count = 0;
        int len = sequence.length();

        for (int i=0; i<len-4; i++){
            String substr = sequence.substring(i,i+5);
            if (substr.equals(">>-->") || substr.equals("<--<<")){
                count++;
            }
        }
        System.out.println("We have "+ count + " arrows !!");
    }
}
