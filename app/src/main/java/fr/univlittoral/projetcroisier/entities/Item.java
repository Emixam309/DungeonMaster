package fr.univlittoral.projetcroisier.entities;

import java.util.Random;

import fr.univlittoral.projetcroisier.enums.ItemType;

/**
 * Class representing an item in the game.
 */
public class Item extends Entity {
    private ItemType type;

    /**
     * Constructor for the Item class.
     *
     * @param type The type of the item.
     */
    public Item(ItemType type) {
        super("");
        this.type = type;
        this.name = type.toString();
    }

    /**
     * Gets the type of the item.
     *
     * @return The type of the item.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Sets the type of the item.
     *
     * @param type The new type of the item.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Uses the item on a player.
     *
     * @param player The player on whom the item is used.
     */
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
