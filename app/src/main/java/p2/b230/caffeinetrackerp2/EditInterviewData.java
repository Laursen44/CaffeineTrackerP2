package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditInterviewData extends AppCompatActivity
{

    EditText dayEdit, weekEdit, dayOfWeekEdit, hourOfDayEdit, minuteOfDayEdit, caffeineContentEdit;
    EntryDatabaseHandler db;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interview_data);

    }

    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interview_data);
        initializeEditTexts();

        db = new EntryDatabaseHandler(this, null, null, 3);
    }

    public void addEntryToDatabase(View view)
    {
        int day = Integer.parseInt(dayEdit.getText().toString());
        int week = Integer.parseInt(weekEdit.getText().toString());
        int dayOfWeek = Integer.parseInt(dayOfWeekEdit.getText().toString());
        int hour = Integer.parseInt(hourOfDayEdit.getText().toString());
        int minute = Integer.parseInt(minuteOfDayEdit.getText().toString());
        int caffeine = Integer.parseInt(caffeineContentEdit.getText().toString());

        CaffeineEntry entry = new CaffeineEntry(day, week, dayOfWeek, hour, minute, caffeine);
        db.addEntry(entry);
    }

    public void dropDatabase(View view)
    {

    }

    public void initializeEditTexts()
    {
        dayEdit = (EditText) findViewById(R.id.day_edit);
        weekEdit = (EditText) findViewById(R.id.week_edit);
        dayOfWeekEdit = (EditText) findViewById(R.id.day_of_week_edit);
        hourOfDayEdit = (EditText) findViewById(R.id.hour_of_day_edit);
        minuteOfDayEdit = (EditText) findViewById(R.id.minute_of_day_edit);
        caffeineContentEdit = (EditText) findViewById(R.id.caffeine_content_edit);
    }

    public void back(View view)
    {
        Intent intent = new Intent(this, MainLoggingActivity.class);
        startActivity(intent);
    }
}
