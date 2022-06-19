package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.expensetracker.fragment.ExpenseFragment;
import com.example.expensetracker.fragment.Incomefragment;

public class viewpageadapter extends FragmentStateAdapter {
    public viewpageadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Incomefragment();

            default:
                return new ExpenseFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
