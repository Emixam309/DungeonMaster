package fr.univlittoral.projetcroisier.game;

import android.content.Context;
import android.util.Log;

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

public class Score {
    private static final String FILENAME = "dungeon_master_scores.txt";
    private static final String SEPARATOR = ";";

    private final String playerName;
    private final String dateTime;
    private final int score;
    private final int level;
    private final Difficulty difficulty;

    public Score(String playerName, String dateTime, int score, int level, Difficulty difficulty) {
        this.playerName = playerName;
        this.dateTime = dateTime;
        this.score = score;
        this.level = level;
        this.difficulty = difficulty;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public static void saveScoreToCSV(Context context, Game game) {
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

    public static List<Score> readScoresFromCSV(Context context) {
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