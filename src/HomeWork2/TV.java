package HomeWork2;

//import java.util.Scanner;
//import java.util.Random;

public class TV {
    private String marka;
    private int diagonal;
    private String podsvenka;

    void displayInfo(){
        System.out.printf(marka, diagonal, podsvenka);
    }

    public TV(String marka, int diagonal, String podsvenka){
        this.marka= marka;
        this.diagonal= diagonal;
        this.podsvenka = podsvenka;
    }

    public static void main(String[] args){
        TV TvSet = new TV("HOSHIBA", 33, "НЕТ");
        System.out.println(TvSet.marka);
        System.out.println(TvSet.diagonal);
        System.out.println(TvSet.podsvenka);
    }

  }