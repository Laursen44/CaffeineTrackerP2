package p2.b230.caffeinetrackerp2;


import android.content.Intent;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import com.github.mikephil.charting.data.LineData;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.TimeZone;


public class StatisticActivity extends AppCompatActivity {

    Description descDay;
    Description descWeek;
    LineChart lineChartDay;
    LineChart lineChartWeek;
    ArrayList<LineData> lineDataDays = new ArrayList<>();
    ArrayList<LineData> lineDataWeeks = new ArrayList<>();
    int activeGraphDay;
    int activeGraphWeek;
    TextView textViewDay;
    TextView textViewWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getDates();
        setTextViews();
        loadGraphData();
        makeGraphDay();
        makeGraphWeek();

    }

    public void changeActivityLEFT(View view)
    {
        Intent intent = new Intent(StatisticActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }

    public void changeActivityRIGHT(View view)
    {
        Intent intent = new Intent(StatisticActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }

    public void makeGraphDay()
    {
        descDay = new Description();
        descDay.setTextSize(14f);
        descDay.setTextColor(Color.rgb(57,23,9));
        descDay.setText("Caffeine in system");


        lineChartDay = (LineChart) findViewById(R.id.graph_day);
        lineChartDay.setBackgroundColor(Color.rgb(216,181,136));
        lineChartDay.setDescription(descDay);
        lineChartDay.setGridBackgroundColor(0);
        lineChartDay.getXAxis().setTextColor(Color.rgb(57,23,9));
        lineChartDay.getXAxis().setTextSize(15f);
        lineChartDay.getAxisLeft().setTextColor(Color.rgb(57,23,9));
        lineChartDay.getAxisLeft().setTextSize(15f);

        //Puts the X axis in the bottom of the chart
        XAxis xAxis = lineChartDay.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis leftAxis = lineChartDay.getAxisLeft();
        YAxis rightAxis = lineChartDay.getAxisRight();

        rightAxis.setEnabled(false);

        lineChartDay.setData(lineDataDays.get(activeGraphDay));
        lineChartDay.invalidate();

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(24);
        leftAxis.setAxisMaximum(500);
        leftAxis.setAxisMinimum(0);
    }

    public void makeGraphWeek()
    {
        descWeek = new Description();
        descWeek.setTextSize(14f);
        descWeek.setTextColor(Color.rgb(57,23,9));
        descWeek.setText("Weekly Caffeine in blood");


        lineChartWeek = (LineChart) findViewById(R.id.graph_week);
        lineChartWeek.setBackgroundColor(Color.rgb(216,181,136));
        lineChartWeek.setDescription(descWeek);
        lineChartWeek.setGridBackgroundColor(0);
        lineChartWeek.getXAxis().setTextColor(Color.rgb(57,23,9));
        lineChartWeek.getXAxis().setTextSize(15f);
        lineChartWeek.getAxisLeft().setTextColor(Color.rgb(57,23,9));
        lineChartWeek.getAxisLeft().setTextSize(15f);


        //Puts the X axis in the bottom of the chart
        XAxis xAxis = lineChartWeek.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis leftAxis = lineChartWeek.getAxisLeft();
        YAxis rightAxis = lineChartWeek.getAxisRight();

        rightAxis.setEnabled(false);

        lineChartWeek.setData(lineDataWeeks.get(activeGraphWeek));
        lineChartWeek.invalidate();

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(168);
        leftAxis.setAxisMaximum(500);
        leftAxis.setAxisMinimum(0);
    }

    public void getDates ()
    {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+2"));
        activeGraphDay = now.get(Calendar.DAY_OF_YEAR);
        activeGraphWeek = now.get(Calendar.WEEK_OF_YEAR);
    }

    public void loadGraphData ()
    {
        EntryDatabaseHandler databaseHandler;
        databaseHandler = new EntryDatabaseHandler(this, null, null, 3);

        lineDataDays.addAll(databaseHandler.loadDataBaseDays());
        lineDataWeeks.addAll(databaseHandler.loadDataBaseWeeks());
    }

    public void changeWeekRight (View view)
    {
        activeGraphWeek ++;
        makeGraphWeek();
        textViewWeek.setText("Week: " + activeGraphWeek);
    }

    public void changeWeekLeft (View view)
    {
        activeGraphWeek --;
        makeGraphWeek();
        textViewWeek.setText("Week: " + activeGraphWeek);
    }

    public void changeDayRight (View view)
    {
        activeGraphDay ++;
        makeGraphDay();
        textViewDay.setText("Day: " + activeGraphDay);
    }

    public void changeDayLeft (View view)
    {
        activeGraphDay --;
        makeGraphDay();
        textViewDay.setText("Day: " + activeGraphDay);
    }

    public void setTextViews ()
    {
        textViewDay = (TextView)findViewById(R.id.day_textview);
        textViewWeek = (TextView)findViewById(R.id.week_textview);
        textViewDay.setText("Day: " + activeGraphDay);
        textViewWeek.setText("Week: " + activeGraphWeek);

    }

}
