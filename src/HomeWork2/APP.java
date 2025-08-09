package HomeWork2;

public class APP {

    public static void main(String[] args){
        TV TvSet1 = new TV("SUNSUNG", 38, "НЕТ");
        TV TvSet2 = new TV("HAIER", 40, "ДА");
        TV TvSet3 = new TV("ZARYA", 27, "ДА");

        System.out.println(TvSet1.marka + " " + TvSet1.diagonal + " " + TvSet1.podsvetka);
        System.out.println(TvSet2.marka + " " + TvSet2.diagonal + " " + TvSet2.podsvetka);
        System.out.println(TvSet3.marka + " " + TvSet3.diagonal + " " + TvSet3.podsvetka);
    }
}
