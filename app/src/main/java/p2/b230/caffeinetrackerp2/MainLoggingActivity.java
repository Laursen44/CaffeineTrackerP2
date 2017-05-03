package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainLoggingActivity extends AppCompatActivity {

    private Spinner caffeineSubstanceChooser;
    private Spinner caffeineAmountChooser;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogging);

        addItemsToCaffeineSubstanceChooserSpinner();
        addItemsToCaffeineAmountChooserSpinner();
        addListenerToCaffeineSubstanceChooserSpinner();
        addListenerToCaffeineAmountChooserSpinner();
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
                        android.R.layout.simple_list_item_single_choice, ExpandableListAdapter.favList);

        caffeineSubstanceChooserAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        caffeineSubstanceChooser.setAdapter(caffeineSubstanceChooserAdapter);
    }

    void addItemsToCaffeineAmountChooserSpinner()
    {
        caffeineAmountChooser = (Spinner) findViewById(R.id.caffeine_amount_choose);

        ArrayAdapter<CharSequence> caffeineAmountChooserAdapter =
                ArrayAdapter.createFromResource(this, R.array.caffeine_amount,
                        android.R.layout.simple_list_item_single_choice);

        caffeineAmountChooserAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        caffeineAmountChooser.setAdapter(caffeineAmountChooserAdapter);
    }

    void addListenerToCaffeineSubstanceChooserSpinner()
    {
        caffeineSubstanceChooser = (Spinner) findViewById(R.id.caffeine_source_choose);

        caffeineSubstanceChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l)
            {
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
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
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }
}
