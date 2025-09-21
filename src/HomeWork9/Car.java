package HomeWork9;

import java.util.*;
import java.util.stream.*;

public class Car {
    // Поля класса с приватным модификатором доступа
    private String number;
    private String model;
    private String color;
    private int mileage;
    private int cost;

    // Конструктор
    public Car(String number, String model, String color, int mileage, int cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    // Геттеры и сеттеры
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    // Переопределение toString
    @Override
    public String toString() {
        return String.format("%s %s %s %d %d", number, model, color, mileage, cost);
    }
}
