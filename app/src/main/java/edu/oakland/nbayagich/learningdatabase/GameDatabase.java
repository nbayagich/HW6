package edu.oakland.nbayagich.learningdatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Player.class, Game.class, Score.class}, version = 1, exportSchema = false)

public abstract class GameDatabase extends RoomDatabase {
    static private GameDatabase INSTANCE = null;
    static private final String DB_NAME = "gamestat.db";

    static public GameDatabase getInstance(Context context)
    {
        if(INSTANCE == null){
            try {
                synchronized (GameDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GameDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration()
                                .addCallback(new RoomDatabase.Callback() {
                                    @Override
                                    public void onCreate(SupportSQLiteDatabase db) {
                                        super.onCreate(db);
                                    }
                                }).build();
                    }
                }
            }catch (Exception e) {}
        }
        return INSTANCE;}

        public abstract PlayerDao playerDao();
        public abstract GameDao gameDao();
        public abstract ScoreDao scoreDao();

    // Migrations
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("drop table if exists games");
        }
    };

}
