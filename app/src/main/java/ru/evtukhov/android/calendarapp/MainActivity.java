package ru.evtukhov.android.calendarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndtDateCalendar;
    private Button mBtnOK;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mChooseStartDate = (Button) findViewById(R.id.chooseStartDate);
        mChooseEndDate = (Button) findViewById(R.id.chooseEndDate);
        mStartDateCalendar = (CalendarView) findViewById(R.id.startDateCalendar);
        mEndtDateCalendar = (CalendarView) findViewById(R.id.endtDateCalendar);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mStartDateCalendar.setVisibility(View.GONE);
        mEndtDateCalendar.setVisibility(View.GONE);

        mChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndtDateCalendar.setVisibility(View.GONE);
            }
        });

        mChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndtDateCalendar.setVisibility(View.VISIBLE);
                mStartDateCalendar.setVisibility(View.GONE);
            }
        });

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mStartDateTxt = year+"-"+month+"-"+dayOfMonth;
                String start = getString(R.string.app_startOut) + mStartDateTxt;
                mChooseStartDate.setText(start);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mEndDateTxt = year+"-"+month+"-"+dayOfMonth;
                String end = getString(R.string.app_endOut) + mEndDateTxt;
                mChooseEndDate.setText(end);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartDate > mEndDate){
                    Toast.makeText(MainActivity.this, getString(R.string.app_error), Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText(getString(R.string.app_start));
                    mChooseEndDate.setText(getString(R.string.app_end));
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.app_begin) + mStartDateTxt + getString(R.string.app_finish) + mEndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
