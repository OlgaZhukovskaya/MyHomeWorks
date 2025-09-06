package Homework07;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price){
        setName(name);
        setPrice(price);
    }

    // геттеры

    public String getName(){
        return name;
    }

    // сеттеры

    public void setName(String name){
        if(name==null || name.trim().length()<3){
            throw new IllegalArgumentException("Название продукта не должно быть короче 3х символов");
        }
        // Проверка, чтобы название не содержало только цифры
        if (name.trim().matches("\\d+")){
            throw new IllegalArgumentException("Название продукта не должно состоять только из цифр");
        }
        this.name=name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if(price<0){
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.price=price;
    }

    @Override
    public String toString() {
        return " купил " + name + " по цене " + price + " рублей ";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                name.equals(product.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, price);
    }

}
