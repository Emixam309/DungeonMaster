package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Item;
import fr.univlittoral.projetcroisier.enums.ItemType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Dungeon implements Serializable {
    private Room[][] rooms;
    private double difficultyMultiplier;
    private int rows;
    private int columns;

    public Dungeon(double difficultyMultiplier, int rows, int columns) {
        this.difficultyMultiplier = difficultyMultiplier;
        rooms = new Room[columns][rows];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                rooms[i][j] = new Room(null); // Initialize rooms without enemies
            }
        }

        placeRandomItems();
        placeRandomEnemies(difficultyMultiplier);
    }

    private void placeRandomItems() {
        Random random = new Random();
        int itemCount = 0;

        if (rooms.length > 0 && rooms[0].length > 0) {
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

    public double getDifficultyMultiplier() {
        return difficultyMultiplier;
    }

    public void setDifficultyMultiplier(double difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    public Room getRoom(int i, int j) {
        return rooms[i][j];
    }

    public int getNumberOfRooms() {
        return rooms.length * rooms[0].length;
    }

    public int getRows() {
        return rooms[0].length;
    }

    public int getColumns() {
        return rooms.length;
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

    @Override
    public String toString() {
        return "Dungeon{" +
                "rooms=" + Arrays.toString(rooms) +
                ", difficulty=" + difficultyMultiplier +
                '}';
    }
}