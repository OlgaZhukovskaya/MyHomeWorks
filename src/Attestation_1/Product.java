package Attestation_1;

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
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.name=name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if(price<0){
           throw new IllegalArgumentException("Стоимосто не может быть отрицательной");
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
