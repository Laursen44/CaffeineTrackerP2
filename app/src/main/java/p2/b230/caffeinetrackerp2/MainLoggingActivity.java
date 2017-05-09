package p2.b230.caffeinetrackerp2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class MainLoggingActivity extends AppCompatActivity {

    private Spinner caffeineSubstanceChooser;
    private Spinner caffeineAmountChooser;
    public static float caffeineContent;
    String itemSelectedInAmount;
    String itemSelectedInSubstance;
    CaffeineDatabase cafData;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogging);

        addItemsToCaffeineSubstanceChooserSpinner();
        addItemsToCaffeineAmountChooserSpinner();
        addListenerToCaffeineSubstanceChooserSpinner();
        addListenerToCaffeineAmountChooserSpinner();
        cafData = new CaffeineDatabase();
    }

    protected void onPause() {
        super.onPause();
    }

    public void changeActivityLeft(View view)
    {
        Intent intent = new Intent(MainLoggingActivity.this, StatisticActivity.class);
        startActivity(intent);
    }

    public void changeActivityRight(View view)
    {
        Intent intent = new Intent(MainLoggingActivity.this, StatisticActivity.class);
        startActivity(intent);

    }

    public void libraryActivity(View view)
    {
        Intent intent = new Intent(MainLoggingActivity.this, LibraryActivity.class);
        startActivity(intent);
    }

    void addItemsToCaffeineSubstanceChooserSpinner()
    {
        caffeineSubstanceChooser = (Spinner) findViewById(R.id.caffeine_source_choose);

        ArrayAdapter<CharSequence> caffeineSubstanceChooserAdapter = new
                ArrayAdapter(this,
                        android.R.layout.simple_spinner_dropdown_item, ExpandableListAdapter.favList);

        caffeineSubstanceChooserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        caffeineSubstanceChooser.setAdapter(caffeineSubstanceChooserAdapter);
    }

    void addItemsToCaffeineAmountChooserSpinner()
    {
        caffeineAmountChooser = (Spinner) findViewById(R.id.caffeine_amount_choose);

        ArrayAdapter<CharSequence> caffeineAmountChooserAdapter =
                ArrayAdapter.createFromResource(this, R.array.caffeine_amount,
                        android.R.layout.simple_spinner_dropdown_item);

        caffeineAmountChooserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        caffeineAmountChooser.setAdapter(caffeineAmountChooserAdapter);
    }

    void addListenerToCaffeineSubstanceChooserSpinner()
    {
        caffeineSubstanceChooser = (Spinner) findViewById(R.id.caffeine_source_choose);

        caffeineSubstanceChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l)
            {
                itemSelectedInSubstance = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    void addListenerToCaffeineAmountChooserSpinner()
    {
        caffeineAmountChooser = (Spinner) findViewById(R.id.caffeine_amount_choose);

        caffeineAmountChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l)
            {
                itemSelectedInAmount = parent.getItemAtPosition(pos).toString();

            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }



    public void addCaffToChart(View view)
    {
        caffeineContent = cafData.returnCaffeineContent(itemSelectedInSubstance, itemSelectedInAmount);
        int hourOfDay;
        int minuteOfDay;
        boolean doChartdata = true;
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+2"));
        hourOfDay = now.get(Calendar.HOUR_OF_DAY);
        minuteOfDay = now.get(Calendar.MINUTE);

        Intent intent = new Intent(this, StatisticActivity.class);

        intent.putExtra("CaffeineContent", caffeineContent);
        intent.putExtra("Hour", hourOfDay);
        intent.putExtra("Minute", minuteOfDay);
        intent.putExtra("SetChartData", doChartdata);

        startActivity(intent);
    }
}
