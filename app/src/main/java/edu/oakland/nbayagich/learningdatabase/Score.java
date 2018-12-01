package edu.oakland.nbayagich.learningdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

@Entity(tableName = "scores", primaryKeys = {"pid", "gid"})
public class Score {
    //@PrimaryKey(autoGenerate = true)
    // @ColumnInfo(name = "sid")
    // public int id;

    @NonNull
    @Embedded
    public final Player player;

    @NonNull
    @Embedded
    public final Game game;

    @ColumnInfo(name = "score")
    public int score;

    public Score(Player player, Game game, int score) {
        this.player = player;
        this.game = game;
        this.score = score;
    }

    @Ignore
    public String toString() {
        return player.first_name +" scored in " + game.game_name + " " + score + " runs";
    }
}