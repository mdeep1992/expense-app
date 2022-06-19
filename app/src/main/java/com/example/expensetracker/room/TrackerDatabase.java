package com.example.expensetracker.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Tracker.class},version = 1)
public abstract class TrackerDatabase extends RoomDatabase {
    public abstract TrackerDao userDao();
    private static TrackerDatabase INSTANCE;
    public static TrackerDatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TrackerDatabase.class,"Tracker")
                    .allowMainThreadQueries()
                    .build();

        }
return INSTANCE;
    }


}
