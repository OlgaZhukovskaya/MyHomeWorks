package HomeWork2;

//import java.util.Scanner;
//import java.util.Random;

public class TV {
    private String marka;
    private int diagonal;
    private String podsvenka;

    public static void main (String[] args){
        TV TvSet = new TV();
        TvSet.marka = "Samsung";
        TvSet.diagonal = 24;
        TvSet.podsvenka = "ЕСТЬ";

        System.out.println("Создан телефизор марки " + TvSet.marka + " с диагональю " + TvSet.diagonal);
        System.out.println("Подсветка: "+ TvSet.podsvenka);
    }
}