package HomeWork8;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Task1 {
       // Метод возвращает набор уникальных элементов из списка
        public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
            return new LinkedHashSet<>(list);
        }

        // Стартовый метод программы — точка входа
        public static void main(String[] args) {
            ArrayList<Integer> numbers = new ArrayList<>();
            numbers.add(1);
            numbers.add(2);
            numbers.add(2);
            numbers.add(3);
            numbers.add(1);

            Set<Integer> uniqueNumbers = getUniqueElements(numbers);
            System.out.println("Уникальные элементы: " + uniqueNumbers);
        }
}
