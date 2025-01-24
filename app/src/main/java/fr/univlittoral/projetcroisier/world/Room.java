package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Ennemy;

public class Room {
    private Ennemy ennemy;
    private boolean visited;

    public Room(Ennemy ennemy) {
        this.ennemy = ennemy;
    }

    public Ennemy getEnnemy() {
        return ennemy;
    }

    public void setEnnemy(Ennemy ennemy) {
        this.ennemy = ennemy;
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
                "ennemy=" + ennemy +
                ", visited=" + visited +
                '}';
    }
}
