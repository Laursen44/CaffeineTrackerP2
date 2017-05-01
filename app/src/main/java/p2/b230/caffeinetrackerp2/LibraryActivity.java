package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LibraryActivity extends AppCompatActivity {

    private Spinner favorites;
    private Spinner soda;
    private Spinner coffee;
    private Spinner tea;
    private Spinner energyDrinks;
    private Spinner other;
    ArrayAdapter<String> favoritesAdapter;
    ArrayAdapter<CharSequence> otherAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        setFavoritesSpinner();
        addListenerToFavoritesSpinner();
        setSodaSpinner();
        addListenerToSodaSpinner();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        setFavoritesSpinner();
        addListenerToFavoritesSpinner();
        setSodaSpinner();
        addListenerToSodaSpinner();
        setCoffeeSpinner();
        addListenerToCoffeeSpinner();
        setTeaSpinner();
        addListenerToTeaSpinner();
        setEnergyDrinksSpinner();
        addListenerToEnergyDrinksSpinner();
        setOtherDrinksSpinner();
        addListenerToOtherSpinner();
    }

    public void makeCostum(View view)
    {

    }

    public void backToLogging(View view)
    {
        Intent intent = new Intent(LibraryActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }

    public void setFavoritesSpinner()
    {
        favorites = (Spinner)findViewById(R.id.library_favorites);
        favoritesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        favoritesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        favorites.setAdapter(favoritesAdapter);
    }

    public void addListenerToFavoritesSpinner()
    {
        favorites = (Spinner) findViewById(R.id.library_favorites);

        favorites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

    void setSodaSpinner()
    {
        soda = (Spinner) findViewById(R.id.library_sodas);

        ArrayAdapter<CharSequence> sodaAdapter =
                ArrayAdapter.createFromResource(this, R.array.Soda,
                        android.R.layout.simple_spinner_item);

        sodaAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        soda.setAdapter(sodaAdapter);
    }

    void addListenerToSodaSpinner()
    {
        soda = (Spinner) findViewById(R.id.library_sodas);

        soda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

    void setCoffeeSpinner()
    {
        coffee = (Spinner) findViewById(R.id.library_coffees);

        ArrayAdapter<CharSequence> coffeeAdapter =
                ArrayAdapter.createFromResource(this, R.array.Coffee,
                        android.R.layout.simple_spinner_item);

        coffeeAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        coffee.setAdapter(coffeeAdapter);
    }

    void addListenerToCoffeeSpinner()
    {
        coffee = (Spinner) findViewById(R.id.library_coffees);

        coffee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

    void setTeaSpinner()
    {
        tea = (Spinner) findViewById(R.id.library_teas);

        ArrayAdapter<CharSequence> teaAdapter =
                ArrayAdapter.createFromResource(this, R.array.Tea,
                        android.R.layout.simple_spinner_item);

        teaAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        tea.setAdapter(teaAdapter);
    }

    void addListenerToTeaSpinner()
    {
        tea = (Spinner) findViewById(R.id.library_teas);

        tea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

    void setEnergyDrinksSpinner()
    {
        energyDrinks = (Spinner) findViewById(R.id.library_energydrinks);

        ArrayAdapter<CharSequence> energyDrinksAdapter =
                ArrayAdapter.createFromResource(this, R.array.EnergyDrinks,
                        android.R.layout.simple_spinner_item);

        energyDrinksAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        energyDrinks.setAdapter(energyDrinksAdapter);
    }

    void addListenerToEnergyDrinksSpinner()
    {
        energyDrinks = (Spinner) findViewById(R.id.library_energydrinks);

        energyDrinks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

    void setOtherDrinksSpinner()
    {
        other = (Spinner) findViewById(R.id.library_others);

        otherAdapter = ArrayAdapter.createFromResource(this, R.array.Other,
                        android.R.layout.simple_spinner_item);

        otherAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);

        other.setAdapter(otherAdapter);
    }

    void addListenerToOtherSpinner()
    {
        other = (Spinner) findViewById(R.id.library_others);


        other.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            boolean[] itemSelectedBool = new boolean[otherAdapter.getCount()];
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l)
            {
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                if(itemSelectedBool[pos])
                    itemSelectedBool[pos] = false;
                else
                    itemSelectedBool[pos] = true;

                if(favoritesAdapter.getCount() == 0)
                    favoritesAdapter.add(itemSelectedInSpinner);

                for(int i = 0; i < favoritesAdapter.getCount(); i++)
                {
                    if(!favoritesAdapter.getItem(i).toString().equals(itemSelectedInSpinner) && itemSelectedBool[pos])
                    {
                        favoritesAdapter.add(itemSelectedInSpinner);
                        break;
                    }else
                    {
                        favoritesAdapter.remove(itemSelectedInSpinner);
                    }
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }
}