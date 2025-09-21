package HomeWork011_dop.repository;

import HomeWork011_dop.Car;
import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
    void saveCars(List<Car> cars);
    List<Car> loadCars();
}