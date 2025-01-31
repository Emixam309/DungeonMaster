package fr.univlittoral.projetcroisier.entities;

import java.io.Serializable;

import fr.univlittoral.projetcroisier.enums.Difficulty;

/**
 * Class representing a player in the game.
 */
public class Player implements Serializable {
    private String name;
    private int health;
    private int power;

    /**
     * Constructor for the Player class.
     *
     * @param name   The name of the player.
     * @param health The health points of the player.
     * @param power  The power points of the player.
     */
    public Player(String name, int health, int power) {
        this.name = name;
        this.health = health;
        this.power = power;
    }

    /**
     * Constructor for the Player class with default health and power.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this(name, 10, 100);
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the health points of the player.
     *
     * @return The health points of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health points of the player.
     *
     * @param health The new health points of the player.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the power points of the player.
     *
     * @return The power points of the player.
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power points of the player.
     *
     * @param power The new power points of the player.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Initializes the player's attributes based on the difficulty level.
     *
     * @param difficulty   The difficulty level.
     * @param customHealth The custom health points (used if difficulty is CUSTOM).
     * @param customPower  The custom power points (used if difficulty is CUSTOM).
     */
    public void initializeAttributes(Difficulty difficulty, int customHealth, int customPower) {
        switch (difficulty) {
            case EASY:
                setHealth(15);
                setPower(100);
                break;
            case MEDIUM:
            case HARD:
                setHealth(10);
                setPower(100);
                break;
            case CUSTOM:
                setHealth(customHealth);
                setPower(customPower);
                break;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", power=" + power +
                '}';
    }
}
