package HomeWork2;

public class TV {
    private String brend;
    private int diagonal;
    private String color;

    public TV(String brend, int diagonal, String color){
        this.brend = brend;
        this.diagonal = diagonal;
        this.color = color;
    }

    public String getBrend(){
        return brend;
    }
    public void setBrend(String brend){
        this.brend = brend;
    }
    public int getDiagonal(){
        return diagonal;
    }
    public void setDiagonal(int diagonal){
        this.diagonal = diagonal;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void ShowInfo(){
        System.out.println("Бренд: " + brend);
        System.out.println("Диагональ: " + diagonal + " дюймов");
        System.out.println("Цвет: " + color);
}
