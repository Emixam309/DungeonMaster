package fr.univlittoral.projetcroisier.entities;

public class Enemy extends Entity {
    private int power;

    public Enemy(double difficulty) {
        super("");
        this.power = setRandomPower(difficulty, 50, 150);
        this.name = setRandomNameFromPower(this.power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    private int setRandomPower(double difficulty, int min, int max) {
        return (int) ((Math.random() * (max - min) + min) * difficulty);
    }

    private String setRandomNameFromPower(int power) {
        if (power < 100) {
            String[] lowPowerNames = {"Slime", "Rat", "Bat", "Hornet", "Imp", "Jellyfish", "Plant", "Willowisp"};
            return lowPowerNames[(int) (Math.random() * lowPowerNames.length)];
        } else if (power < 200) {
            String[] mediumPowerNames = {"Cockatrice", "Soldier", "Skeleton", "Swordsman", "Zombie", "Skeleton", "Rogue", "Fanatic", "Ghost", "Puppet", "Scorpion", "Snake", "Spider", "Mimic", "Mage", "Lamia"};
            return mediumPowerNames[(int) (Math.random() * mediumPowerNames.length)];
        } else if (power < 300) {
            String[] highPowerNames = {"Assassin","Captain", "Vampire", "Werewolf", "Ogre", "Orc", "Sahuagin", "Chimera", "Demon", "Gargoyle", "Garuda", "Gazer", "General"};
            return highPowerNames[(int) (Math.random() * highPowerNames.length)];
        } else if (power < 400) {
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
