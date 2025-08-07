package HomeWork2;

import java.util.Scanner;
import java.util.Random;

public class TV {
    private String brend;
    private int diagonal;
    private String color;

    public TV(String brend, int diagonal, String color){
        this.brend = brend;
        this.diagonal = diagonal;
        this.color = color;
    }

    public String getBrend(){
        return brend;
    }
    public void setBrend(String brend){
        this.brend = brend;
    }
    public int getDiagonal(){
        return diagonal;
    }
    public void setDiagonal(int diagonal){
        this.diagonal = diagonal;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void ShowInfo(){
        System.out.println("Бренд: " + brend);
        System.out.println("Диагональ: " + diagonal + " дюймов");
        System.out.println("Цвет: " + color);
}

// Класс App с методом main


    public class App {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            // Создание экземпляров с помощью конструктора
            TV tv1 = new TV("Samsung", 55, "Black");
            TV tv2 = new TV("LG", 42, "Red");

            // Создание экземпляра с параметрами, вводимыми с клавиатуры
            System.out.print("Введите бренд телевизора: ");
            String brend = scanner.nextLine();

            System.out.print("Введите диагональ (в дюймах): ");
            int giagonal = scanner.nextInt();

            System.out.print("Введите цвет телевизора: ");
            String color = scanner.nextLine();

            System.out.println("\nИнформация о первом телевизоре:");
            tv1.ShowInfo();
            System.out.println("\nИнформация о втором телевизоре:");
            tv2.ShowInfo();
        }
    }