package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Item;
import fr.univlittoral.projetcroisier.enums.ItemType;

import java.util.Random;

public class Dungeon {
    private Room[][] rooms;
    private double difficulty;

    public Dungeon(double difficulty) {
        int rows = 4;
        int cols = 4;
        this.difficulty = difficulty;
        rooms = new Room[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rooms[i][j] = new Room(null); // Initialize rooms without enemies
            }
        }

        placeRandomItems();
        placeRandomEnemies(difficulty);
    }

    private void placeRandomItems() {
        Random random = new Random();
        int itemCount = 0;

        while (itemCount < 2) {
            int row = random.nextInt(rooms.length);
            int col = random.nextInt(rooms[0].length);

            if (rooms[row][col].getEntity() == null) {
                ItemType itemType = random.nextBoolean() ? ItemType.HEALTH_POTION : ItemType.POWER_CHARM;
                rooms[row][col].setEntity(new Item(itemType));
                itemCount++;
            }
        }
    }

    private void placeRandomEnemies(double difficulty) {
        for (Room[] room : rooms) {
            for (Room value : room) {
                if (value.getEntity() == null) {
                    value.setEntity(new Enemy(difficulty));
                }
            }
        }
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
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