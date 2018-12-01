package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    Spinner game, player = null;
    EditText score = null;
    Button addScoreButton = null;
    ListView listView = null;
    ScoreViewModel scoreViewModel = null;
    List<Player> players = null;
    List<Game> games = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        game = findViewById(R.id.gameSpinner);
        player = findViewById(R.id.playerSpinner);
        score = findViewById(R.id.scoreExitText);
        addScoreButton = findViewById(R.id.addScoreButton);
        listView = findViewById(R.id.listView);

        scoreViewModel = new ScoreViewModel(this.getApplication());
        scoreViewModel.getScoresList().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                updateScoreList(scores);
            }
        });

        scoreViewModel.getPlayersList().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> list) {
                players = list;
                playerSppiner();
            }
        });

        scoreViewModel.getGamesList().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> list) {
                games = list;
                gameSppiner();
            }
        });

        addScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player p = players.get(player.getSelectedItemPosition());
                Game g = games.get(game.getSelectedItemPosition());
                scoreViewModel.insert(new Score(p, g, Integer.parseInt(score.getText().toString())));
            }
        });
    }


    private void updateScoreList(List<Score> scores) {
        if (scores != null) {
            ArrayList<String> names = new ArrayList<>();
            for (Score s : scores) {
                names.add(s.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
            listView.setAdapter(adapter);
        }
    }


    private void playerSppiner() {
        if (players != null) {
            ArrayList<String> names = new ArrayList<>();
            for (Player p : players) {
                names.add(p.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
            player.setAdapter(adapter);
        }
    }

    private void gameSppiner() {
        if (games != null) {
            ArrayList<String> names = new ArrayList<>();
            for (Game g : games) {
                names.add(g.toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);

            game.setAdapter(adapter);
        }
    }
}