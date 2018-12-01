package edu.oakland.nbayagich.learningdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM players WHERE pid = :id LIMIT 1")
    Player findPlayerById(int id);

    @Query("SELECT * FROM players WHERE first_name = :firstName")
    Player findPlayerByName(String firstName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Player player);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player... players);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Player player);

    @Query("DELETE FROM players")
    void deleteAll();

    @Query("SELECT * FROM players ORDER BY last_name ASC")
    LiveData<List<Player> > getAllPlayers();
}
