package edu.oakland.nbayagich.learningdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ScoreViewModel extends AndroidViewModel {
    private ScoreDao scoreDao = null;
    private PlayerDao playerDao = null;
    private GameDao gameDao = null;
    private LiveData<List<Score>> scores = null;
    private LiveData<List<Player>> players = null;
    private LiveData<List<Game>> games = null;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        scoreDao = GameDatabase.getInstance(application.getApplicationContext()).scoreDao();
        playerDao = GameDatabase.getInstance(application.getApplicationContext()).playerDao();
        gameDao = GameDatabase.getInstance(application.getApplicationContext()).gameDao();
        scores = scoreDao.getAllScores();
        players = playerDao.getAllPlayers();
        games = gameDao.getAllGames();
    }

    public LiveData<List<Score>> getScoresList() {
        return scores;
    }

    public LiveData<List<Player>> getPlayersList() {
        return players; }

    public LiveData<List<Game>> getGamesList() {
        return games; }

    public void insert(Score score) {
        scoreDao.insert(score); }

    public void insert(Score... scores) {
        scoreDao.insert(scores); }

    public void update(Score score){
        scoreDao.update(score); }

    public void deleteAll(){
        scoreDao.deleteAll();
    }
}
