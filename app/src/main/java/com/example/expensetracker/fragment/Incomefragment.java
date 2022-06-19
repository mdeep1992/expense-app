package com.example.expensetracker.fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.TrackerAdapter;
import com.example.expensetracker.room.Tracker;
import com.example.expensetracker.room.TrackerDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Incomefragment extends Fragment {
    @SuppressLint("NewApi")
    final Calendar myCalendar = Calendar.getInstance();
    List<Tracker> list;
    TrackerAdapter trackerAdapter;
    Button button_income;
    EditText edit_income_des;
    EditText edit_income_amt;
    TextView date_income;
    TextView total_income;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incomefragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date_income = view.findViewById(R.id.income_date_pic);
        list = new ArrayList<>();
        edit_income_amt = view.findViewById(R.id.edt_txt_income_amt);
        edit_income_des = view.findViewById(R.id.edit_tx_income_des);
        button_income = view.findViewById(R.id.add_income_btn);
        total_income=view.findViewById(R.id.total_income);
        button_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = edit_income_des.getText().toString();
                String amount = (edit_income_amt.getText().toString());
                ;
                String date = date_income.getText().toString();
                hideKeybaord(view);
                if (TextUtils.isEmpty(edit_income_des.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter description", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(getActivity(), "Enter the amount", Toast.LENGTH_LONG).show();
                } else if (date.equalsIgnoreCase("Select a date")) {
                    Toast.makeText(getActivity(), "Select a date", Toast.LENGTH_LONG).show();
                } else {
                    Tracker tracker = new Tracker(description, date, Long.parseLong(amount), true);
                    insertTracker(tracker);
                    clearData();
                }
            }

        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_income);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        trackerAdapter = new TrackerAdapter(list);
        recyclerView.setAdapter(trackerAdapter);
        setData();


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("NewApi")
            @Override

            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        date_income.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
    }

    @SuppressLint("NewApi")
    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date_income.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void setData() {
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            list = TrackerDatabase.getInstance(getActivity()).userDao().getIncome();
            int totalIncome = TrackerDatabase.getInstance(getActivity()).userDao().getSumOfIncome();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    trackerAdapter.setList(list);
                    total_income.setText("TOTAL INCOME:RS." + totalIncome);
                    trackerAdapter.notifyDataSetChanged();
                }
            });
        }
    };

    private void insertTracker(Tracker tracker) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TrackerDatabase.getInstance(getActivity()).userDao().InsertUser(tracker);

                setData();
            }
        }).start();
    }

    private void clearData() {
        edit_income_des.setText("");
        edit_income_amt.setText("");
        date_income.setText("select a date");
    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }
}
