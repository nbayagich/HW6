package edu.oakland.nbayagich.learningdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerDao playerDao = null;
    private LiveData<List<Player>> players = null;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerDao = GameDatabase.getInstance(application).playerDao();
        players = playerDao.getAllPlayers();

    }

    public LiveData<List<Player>> getPlayersList() {return  players;}

    public void insert(Player player) {playerDao.insert(player);}

    public void insert(Player... players) {playerDao.insert(players);}

    public void update(Player player) {playerDao.update(player);}

    public void deleteAll() {playerDao.deleteAll();}

}
