package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM games WHERE gid = :id LIMIT 1")
    Game findGameById(int id);

    @Query("SELECT * FROM games WHERE game_name = :gameName LIMIT 1")
    Game findGameByName(String gameName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Game game);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Game... games);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Game game);

    @Query("DELETE FROM games")
    void deleteAll();

    @Query("SELECT * FROM games ORDER BY game_name ASC")
    LiveData<List<Game>> getAllGames();
}
