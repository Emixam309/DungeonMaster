package fr.univlittoral.projetcroisier.entities;

public class Enemy extends Entity {
    private int power;

    public Enemy(double difficulty, int level) {
        super("");
        this.power = setRandomPower(1, 150, difficulty, level);
        this.name = setRandomNameFromPower(this.power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    private int setRandomPower(int min, int max, double difficulty, int level) {
        return (int) ((Math.random() * (max - min) + min) + (((level - 1) * 140) * (difficulty / 2) * Math.exp(0.1 * (level - 1))));
    }

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
