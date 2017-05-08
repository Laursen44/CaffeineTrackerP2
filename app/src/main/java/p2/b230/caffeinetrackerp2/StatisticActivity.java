package p2.b230.caffeinetrackerp2;


import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import static p2.b230.caffeinetrackerp2.R.id.graph;
import static p2.b230.caffeinetrackerp2.R.id.line1;


public class StatisticActivity extends AppCompatActivity {

    Description desc;
    LineChart lineChart;
    float caffeineContent;
    float hour;
    float minute;
    boolean doChartData = false;
    List<Entry> graphData = new ArrayList<>();
    LineDataSet list1;
    LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        Bundle bundle = getIntent().getExtras();

        extractBundleInfo(bundle);

        LineData line = setLineData(caffeineContent, hour, minute, doChartData);
        if(line != null)
            makeGraph(line);
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

    public LineData setLineData (float caffeineNumber, float hour, float min, boolean active)
    {
        if(!active) return null;

        min = map(min, 0, 59, 0, 99);
        float minInHours = min/100;
        float xConsumption = hour + minInHours;
        double tempY;
        float y = 0;
        double fifthRoot = 1.0/5;
        double halfTimeConstant = 0.5;
        double timeSinceConsumption;

        for(float x = 0; x < 24; x+=0.1)
        {
            if(x >= xConsumption)
            {
                timeSinceConsumption = x-xConsumption;

                tempY = Math.pow(Math.pow(halfTimeConstant, timeSinceConsumption), fifthRoot);

                y = caffeineNumber * (float)tempY;
            }

            if(y <= 0) y = 0;

            Entry dataPoint = new Entry(x, y);
            graphData.add(dataPoint);
        }

        list1 = new LineDataSet(graphData, "List one");
        list1.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineData = new LineData(list1);

        active = false;

        return lineData;
    }

    public void makeGraph(LineData linedata)
    {
        desc = new Description();
        desc.setTextSize(14f);
        desc.setTextColor(Color.rgb(57,23,9));
        desc.setText("Caffeine in blood");


        lineChart = (LineChart) findViewById(R.id.graph);
        lineChart.setBackgroundColor(Color.rgb(216,181,136));
        lineChart.setDescription(desc);
        lineChart.setGridBackgroundColor(0);
        list1.setCircleColor(Color.rgb(57,23,9));
        list1.setFillColor(Color.rgb(176,64,64));
        list1.setColors(Color.rgb(57,23,9));

        list1.setCircleColorHole(Color.rgb(57,23,9));
        lineChart.getXAxis().setTextColor(Color.rgb(57,23,9));
        lineChart.getXAxis().setTextSize(15f);
        lineChart.getAxisLeft().setTextColor(Color.rgb(57,23,9));
        lineChart.getAxisLeft().setTextSize(15f);

        //Puts the X axis in the bottom of the chart
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();

        rightAxis.setEnabled(false);

        lineChart.setData(linedata);
        lineChart.invalidate();

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(24);
        leftAxis.setAxisMaximum(500);
        leftAxis.setAxisMinimum(0);
        lineChart.setVisibleXRangeMinimum(0f);
        lineChart.setVisibleXRangeMaximum(100f);
    }



    public float map (float value, float start1, float stop1, float start2, float stop2)
    {
    float remap = start2 + (value - start1) * (start2 - stop2) / (start1 - stop1);
        return remap;
    }

    public void extractBundleInfo(Bundle bundle)
    {
        caffeineContent = bundle.getFloat("CaffeineContent");
        hour = bundle.getInt("Hour");
        minute = bundle.getInt("Minute");
        doChartData = bundle.getBoolean("SetChartData");
    }

}
