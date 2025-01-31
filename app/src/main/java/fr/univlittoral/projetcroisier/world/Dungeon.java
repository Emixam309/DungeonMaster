package fr.univlittoral.projetcroisier.world;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Item;
import fr.univlittoral.projetcroisier.enums.ItemType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * Class representing a dungeon in the game.
 */
public class Dungeon implements Serializable {
    private Room[][] rooms;
    private final int rows;
    private final int columns;
    private int level;

    /**
     * Constructor for the Dungeon class.
     *
     * @param difficultyMultiplier The difficulty multiplier.
     * @param rows                 The number of rows in the dungeon.
     * @param columns              The number of columns in the dungeon.
     * @param level                The level of the dungeon.
     */
    public Dungeon(double difficultyMultiplier, int rows, int columns, int level) {
        rooms = new Room[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.level = level;

        // Initialize rooms without enemies
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rooms[i][j] = new Room(null);
            }
        }

        placeRandomItems();
        placeRandomEnemies(difficultyMultiplier);
    }

    /**
     * Places random items in the dungeon.
     */
    private void placeRandomItems() {
        Random random = new Random();
        int itemCount = 0;

        // Ensure there are at least 2 items placed in the dungeon
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

    /**
     * Places random enemies in the dungeon based on the difficulty.
     *
     * @param difficulty The difficulty multiplier.
     */
    private void placeRandomEnemies(double difficulty) {
        for (Room[] room : rooms) {
            for (Room value : room) {
                if (value.getEntity() == null) {
                    value.setEntity(new Enemy(difficulty, level));
                }
            }
        }
    }

    /**
     * Gets the rooms in the dungeon.
     *
     * @return A 2D array of rooms.
     */
    public Room[][] getRooms() {
        return rooms;
    }

    /**
     * Sets the rooms in the dungeon.
     *
     * @param rooms A 2D array of rooms.
     */
    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    /**
     * Gets a specific room in the dungeon.
     *
     * @param i The row index.
     * @param j The column index.
     * @return The room at the specified position.
     */
    public Room getRoom(int i, int j) {
        return rooms[i][j];
    }

    /**
     * Gets the total number of rooms in the dungeon.
     *
     * @return The total number of rooms.
     */
    public int getNumberOfRooms() {
        return rooms.length * rooms[0].length;
    }

    /**
     * Gets the number of rows in the dungeon.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the dungeon.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the number of unvisited rooms in the dungeon.
     *
     * @return The number of unvisited rooms.
     */
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