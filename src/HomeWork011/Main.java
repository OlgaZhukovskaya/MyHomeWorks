package HomeWork011;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Создание списка автомобилей
        List<Car> cars = List.of(
                new Car("a123me", "Mercedes", "White", 0, 8300000),
                new Car("b873of", "Volga", "Black", 0, 673000),
                new Car("w487mn", "Lexus", "Grey", 76000, 900000),
                new Car("p987hj", "Volga", "Red", 610, 704340),
                new Car("c987ss", "Toyota", "White", 254000, 761000),
                new Car("o983op", "Toyota", "Black", 698000, 740000),
                new Car("p146op", "BMW", "White", 271000, 850000),
                new Car("u893ii", "Toyota", "Purple", 210900, 440000),
                new Car("l097df", "Toyota", "Black", 108000, 780000),
                new Car("y876wd", "Toyota", "Black", 160000, 1000000)
        );

        // Вывод базы
        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        cars.forEach(c -> System.out.println(c.toString()));

        // Заданные параметры для поиска
        String colorToFind = "Black";
        int mileageToFind = 0;
        int n = 500000;
        int m = 900000;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        // 1) Номера всех автомобилей с заданным цветом или нулевым пробегом
        System.out.println("Номера автомобилей по цвету или пробегу:");
        cars.stream()
                .filter(c -> c.getColor().equalsIgnoreCase(colorToFind) || c.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .distinct()
                .forEach(number -> System.out.print(number + " "));
        System.out.println();

        // 2) Количество уникальных моделей в ценовом диапазоне от n до m
        long uniqueModelsCount = cars.stream()
                .filter(c -> c.getCost() >= n && c.getCost() <= m)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

        // 3) Цвет автомобиля с минимальной стоимостью
        cars.stream()
                .min(Comparator.comparingInt(Car::getCost))
                .ifPresent(c -> System.out.println("Цвет автомобиля с минимальной стоимостью: " + c.getColor()));

        // 4) Средняя стоимость искомой модели modelToFind1
        OptionalDouble avgCostToyota = cars.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(modelToFind1))
                .mapToInt(Car::getCost)
                .average();
        System.out.printf("Средняя стоимость модели %s: %.2f%n",
                modelToFind1, avgCostToyota.orElse(0.0));

        // Средняя стоимость модели modelToFind2
        OptionalDouble avgCostVolga = cars.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(modelToFind2))
                .mapToInt(Car::getCost)
                .average();
        System.out.printf("Средняя стоимость модели %s: %.2f%n",
                modelToFind2, avgCostVolga.orElse(0.0));
    }
}
