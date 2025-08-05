package HomeWork1;

import java.util.Scanner;

public class HelloUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name, please, --> ");
        String UserName = scanner.nextLine();
        System.out.println("Hi, " + UserName);
        scanner.close();
    }
}
