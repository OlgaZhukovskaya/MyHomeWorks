package Attestation_1;

import java.util.Scanner;

public class APP {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Создаем покупателя
        System.out.println("Введите имя покупателя: ");
        String name = scanner.nextLine();
        while (name.trim().length()<3 || name.trim().isEmpty()){
            System.out.println("Некорректное имя. Введите имя длиной не менее 3х символов");
            name = scanner.nextLine();
        }

        System.out.println("Введите сумму денег покупателя: ");
        double money = 0;
        while(true){
            try {
                money = Double.parseDouble(scanner.nextLine());
                if (money<0){
                    System.out.println("Деньги не могут быть отрицательными, попробуйте еще раз" );
                } else {
                    break;
                }
            } catch(NumberFormatException e){
                System.out.println("Некорректный ввод, попробуйте еще раз: ");
            }
        }

        Person buyer = new Person(name, money);

        // Добавление продуктов

        System.out.println("Введите продукты для покупателя. Формат: название - стоимость");
        System.out.println("Чтобы закончить добавление продуктов введите слово END ");

        while(true){
            System.out.println("Введите название продукта или END для завершения ");
            String productName = scanner.nextLine().trim();

            if(productName.equalsIgnoreCase("END")){
                break;
            }

            System.out.println("Введите стоимость продукта: ");
            double price;
            try{
                price = Double.parseDouble(scanner.nextLine());
                if(price<0){
                    System.out.println("Стоимость не может быть отрицательной. Попробуйте еще раз ");
                    continue;
                }
            } catch (NumberFormatException e){
                System.out.println("Некорректный ввод стоимости. попробуйте еще раз.");
                continue;
            }

            try{
                Product product = new Product(productName, price);
                // пытаемся купить продукт
                if(buyer.buyProduct(product)){
                    System.out.println("Продукт добавлен в пакет: " + product);
                } else {
                    System.out.println("Недостаточно денег для продукта: " + product);
                }
            } catch(IllegalArgumentException e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // ИТОГ

        System.out.println("\n === Итог покупки ===");
        System.out.println(buyer);

        scanner.close();
    }
}
