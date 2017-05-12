package p2.b230.caffeinetrackerp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class EntryDatabaseHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "entryDatabase.db";
    public static final String TABLE_ENTRY = "entries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_WEEK = "week";
    public static final String COLUMN_DAYOFWEEK = "dayOfWeek";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_CAFFEINECONTENT = "caffContent";

    public EntryDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String query = " CREATE TABLE IF NOT EXISTS " + TABLE_ENTRY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DAY + " INTEGER, " +
                COLUMN_WEEK + " INTEGER, " +
                COLUMN_DAYOFWEEK + " INTEGER, " +
                COLUMN_HOUR + " INTEGER, " +
                COLUMN_MINUTE + " INTEGER, " +
                COLUMN_CAFFEINECONTENT + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ENTRY);
        onCreate(db);
    }


    //add a new row to the database
    public void addEntry(CaffeineEntry caffeineEntry)
    {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DAY, caffeineEntry.getDay());
        values.put(COLUMN_WEEK, caffeineEntry.getWeek());
        values.put(COLUMN_DAYOFWEEK, caffeineEntry.getDayOfWeek());
        values.put(COLUMN_HOUR, caffeineEntry.getHour());
        values.put(COLUMN_MINUTE, caffeineEntry.getMinute());
        values.put(COLUMN_CAFFEINECONTENT, caffeineEntry.getCaffContent());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ENTRY, null, values);
        db.close();
    }

    public void dropTable()
    {

    }

    public ArrayList<LineData> loadDataBaseDays()
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ArrayList<CaffeineEntry>> days = new ArrayList<>(365);

        for (int i = 0; i < 365; i++)
        {
            ArrayList<CaffeineEntry> tempList = new ArrayList<>();
            days.add(i, tempList);
        }

        ArrayList<LineData> resultLineDataList = new ArrayList<>();
        boolean no = true;

        String[] columns = new String[]{COLUMN_ID, COLUMN_DAY, COLUMN_WEEK, COLUMN_DAYOFWEEK, COLUMN_HOUR, COLUMN_MINUTE, COLUMN_CAFFEINECONTENT};
        Cursor c = db.query(TABLE_ENTRY, columns, null, null, null, null, null);

        int tempDay, tempWeek, tempDayOfWeek, tempHour, tempMinute, tempCaffeineContent;

        int iDay = c.getColumnIndex(COLUMN_DAY);
        int iWeek = c.getColumnIndex(COLUMN_WEEK);
        int iDayofweek = c.getColumnIndex(COLUMN_DAYOFWEEK);
        int iHour = c.getColumnIndex(COLUMN_HOUR);
        int iMinute = c.getColumnIndex(COLUMN_MINUTE);
        int iCaffeinecontent = c.getColumnIndex(COLUMN_CAFFEINECONTENT);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            tempDay = c.getInt(iDay);
            tempWeek = c.getInt(iWeek);
            tempDayOfWeek = c.getInt(iDayofweek);
            tempHour = c.getInt(iHour);
            tempMinute = c.getInt(iMinute);
            tempCaffeineContent = c.getInt(iCaffeinecontent);


            CaffeineEntry caffeineEntryDay = new CaffeineEntry(tempDay, tempHour, tempMinute, tempCaffeineContent, no);

            days.get(tempDay).add(caffeineEntryDay);
        }

        for (int i = 0; i < days.size(); i++)
        {
            List<Entry> graphData = new ArrayList<>(24);
            boolean initGraph = true;

            if (days.get(i).size() <= 0)
            {
                // make a all 0 list

                for (int x0 = 0; x0 < 24; x0++)
                {
                    float y = 0;

                    Entry dataPoint = new Entry(x0, y);
                    graphData.add(dataPoint);
                }

                LineDataSet lineDataSet = new LineDataSet(graphData, "List");
                lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setFillColor(Color.rgb(176,64,64));
                lineDataSet.setColors(Color.rgb(57,23,9));
                lineDataSet.setLineWidth(3f);

                LineData lineData = new LineData(lineDataSet);

                resultLineDataList.add(lineData);

            }
            else
            {
                //make a list out of all of the entries
                for (int j = days.get(i).size() - 1; j >= 0; j--)
                {
                    if (initGraph)
                    {
                        for (int x0 = 0; x0 < 24; x0++)
                        {
                            float y = 0;

                            Entry dataPoint = new Entry(x0, y);
                            graphData.add(dataPoint);
                        }
                        initGraph = false;
                    }

                    int hour = days.get(i).get(j).getHour();
                    float min = days.get(i).get(j).getMinute();
                    int caffeine = days.get(i).get(j).getCaffContent();

                    min = map(min, 0, 59, 0, 99);
                    float minInHours = min/100;
                    float xConsumption = hour + minInHours;
                    double tempY;
                    float y = 0;
                    double fifthRoot = 1.0/5;
                    double halfTimeConstant = 0.5;
                    double timeSinceConsumption;

                    for(int x = 0; x < 24; x++)
                    {
                        if(x >= xConsumption)
                        {
                            timeSinceConsumption = x-xConsumption;

                            tempY = Math.pow(Math.pow(halfTimeConstant, timeSinceConsumption), fifthRoot);

                            y = (caffeine * (float)tempY) + graphData.get(x).getY();
                        }

                        if(y <= 0) y = 0;

                        graphData.get(x).setX(x);
                        graphData.get(x).setY(y);
                    }
                }

                LineDataSet lineDataSet = new LineDataSet(graphData, "List");
                lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setFillColor(Color.rgb(176,64,64));
                lineDataSet.setColors(Color.rgb(57,23,9));
                lineDataSet.setLineWidth(3f);

                LineData lineData = new LineData(lineDataSet);

                resultLineDataList.add(lineData);
            }
        }
        System.out.println(resultLineDataList.size());
        db.close();
        return resultLineDataList;
    }

    public ArrayList<LineData> loadDataBaseWeeks()
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ArrayList<CaffeineEntry>> weeks = new ArrayList<>(52);

        for (int i = 0; i < 52; i++)
        {
            ArrayList<CaffeineEntry> tempList = new ArrayList<>();
            weeks.add(i, tempList);
        }

        ArrayList<LineData> resultLineDataList = new ArrayList<>();

        String[] columns = new String[]{COLUMN_ID, COLUMN_DAY, COLUMN_WEEK, COLUMN_DAYOFWEEK, COLUMN_HOUR, COLUMN_MINUTE, COLUMN_CAFFEINECONTENT};
        Cursor c = db.query(TABLE_ENTRY, columns, null, null, null, null, null);

        int tempDay, tempWeek, tempDayOfWeek, tempHour, tempMinute, tempCaffeineContent;

        int iDay = c.getColumnIndex(COLUMN_DAY);
        int iWeek = c.getColumnIndex(COLUMN_WEEK);
        int iDayofweek = c.getColumnIndex(COLUMN_DAYOFWEEK);
        int iHour = c.getColumnIndex(COLUMN_HOUR);
        int iMinute = c.getColumnIndex(COLUMN_MINUTE);
        int iCaffeinecontent = c.getColumnIndex(COLUMN_CAFFEINECONTENT);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            tempDay = c.getInt(iDay);
            tempWeek = c.getInt(iWeek);
            tempDayOfWeek = c.getInt(iDayofweek);
            tempHour = c.getInt(iHour);
            tempMinute = c.getInt(iMinute);
            tempCaffeineContent = c.getInt(iCaffeinecontent);


            CaffeineEntry caffeineEntryWeek = new CaffeineEntry(tempWeek, tempDayOfWeek, tempHour, tempCaffeineContent);

            weeks.get(tempWeek).add(caffeineEntryWeek);
        }

        for (int i = 0; i < weeks.size(); i++)
        {
            List<Entry> graphData = new ArrayList<>();
            boolean initGraph = true;

            if (weeks.get(i).size() <= 0)
            {
                // make a all 0 list

                for (int x0 = 0; x0 < 168; x0++)
                {
                    float y = 0;

                    Entry dataPoint = new Entry(x0, y);
                    graphData.add(dataPoint);
                }

                LineDataSet lineDataSet = new LineDataSet(graphData, "List");
                lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setFillColor(Color.rgb(176,64,64));
                lineDataSet.setColors(Color.rgb(57,23,9));
                lineDataSet.setLineWidth(3f);

                LineData lineData = new LineData(lineDataSet);

                resultLineDataList.add(lineData);

            }
            else
            {
                //make a list out of all of the entries
                for (int j = 0; j < weeks.get(i).size(); j++)
                {
                    if (initGraph)
                    {
                        for (int x0 = 0; x0 < 168; x0++)
                        {
                            float y = 0;

                            Entry dataPoint = new Entry(x0, y);
                            graphData.add(dataPoint);
                        }
                        initGraph = false;
                    }

                    int dayOfWeek = (weeks.get(i).get(j).getDayOfWeek() - 1) * 24;
                    float hour = weeks.get(i).get(j).getHour();
                    int caffeine = weeks.get(i).get(j).getCaffContent();

                    float xConsumption = dayOfWeek + hour;
                    double tempY;
                    float y = 0;
                    double fifthRoot = 1.0/5;
                    double halfTimeConstant = 0.5;
                    double timeSinceConsumption;

                    for(int x = 0; x < 168; x++)
                    {
                        if(x >= xConsumption)
                        {
                            timeSinceConsumption = x-xConsumption;

                            tempY = Math.pow(Math.pow(halfTimeConstant, timeSinceConsumption), fifthRoot);

                            y = (caffeine * (float)tempY) + graphData.get(x).getY();
                        }

                        if(y <= 0) y = 0;

                        graphData.get(x).setX(x);
                        graphData.get(x).setY(y);
                    }
                }

                LineDataSet lineDataSet = new LineDataSet(graphData, "List");
                lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setFillColor(Color.rgb(176,64,64));
                lineDataSet.setColors(Color.rgb(57,23,9));
                lineDataSet.setLineWidth(3f);

                LineData lineData = new LineData(lineDataSet);

                resultLineDataList.add(lineData);
            }
        }
        System.out.println(resultLineDataList.size());
        db.close();
        return resultLineDataList;
    }

    public float map (float value, float start1, float stop1, float start2, float stop2)
    {
        float remap = start2 + (value - start1) * (start2 - stop2) / (start1 - stop1);
        return remap;
    }
}
