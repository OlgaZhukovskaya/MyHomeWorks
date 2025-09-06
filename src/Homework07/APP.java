package Homework07;

import java.util.*;

public class APP {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        Map<String, Person> peopleMap = new HashMap<>();


        //Создаем покупателя
        System.out.println("=== ВВОД ПОКУПАТЕЛЕЙ  ===");

        while(true){
            System.out.println("Введите Покупателя или END для завершения");
            String name = scanner.nextLine().trim();
            if(name.equalsIgnoreCase("END")) break;

            if(name.length()<3){
                System.out.println("Имя не может быть короче 3х символов, попорбуйле еще раз");
                continue;
            }

            if (name.isEmpty()){
                System.out.println("Имя не может быть пустым, попорбуйле еще раз");
                continue;
            }

            Double money = null;
            while(true){
                System.out.println("Введите бюджет покупателя: ");
                String moneyStr = scanner.nextLine().trim();
                try{
                    money = Double.parseDouble(moneyStr);
                    if(money<0){
                        System.out.println("Деньги не могут быть отрицательными, попорбуйле еще раз");
                        continue;
                    }
                    break;
                } catch(NumberFormatException e){
                    System.out.println("Некорректный ввод, попорбуйле еще раз");
                }
            }

            Person person = new Person(name, money);
            people.add(person);
            peopleMap.put(name, person);
        }

        // Добавление продуктов

        System.out.println("=== ВВОД ПРОДУКТОВ  ===");
        Map<String, Product> productsMap = new HashMap<>();
        while(true){
            System.out.println("Введите название продукта или END для завершения: ");
            String productName = scanner.nextLine().trim();
            if(productName.equalsIgnoreCase("END")) break;

            if(productName.isEmpty()){
                System.out.println("Название продукта не может быть пустым, попробуйте еще раз");
                continue;
            }

            Double price = null;
            while(true){
                System.out.println("Введите стоимость продукта");
                String priceStr = scanner.nextLine().trim();
                try{
                    price = Double.parseDouble(priceStr);
                    if(price<0){
                        System.out.println("Стоимость продукта не может быть отрицательной, попробуйте еще раз");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Некорректный ввод, попробуйте еще раз");
                }
            }

            try{
                Product product = new Product(productName, price);
                productsMap.put(productName,product);
            } catch (IllegalArgumentException e){
                System.out.println("Ошибка при вводе продукта: "+ e.getMessage());
            }
        }


        // ВВОД ПОКУПОК

        System.out.println("=== ВВОД ПОКУПОК  ===");
        while(true){
            System.out.println("Введите имя покупателя или END для завершения: ");
            String personName = scanner.nextLine().trim();
            if (personName.equalsIgnoreCase("END")) break;

            if(!peopleMap.containsKey(personName)){
                System.out.println("Покупатель с таким именем не найден, попробуйте еще раз ");
                continue;
            }

            System.out.println("Введите название покупаемого продукта ");
            String productName = scanner.nextLine().trim();

            Product product = productsMap.get(productName);
            if(product == null){
                System.out.println("Продукт с таким названием не найден, попробуйте еще раз  ");
                continue;
            }

            // ПОПЫТКА ПОКУПКИ

            Person person = peopleMap.get(personName);
            person.buyProduct(product);
        }

        // ИТОГ
        System.out.println("=== ИТОГ ПОКУПОК ===");
        for( Person p: people){
            System.out.println(p.getName() + ": " + p.getProductNames());
        }

        scanner.close();
    }
}
