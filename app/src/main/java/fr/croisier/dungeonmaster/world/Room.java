package fr.croisier.dungeonmaster.world;

import java.io.Serializable;

import fr.croisier.dungeonmaster.entities.Enemy;
import fr.croisier.dungeonmaster.entities.Entity;

/**
 * Class representing a room in the dungeon.
 */
public class Room implements Serializable {
    private Entity entity;
    private boolean visited;

    /**
     * Constructor for the Room class.
     *
     * @param entity The entity in the room (can be null).
     */
    public Room(Enemy entity) {
        this.entity = entity;
    }

    /**
     * Gets the entity in the room.
     *
     * @return The entity in the room.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the entity in the room.
     *
     * @param entity The new entity in the room.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Checks if the room has been visited.
     *
     * @return True if the room has been visited, false otherwise.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited status of the room.
     *
     * @param visited The new visited status.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Room{" +
                "entity=" + entity +
                ", visited=" + visited +
                '}';
    }
}
