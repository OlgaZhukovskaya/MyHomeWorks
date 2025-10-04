package com.example.dungeon.model;

import java.util.*;
import java.util.stream.Collectors;

public class Player extends Entity {
    private int attack;
    private final List<Item> inventory = new ArrayList<>();

    public Player(String name, int hp, int attack) {
        super(name, hp);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String describeInventory() {
        StringBuilder sb = new StringBuilder("Инвентарь игрока:");

        if (inventory.isEmpty()) {
            // если ничего нет, вернём что инвентарь пуст
            sb.append("\n").append("<пустой>");
            return sb.toString();
        }

        // группируем по названию класса 
        Map<String, List<Item>> groups = inventory.stream().collect(Collectors.groupingBy(Item::getClassName));
        groups.forEach((type, items) -> {

            // сортируем полученный список предметов
            // по их описанию
            items.sort((Item i1, Item i2)->i1.describe().compareTo(i2.describe()));		
            
            // выводим конкретный тип и список  
            sb.append("\n").append(" - " + type + "(" + items.size() + "):");

	    Iterator<Item> iterator = items.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                sb.append("\n").append("     " + item.describe());
             }
        });
        return sb.toString();
    }

    // похожий метод как в Room, нужно найти
    // в списке инвентаря прдмет по имени
    // если нет, соответственно возвращаем
    // null
    public Item getItem(String name) {

        Iterator<Item> iterator = inventory.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            String iname = item.getName();

               // TODO: непонятно что делать если есть
               // предметы с одинаковым именем
               if (iname.equals(name)) {
                   iterator.remove();
                   return item;
               }
       }
       return null;
    }
}
