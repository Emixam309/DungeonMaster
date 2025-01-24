package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Ennemy;

public class Dungeon {
    private Room[][] rooms;

    public Dungeon() {
        int rows = 4;
        int cols = 4;
        rooms = new Room[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rooms[i][j] = new Room(new Ennemy("Ennemy", 50));
            }
        }
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public Room getRoom(int i, int j) {
        return rooms[i][j];
    }

    public int getNumberOfRooms() {
        return rooms.length * rooms[0].length;
    }

    public int getNumberOfUnvisitedRooms() {
        int count = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (!rooms[i][j].isVisited()) {
                    count++;
                }
            }
        }
        return count;
    }
}
