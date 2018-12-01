package edu.oakland.nbayagich.learningdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private GameDao gameDao = null;
    private LiveData<List<Game>> games = null;

    public GameViewModel(@NonNull Application application) {
        super(application);
        gameDao = GameDatabase.getInstance(application.getApplicationContext()).gameDao();
        games = gameDao.getAllGames();
    }

    public LiveData<List<Game>> getGamesList() {
        return games;
    }

    public void insert(Game game) {
        gameDao.insert(game);
    }

    public void insert(Game... games) {
        gameDao.insert(games);
    }

    public void update(Game game) {
        gameDao.update(game);
    }

    public void deleteAll() {
        gameDao.deleteAll();
    }
}
