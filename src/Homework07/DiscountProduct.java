package Homework07;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {
    private double discount; //размер скидки
    private LocalDate expiryDate; // дата окончаия действия скидки

    public DiscountProduct(String name, double price, double discount, LocalDate expiryDate){
        super(name, price);
        setDiscount(discount);
        this.expiryDate=expiryDate;
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount) {
        if(discount<=0 || discount>= getPrice()){
            throw new IllegalArgumentException("Размер скидки должен быть положительным и меньше цены товара");
        }
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Метод для получения текущей стоимости с учетом скидки и срока

    public double getCurrentPrice(){
        if(LocalDate.now().isAfter(expiryDate)){
            //Скидка закончилась
            return getPrice();
        } else{
            // Скидка активна, цена с учетом скидки
            return getPrice() - discount;
        }
    }

    @Override
    public String toString(){
        return " купил " + getName() + " по цене " + getCurrentPrice() + " рублей, скидка составила " + discount ;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof DiscountProduct)) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that =(DiscountProduct) o;
        return Double.compare(that.discount, discount) == 0 &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), discount, expiryDate);
    }
}
