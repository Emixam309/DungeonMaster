package fr.croisier.dungeonmaster.game;

import fr.croisier.dungeonmaster.entities.Player;
import fr.croisier.dungeonmaster.enums.Difficulty;
import fr.croisier.dungeonmaster.world.Dungeon;

/**
 * Represents the game state and logic.
 */
public class Game {
    private Player player;
    private Difficulty difficulty;
    private double difficultyMultiplier;
    private Dungeon dungeon;
    private int score;
    private int level;

    /**
     * Constructs a Game instance.
     *
     * @param player the player
     * @param difficulty the difficulty level
     * @param difficultyMultiplier the difficulty multiplier
     * @param rows the number of rows in the dungeon
     * @param columns the number of columns in the dungeon
     * @param level the current level
     * @param score the current score
     */
    public Game(Player player, Difficulty difficulty, double difficultyMultiplier, int rows, int columns, int level, int score) {
        this.player = player;
        this.difficulty = difficulty;
        this.dungeon = new Dungeon(difficultyMultiplier, rows, columns, level);
        this.difficultyMultiplier = difficultyMultiplier;
        this.level = level;
        this.score = score;
    }

    /**
     * Constructs a Game instance.
     *
     * @param player the player
     * @param difficulty the difficulty level
     * @param dungeon the dungeon
     * @param level the current level
     * @param score the current score
     */
    public Game(Player player, Difficulty difficulty, Dungeon dungeon, int level, int score) {
        this.player = player;
        this.difficulty = difficulty;
        this.dungeon = dungeon;
        this.level = level;
        this.score = score;
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     *
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the difficulty level.
     *
     * @return the difficulty level
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level.
     *
     * @param difficulty the difficulty level to set
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the difficulty multiplier.
     *
     * @return the difficulty multiplier
     */
    public double getDifficultyMultiplier() {
        return difficultyMultiplier;
    }

    /**
     * Sets the difficulty multiplier.
     *
     * @param difficultyMultiplier the difficulty multiplier to set
     */
    public void setDifficultyMultiplier(double difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    /**
     * Gets the dungeon.
     *
     * @return the dungeon
     */
    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * Sets the dungeon.
     *
     * @param dungeon the dungeon to set
     */
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    /**
     * Gets the current score.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score.
     *
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increases the score by a specified amount.
     *
     * @param score the amount to increase the score by
     */
    public void increaseScore(int score) {
        this.score += score;
    }

    /**
     * Gets the current level.
     *
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level.
     *
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player +
                ", difficulty=" + difficulty +
                ", difficultyMultiplier=" + difficultyMultiplier +
                ", dungeon=" + dungeon +
                ", score=" + score +
                ", level=" + level +
                '}';
    }
}
