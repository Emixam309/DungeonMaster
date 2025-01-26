package fr.univlittoral.projetcroisier.entities;

import fr.univlittoral.projetcroisier.enums.ItemType;

public class Item extends Entity {
    private ItemType type;

    public Item(ItemType type) {
        super("");
        this.type = type;
        this.name = type.toString();
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
