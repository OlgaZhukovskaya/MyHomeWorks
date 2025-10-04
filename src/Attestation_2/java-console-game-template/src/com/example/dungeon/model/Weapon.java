package com.example.dungeon.model;

public class Weapon extends Item {
    private final int bonus;

    public Weapon(String name, int bonus) {
        super(name);
        this.bonus = bonus;
    }

    @Override
    public void apply(GameState ctx) {
        var p = ctx.getPlayer();
        p.setAttack(p.getAttack() + bonus);
        System.out.println("Оружие экипировано. Атака теперь: " + p.getAttack());
        p.getInventory().remove(this);
    }

    @Override
    public String describe() {

        // у оружения есть bonus к аттаке
        return super.getName() + " (+" + bonus + ")" ;
    }

    @Override
    public String save() {
        return super.getName() + ":" + bonus;
    }
}
