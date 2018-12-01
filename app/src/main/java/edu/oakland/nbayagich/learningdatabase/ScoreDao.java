package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM scores WHERE scores.pid = :pid AND scores.gid = :gid LIMIT 1")
    Score findScoreById(int pid, int gid);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Score score);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Score... scores);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Score score);

    @Query("DELETE FROM scores")
    void deleteAll();

    @Query("SELECT * FROM scores ORDER BY score ASC")
    LiveData<List<Score>> getAllScores();

    @Query("SELECT * FROM scores ORDER BY score ASC")
    List<Player> getTopPlayers();

}