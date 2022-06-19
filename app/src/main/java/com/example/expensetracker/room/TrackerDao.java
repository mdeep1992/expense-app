package com.example.expensetracker.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrackerDao {

    @Insert
    void InsertUser(Tracker... trackers);
    @Delete
    void DeleteUser(Tracker tracker);
    @Query("SELECT * FROM Tracker WHERE isIncome=1")
    List<Tracker>getIncome();
    @Query("SELECT * FROM Tracker WHERE isIncome=0")
    List<Tracker>getExpenses();
    @Query("SELECT SUM(amount) FROM Tracker WHERE isIncome=1")
    int getSumOfIncome();
    @Query("SELECT SUM(amount) FROM Tracker WHERE isIncome=0")
    int getSumOfExpense();

}
