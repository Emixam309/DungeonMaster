package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Entity;

public class Room {
    private Entity entity;
    private boolean visited;

    public Room(Enemy entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public boolean isVisited() {
        return visited;
    }

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
