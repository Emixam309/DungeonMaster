package fr.univlittoral.projetcroisier.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import fr.univlittoral.projetcroisier.R;
import fr.univlittoral.projetcroisier.game.Score;

public class ScoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScoresAdapter adapter;
    private List<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.scores);
        setContentView(R.layout.activity_scores);
        setContentView(R.layout.activity_scores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scores_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.score_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scoreList = Score.readScoresFromCSV(this);
        adapter = new ScoresAdapter(this, scoreList);
        recyclerView.setAdapter(adapter);
    }
}