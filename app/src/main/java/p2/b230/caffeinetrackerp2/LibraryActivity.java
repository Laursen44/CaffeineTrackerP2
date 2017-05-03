package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private Spinner favoriteSpinner;
    ArrayAdapter<CharSequence> favoriteSpinnerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        expandableLibrary();
        addItemsToFavoriteSpinner();
        addListenerToFavoriteSpinner();
    }

    public void makeCostum(View view)
    {

    }

    public void backToLogging(View view)
    {
        Intent intent = new Intent(LibraryActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }

    public void expandableLibrary()
    {
        // get the listview
        expandableListView = (ExpandableListView) findViewById(R.id.expand_view);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });

    }

    public void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Soda");
        listDataHeader.add("Coffee");
        listDataHeader.add("Tea");
        listDataHeader.add("EnergyDrinks");
        listDataHeader.add("Other");

        // Adding child data
        List<String> Soda = new ArrayList<String>();
        Soda.add("Coca Cola");
        Soda.add("Faxe Kondi");
        Soda.add("Fanta");
        Soda.add("Sprite");
        Soda.add("Pepsi");

        List<String> Coffee = new ArrayList<String>();
        Coffee.add("Black Coffee");
        Coffee.add("Instant Coffee");
        Coffee.add("Espresso");
        Coffee.add("Cappuccino");
        Coffee.add("Macchiato");

        List<String> Tea = new ArrayList<String>();
        Tea.add("Green Tea");
        Tea.add("Black Tea");
        Tea.add("Light Tea");
        Tea.add("Cammellia");
        Tea.add("Chamomile");

        List<String> EnergyDrinks = new ArrayList<String>();
        EnergyDrinks.add("Red Bull");
        EnergyDrinks.add("Monster");
        EnergyDrinks.add("Cult");
        EnergyDrinks.add("Faxe Booster");
        EnergyDrinks.add("Burn");
        EnergyDrinks.add("Rockstar");

        List<String> Other = new ArrayList<String>();
        Other.add("Caff pill");
        Other.add("Pre-Workout");
        Other.add("Chocolate Dark");
        Other.add("Gum");

        listDataChild.put(listDataHeader.get(0), Soda); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Coffee);
        listDataChild.put(listDataHeader.get(2), Tea);
        listDataChild.put(listDataHeader.get(3), EnergyDrinks);
        listDataChild.put(listDataHeader.get(4), Other);
    }

    public void addItemsToFavoriteSpinner()
    {
        favoriteSpinner = (Spinner) findViewById(R.id.favorites_spinner_library);

        favoriteSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,
                    new ArrayList<String>());
        favoriteSpinner.setAdapter(favoriteSpinnerAdapter);
    }

    public void addListenerToFavoriteSpinner()
    {
        favoriteSpinner = (Spinner) findViewById(R.id.favorites_spinner_library);

        favoriteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
