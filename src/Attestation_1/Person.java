package Attestation_1;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private Product[] products;  // пакет продуктов

    //Конструктор
    public Person(String name, double money){
        setName(name);
        setMoney(money);
        this.products = new Product[0]; //Изначально пустой пакет
    }

    // Геттеры

    public String getName(){
        return name;
    }

    public String getProductNames(){
        if (products.length == 0) {
            return "Нет покупок";
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0; i< products.length; i++){
            sb.append(products[i].getName());
            if(i<products.length-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public double getMoney(){
        return money;
    }

    public Product[] getProducts(){
        return products;
    }

    //Сеттеры

    public void setName(String name){
        if (name == null || name.trim().length()<3){
            throw new IllegalArgumentException("Имя должно быть не менее 3х символов и не быть пустым");
        }
        this.name=name;
    }

    public void setMoney(double money){
        if (money<0){
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money=money;
    }

    // Попытка купить продукт

    public void buyProduct(Product product){
        if(product == null) return;
        if(this.money - product.getPrice()>=0){
            // добавляем продукт в пакет
            this.products = Arrays.copyOf(this.products, this.products.length +1);
            this.products[this.products.length - 1] = product;
            // списываем деньги
            this.money -= product.getPrice();
            System.out.println(this.name+" купил "+ product.getName());
        } else {
            System.out.println(this.name+" не может себе позводить "+ product.getName());
        }
    }

    @Override
    public String toString(){
        return "Person{" +
                "name=" + name +
                "money=" + money +
                "product = " + Arrays.toString(products);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Arrays.equals(products, person.products);
    }

    @Override
    public int hashCode(){
        int result = Objects.hash(name, money);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }

}
