package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    EditText gameName, gameScore = null;
    Button addButton = null;
    ListView listView = null;
    GameViewModel gameViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_game);
        gameName = findViewById(R.id.gameNameEditText);
        gameScore = findViewById(R.id.weightEditText);
        addButton = findViewById(R.id.addScoreButton);
        listView = findViewById(R.id.listView2);

        gameViewModel = new GameViewModel(this.getApplication());
        LiveData<List<Game>> list = gameViewModel.getGamesList();

        list.observe(this, new android.arch.lifecycle.Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                updateList(games);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = gameName.getText().toString();
                float l = Float.parseFloat(gameScore.getText().toString());
                gameViewModel.insert(new Game(f, l));
            }
        });
    }


    private void updateList(List<Game> list)
    {
        if(list != null)
        {
            ArrayList<String> names = new ArrayList<>(); for (Game g:list) {
                names.add(g.toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
            listView.setAdapter(adapter);
        }
    }

}
