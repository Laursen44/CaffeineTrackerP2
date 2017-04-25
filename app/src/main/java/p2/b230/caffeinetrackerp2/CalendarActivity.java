package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void changeActivityLEFT(View view)
    {
        Intent intent = new Intent(CalendarActivity.this, StatisticActivity.class);
        startActivity(intent);
    }

    public void changeActivityRIGHT(View view)
    {
        Intent intent = new Intent(CalendarActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }
}