package HomeWork011_dop.repository;

import HomeWork011_dop.Car;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {
    private final String filePath;

    public CarsRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Car> getAllCars() {
        return loadCars();
    }

    @Override
    public void saveCars(List<Car> cars) {
        List<String> lines = cars.stream()
                .map(car -> String.format("%s|%s|%s|%d|%d",
                        car.getNumber(), car.getModel(), car.getColor(), car.getMileage(), car.getCost()))
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(filePath), lines);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    cars.add(new Car(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])));
                }
            }
        } catch(IOException e) {
            // Файл может не существовать, в таком случае возвращаем пустой список
        }
        return cars;
    }
}