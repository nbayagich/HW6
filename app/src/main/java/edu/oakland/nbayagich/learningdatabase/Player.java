package edu.oakland.nbayagich.learningdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "players", indices = {@Index(value = "first_name", unique = false)})

public class Player {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid")
    public int id;

    @ColumnInfo(name = "first_name")
    public String first_name;

    @ColumnInfo(name = "last_name")
    public String last_name;

    public Player(String first_name, String last_name)
    {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String toString()
    {
        return last_name + ", " + first_name;
    }

}
