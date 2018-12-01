package edu.oakland.nbayagich.learningdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "games", indices = {@Index(value = "game_name", unique = false)})

public class Game {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "gid")
    public int id;

    @ColumnInfo(name = "game_name")
    public String game_name;

    @ColumnInfo(name = "weight")
    public float weight;

    public Game(String game_name, float weight) {
        this.game_name = game_name;
        this.weight = weight;
    }

    @Ignore
    public String toString() {
        return game_name + ": " + String.format("%.2f", weight);
    }
}