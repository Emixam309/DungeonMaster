package fr.univlittoral.projetcroisier.entities;

import java.util.Random;

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

    public void useItem(Player player) {
        switch (type) {
            case HEALTH_POTION:
                // Increase player health
                int healthRestored = new Random().nextInt(3) + 1;
                player.setHealth(player.getHealth() + healthRestored);
                break;
            case POWER_CHARM:
                // Increase player power
                int powerBoost = new Random().nextInt(6) + 5;
                player.setPower(player.getPower() + powerBoost);
                break;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
