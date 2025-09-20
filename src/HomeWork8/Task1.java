package HomeWork8;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Task1 {
       // Метод возвращает набор уникальных элементов из списка
        public static Set<String> getUniqueElements(ArrayList<String> list) {
            return new LinkedHashSet<>(list);
        }

        // Стартовый метод программы — точка входа
        public static void main(String[] args) {
            ArrayList<String> elements = new ArrayList<>();
            elements.add("Cat");
            elements.add("Cat");
            elements.add("Cow");
            elements.add("Dog");
            elements.add("Cat");
            elements.add("Mouse");
            elements.add("Tiger");
            elements.add("Cat");
            elements.add("Tiger");
            elements.add("Cow");

            Set<String> uniqueElements = getUniqueElements(elements);
            System.out.println("Уникальные элементы: " + uniqueElements);
        }
}
