package com.example.dungeon.model;

public abstract class Item {
    private final String name;

    protected Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // будем использовать для группировки в
    // Stream API
    public String getClassName() {
      return this.getClass().getSimpleName();
    }

    public abstract void apply(GameState ctx);

    // мы хотим показывать в инвентаре item
    // c определённым параметрами, которые установлены
    // для различных типов
    public abstract String describe();

    // чтобы сохранять данные item каждый 
    // из типов должен уметь себя представить
    // в виде persistant строки
    public abstract String save();
}
