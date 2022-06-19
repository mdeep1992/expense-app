package com.example.expensetracker.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tracker {
    @PrimaryKey(autoGenerate = true)
    private int Uid;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "amount")
    private long amount;
    private boolean isIncome;

    public Tracker(String description, String date, long amount, boolean isIncome) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.isIncome = isIncome;
    }

    public int getUid() {
        return Uid;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}
