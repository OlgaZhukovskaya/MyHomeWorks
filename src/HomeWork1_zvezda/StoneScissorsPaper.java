package HomeWork1_zvezda;

import java.util.Random;

public class StoneScissorsPaper {
    public static void main(String[] args) {
        Random random = new Random();

        int VasyaChoice = random.nextInt(3);
        int PetyaChoice = random.nextInt(3);

        String[] options = {"STONE","SCISSORS","PAPER"};

        System.out.println("Vasya chose: " + options[VasyaChoice]);
        System.out.println("Petya chose: " + options[PetyaChoice]);

        if (VasyaChoice == PetyaChoice) {
            System.out.println("DRAW !!!");
        } else if ((VasyaChoice == 0 && PetyaChoice == 1 ) ||
                   (VasyaChoice == 1 && PetyaChoice == 2 ) ||
                   (VasyaChoice == 2 && PetyaChoice == 0 )) {
            System.out.println("Vasya WON !!!");
        } else {
            System.out.println("Petya WON !!!");
        }
    }
}
