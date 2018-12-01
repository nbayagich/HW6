package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    EditText firstName, lastName = null;
    Button addButton = null;
    ListView listView = null;
    MyDatabaseHelper helper = null;

    PlayerViewModel playerViewModel = null;
    LiveData<List<Player>> listLiveData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        firstName = findViewById(R.id.gameNameEditText);
        lastName = findViewById(R.id.weightEditText);
        addButton = findViewById(R.id.addScoreButton);
        listView = findViewById(R.id.listView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = firstName.getText().toString();
                String l = lastName.getText().toString();

                //helper.createPlayer(f, l);
                //UpdateList();
                playerViewModel.insert(new Player(f, l));
            }
        });

        playerViewModel = new PlayerViewModel(this.getApplication());
        listLiveData = playerViewModel.getPlayersList();
        listLiveData.observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                ArrayList<String> names = new ArrayList<>();
                for(Player player:players)
                {
                    names.add(player.toString());
                }
                UpdateList(names);
            }
        });
        //helper = new MyDatabaseHelper(this);
        //updateList();

    }


    protected void UpdateList(ArrayList<String> list)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

}
