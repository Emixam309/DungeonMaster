package fr.univlittoral.projetcroisier.game;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univlittoral.projetcroisier.enums.Difficulty;

/**
 * Represents a score entry in the game.
 */
public class Score {
    private static final String FILENAME = "dungeon_master_scores.txt";
    private static final String SEPARATOR = ";";

    private final String playerName;
    private final String dateTime;
    private final int score;
    private final int level;
    private final Difficulty difficulty;

    /**
     * Constructs a Score instance.
     *
     * @param playerName the player's name
     * @param dateTime the date and time of the score
     * @param score the score value
     * @param level the level achieved
     * @param difficulty the difficulty level
     */
    public Score(String playerName, String dateTime, int score, int level, Difficulty difficulty) {
        this.playerName = playerName;
        this.dateTime = dateTime;
        this.score = score;
        this.level = level;
        this.difficulty = difficulty;
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the date and time of the score.
     *
     * @return the date and time of the score
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Gets the score value.
     *
     * @return the score value
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the level achieved.
     *
     * @return the level achieved
     */
    public int getLevel() {
        return level;
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
     * Saves the current game score to a file.
     *
     * @param context the context
     * @param game the game instance
     */
    public static void saveScoreToFile(Context context, Game game) {
        File file = new File(context.getFilesDir(), FILENAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            long timestamp = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            sb.append(timestamp).append(SEPARATOR);
            sb.append(game.getPlayer().getName()).append(SEPARATOR);
            sb.append(game.getScore()).append(SEPARATOR);
            sb.append(game.getDifficulty()).append(SEPARATOR);
            sb.append(game.getLevel()).append(SEPARATOR);
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads scores from a file.
     *
     * @param context the context
     * @return a list of scores
     */
    public static List<Score> readScoresFromFile(Context context) {
        File file = new File(context.getFilesDir(), FILENAME);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<Score> scoreList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(SEPARATOR);
                long timestamp = Long.parseLong(details[0]);
                String dateTime = formatter.format(new Date(timestamp));
                String playerName = details[1];
                int score = Integer.parseInt(details[2]);
                String difficulty = details[3];
                int level = Integer.parseInt(details[4]);

                scoreList.add(new Score(playerName, dateTime, score, level, Difficulty.valueOf(difficulty)));
            }
            return scoreList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
