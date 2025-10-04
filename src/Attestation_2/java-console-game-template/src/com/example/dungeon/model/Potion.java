package com.example.dungeon.model;

public class Potion extends Item {
    private final int heal;

    public Potion(String name, int heal) {
        super(name);
        this.heal = heal;
    }

    @Override
    public void apply(GameState ctx) {
        Player p = ctx.getPlayer();
        p.setHp(p.getHp() + heal);
        System.out.println("Выпито зелье: +" + heal + " HP. Текущее HP: " + p.getHp());
        p.getInventory().remove(this);
    }

    @Override
    public String describe() {

        // у зелья есть heal, вернём имя и 
        // сколько heal в нём есть
        return super.getName() + " (+" + heal + " HP)" ;
    }

    @Override
    public String save() {
        return super.getName() + ":" + heal;  
    }
}
