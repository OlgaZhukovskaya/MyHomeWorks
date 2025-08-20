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

    //Метод для покупки пордукта

    public boolean buyProduct(Product product){
        if(product == null) return false;
        if(this.money >= product.getPrice()){
            // добавляем пордукт в пакет
            this.products = Arrays.copyOf(this.products, this.products.length +1);
            this.products[this.products.length - 1] = product;
            // списываем деньги
            this.money -= product.getPrice();
            return true;
        }
        // недостаточно денег
        return false;
    }

    @Override
    public String toString(){
        return "Person{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", products=" + Arrays.toString(products)+
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
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
