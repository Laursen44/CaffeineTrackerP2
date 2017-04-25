package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainLoggingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogging);


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void changeActivityLeft(View view)
    {
        Intent intent = new Intent(MainLoggingActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void changeActivityRight(View view)
    {
        Intent intent = new Intent(MainLoggingActivity.this, StatisticActivity.class);
        startActivity(intent);
    }
}
