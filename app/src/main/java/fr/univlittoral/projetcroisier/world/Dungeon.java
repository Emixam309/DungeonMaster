package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Item;
import fr.univlittoral.projetcroisier.enums.ItemType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Dungeon implements Serializable {
    private Room[][] rooms;
    private final int rows;
    private final int columns;
    private int level;

    public Dungeon(double difficultyMultiplier, int rows, int columns, int level) {
        rooms = new Room[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.level = level;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rooms[i][j] = new Room(null); // Initialize rooms without enemies
            }
        }

        placeRandomItems();
        placeRandomEnemies(difficultyMultiplier);
    }

    private void placeRandomItems() {
        Random random = new Random();
        int itemCount = 0;

        if (rows > 0 && columns > 0) {
            while (itemCount < 2) {
                int row = random.nextInt(rows);
                int col = random.nextInt(columns);

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
                    value.setEntity(new Enemy(difficulty, level));
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

    public Room getRoom(int i, int j) {
        return rooms[i][j];
    }

    public int getNumberOfRooms() {
        return rooms.length * rooms[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
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
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}