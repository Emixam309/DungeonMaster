package fr.croisier.dungeonmaster.entities;

/**
 * Class representing an enemy in the game.
 */
public class Enemy extends Entity {
    private int power;

    /**
     * Constructor for the Enemy class.
     *
     * @param difficulty The difficulty multiplier.
     * @param level      The level of the enemy.
     */
    public Enemy(double difficulty, int level) {
        super("");
        this.power = setRandomPower(1, 150, difficulty, level);
        this.name = setRandomNameFromPower(this.power);
    }

    /**
     * Gets the power of the enemy.
     *
     * @return The power of the enemy.
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the enemy.
     *
     * @param power The new power of the enemy.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Sets a random power for the enemy based on the difficulty and level.
     *
     * @param min        The minimum power.
     * @param max        The maximum power.
     * @param difficulty The difficulty multiplier.
     * @param level      The level of the enemy.
     * @return The calculated power.
     */
    private int setRandomPower(int min, int max, double difficulty, int level) {
        return (int) ((Math.random() * (max - min) + min) + (((level - 1) * 140) * (difficulty / 2) * Math.exp(0.1 * (level - 1))));
    }

    /**
     * Sets a random name for the enemy based on its power.
     *
     * @param power The power of the enemy.
     * @return The name of the enemy.
     */
    private String setRandomNameFromPower(int power) {
        if (power < 100) {
            String[] lowPowerNames = {"Slime", "Rat", "Bat", "Hornet", "Imp", "Jellyfish", "Plant", "Willowisp", "Spider", "Snake", "Scorpion"};
            return lowPowerNames[(int) (Math.random() * lowPowerNames.length)];
        } else if (power < 250) {
            String[] mediumPowerNames = {"Assassin", "Cockatrice", "Soldier", "Skeleton", "Swordsman", "Zombie", "Skeleton", "Rogue", "Fanatic", "Ghost", "Puppet", "Mimic", "Mage"};
            return mediumPowerNames[(int) (Math.random() * mediumPowerNames.length)];
        } else if (power < 400) {
            String[] highPowerNames = {"Captain", "Vampire", "Werewolf", "Ogre", "Orc", "Sahuagin", "Chimera", "Demon", "Gargoyle", "Garuda", "Gazer", "General", "Lamia"};
            return highPowerNames[(int) (Math.random() * highPowerNames.length)];
        } else if (power < 500) {
            String[] veryHighPowerNames = {"Behemoth", "Cerberus", "Dragon", "Minotaur", "Darklord", "Irongiant", "Vampire", "Succubus"};
            return veryHighPowerNames[(int) (Math.random() * veryHighPowerNames.length)];
        } else {
            String[] ultraHighPowerNames = {"God", "Goddess", "Evilgod", "Darklord_final", "Death"};
            return ultraHighPowerNames[(int) (Math.random() * ultraHighPowerNames.length)];
        }
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
