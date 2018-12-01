package edu.oakland.nbayagich.learningdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button playerButton, gameButton, scoreButton, winnnersButton = null;
    ListView topPlayersListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerButton = findViewById(R.id.playerButton);
        gameButton = findViewById(R.id.gameButton);
        scoreButton = findViewById(R.id.scoreButton);
        winnnersButton = findViewById(R.id.winnersButton);
        topPlayersListView = findViewById(R.id.listView);

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                v.getContext().startActivity(intent);
            }
        });

        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                v.getContext().startActivity(intent);
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                v.getContext().startActivity(intent);
            }
        });


        winnnersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Query for top 5 players
                ArrayList<String> topPlayers = new ArrayList<>();
                List<Player> players = GameDatabase.getInstance(v.getContext()).scoreDao().getTopPlayers();
                for(Player player : players)
                {
                    topPlayers.add(player.toString());
                }

                // Update list view with the top players

                UpdateTopPlayersListView(topPlayers);
            }
        });

    }


    protected void UpdateTopPlayersListView(ArrayList <String> topPlayers)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topPlayers);
        topPlayersListView.setAdapter(adapter);
    }
}
