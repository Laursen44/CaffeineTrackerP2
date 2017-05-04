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


public class StatisticActivity extends AppCompatActivity {

    Description desc;
    LineChart lineChart;

    public double power(double x, double y)
    {
        double z=  x * (y*x);
        return z;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);




    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        desc = new Description();
        desc.setText("Caffeine in blood");
        lineChart = (LineChart) findViewById(R.id.graph);
        lineChart.setBackgroundColor(Color.rgb(255,248,219));
        lineChart.setDescription(desc);
        lineChart.setGridBackgroundColor(Color.rgb(216,181,136));

        //Puts the X axis in the bottom of the chart
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        ArrayList<Entry> xAXES = new ArrayList<>();
        int timeX;
        int halfTime = 5;
        int numDataPoints;
        double cmg =400;
        double mmg = cmg/2;

        Entry entry = new Entry();


        double fgd;

        for (int i = 0; i < 24; i++)
        {
            double x=i;
            double halftimeCaffeine = i/halfTime;
            double y = Math.pow(0.5, halftimeCaffeine);
            double testy = cmg * y;
            System.out.println(testy);
        }




        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(24);
        lineChart.setVisibleXRangeMinimum(0f);
        lineChart.setVisibleXRangeMaximum(100f);
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

    public void setChartData (float caffeineNmbr, float hour, float min)
    {

    }
}
