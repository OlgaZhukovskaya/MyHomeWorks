package HomeWork2;

//import java.util.Scanner;
//import java.util.Random;

public class TV {
     String marka;
     int diagonal;
     String podsvetka;

    void displayInfo(){
        System.out.printf(marka, diagonal, podsvetka);
    }

    public TV(String marka, int diagonal, String podsvetka){
        this.marka= marka;
        this.diagonal= diagonal;
        this.podsvetka = podsvetka;
    }

    public static void main(String[] args){
        TV TvSet = new TV("HOSHIBA", 33, "НЕТ");
        System.out.println(TvSet.marka);
        System.out.println(TvSet.diagonal);
        System.out.println(TvSet.podsvetka);
    }


  }