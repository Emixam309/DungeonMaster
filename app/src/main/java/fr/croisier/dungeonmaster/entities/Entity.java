package fr.croisier.dungeonmaster.entities;

import java.io.Serializable;

/**
 * Abstract class representing a generic entity in the game.
 */
public abstract class Entity implements Serializable {
    String name;

    /**
     * Constructor for the Entity class.
     *
     * @param name The name of the entity.
     */
    public Entity(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the entity.
     *
     * @return The name of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity.
     *
     * @param name The new name of the entity.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                '}';
    }
}
