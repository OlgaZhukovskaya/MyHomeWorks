package HomeWork8;

import java.util.HashSet;
import java.util.Set;

public class Task3 {
    // Пересечение множеств (intersection)
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2); // оставляем только общие элементы
        return result;
    }

    // Объединение множеств (union)
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2); // добавляем элементы второго множества
        return result;
    }

    // Относительная разность (элементы set1, отсутствующие в set2)
    public static <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2); // удаляем элементы второго множества
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(1, 2, 3);
        Set<Integer> set2 = Set.of(0, 1, 2, 4);

        System.out.println("Пересечение: " + intersection(set1, set2)); // {1, 2}
        System.out.println("Объединение: " + union(set1, set2)); // {0, 1, 2, 3, 4}
        System.out.println("Относительная разность: " + relativeComplement(set1, set2)); // {3}
    }
}
