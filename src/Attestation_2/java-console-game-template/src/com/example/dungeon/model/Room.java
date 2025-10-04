package com.example.dungeon.model;

import java.util.*;

public class Room {
    private final String name;
    private final String description;
    private final Map<String, Room> neighbors = new HashMap<>();
    private final List<Item> items = new ArrayList<>();
    private Monster monster;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Map<String, Room> getNeighbors() {
        return neighbors;
    }

    // возвращаем следующую комнату или null
    // если по запрошенному направлению
    // комнаты нет
    public Room getNeighborByDirection(String direction) {
       return neighbors.get(direction);
    }

    public List<Item> getItems() {
        return items;
    }

    // возвращаем из комнаты предмет если есть
    // или null если его нет по его имени, если
    // on есть мы его удаляем из комнаты
    public Item getItem(String name) {

        // плохо что в модели items - это list
        // придётся итерироваться, по-хорошему тут
        // тоже можно было бы сделать hash
        Iterator<Item> iterator = items.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            String iname = item.getName();

               // берём первое подошедшее
               // по имени
               if (iname.equals(name)) {
                   iterator.remove();
                   return item;
               }
       } 
       // ничего не нашли
       return null; 
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster m) {
        this.monster = m;
    }

    public String describe() {
        StringBuilder sb = new StringBuilder(name + ": " + description);
        if (!items.isEmpty()) {
            sb.append("\nПредметы: ").append(String.join(", ", items.stream().map(Item::getName).toList()));
        }
        if (monster != null) {
            sb.append("\nВ комнате монстр: ").append(monster.getName()).append(" (ур. ").append(monster.getLevel()).append(")");
        }
        if (!neighbors.isEmpty()) {
            sb.append("\nВыходы: ").append(String.join(", ", neighbors.keySet()));
        }
        return sb.toString();
    }
}
