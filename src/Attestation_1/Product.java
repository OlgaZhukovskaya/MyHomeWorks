package Attestation_1;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        if(price<0) {
            throw new IllegalArgumentException("Стоимосто не может быть отрицательной");
        }
        this.name=name;
        this.price=price;
    }

    // геттеры

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    // сеттеры

    public void setName(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.name=name;
    }

    public void setPrice(double price){
        if(price<0){
           throw new IllegalArgumentException("Стоимосто не может быть отрицательной");
        }
        this.price=price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
               Objects.equals(name, product.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, price);
    }

}
