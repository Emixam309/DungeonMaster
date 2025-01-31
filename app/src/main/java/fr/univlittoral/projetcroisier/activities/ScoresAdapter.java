package fr.univlittoral.projetcroisier.activities;

import static fr.univlittoral.projetcroisier.game.Score.readScoresFromCSV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.univlittoral.projetcroisier.R;
import fr.univlittoral.projetcroisier.enums.Difficulty;
import fr.univlittoral.projetcroisier.game.Score;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder> {

    private final List<Score> scores;
    private final LayoutInflater inflater;

    public ScoresAdapter(Context context, List<Score> scores) {
        this.scores = readScoresFromCSV(context);
        this.scores.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score current = scores.get(position);
        holder.tvDateTime.setText(current.getDateTime());
        holder.tvPlayerName.setText(current.getPlayerName());
        holder.tvScore.setText(String.valueOf(current.getScore()));
        holder.tvDifficulty.setText(getDifficultyString(current.getDifficulty()));
        holder.tvLevel.setText(String.valueOf(current.getLevel()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDateTime;
        private final TextView tvPlayerName;
        private final TextView tvScore;
        private final TextView tvDifficulty;
        private final TextView tvLevel;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvDifficulty = itemView.findViewById(R.id.tvDifficulty);
            tvLevel = itemView.findViewById(R.id.tvLevel);
        }
    }

    private int getDifficultyString(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return R.string.difficulty_easy;
            case MEDIUM:
                return R.string.difficulty_medium;
            case HARD:
                return R.string.difficulty_hard;
            case CUSTOM:
                return R.string.difficulty_custom;
            default:
                return 0;
        }
    }
}