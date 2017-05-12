package p2.b230.caffeinetrackerp2;

import java.util.ArrayList;

public class CaffeineEntry
{


    public static ArrayList<ArrayList<CaffeineEntry>> weeks = new ArrayList<ArrayList<CaffeineEntry>>(52);


    private int _id;
    private int day;
    private int week;
    private int dayOfWeek;
    private int hour;
    private int minute;
    private int caffContent;

    public CaffeineEntry()
    {

    }

    public CaffeineEntry(int day, int week, int dayOfWeek, int hour, int minute, int caffContent)
    {
        this.day = day;
        this.week = week;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
        this.caffContent = caffContent;
    }

    public CaffeineEntry(int week, int dayOfWeek, int hour, int caffContent)
    {
        this.week = week;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.caffContent = caffContent;
    }

    public CaffeineEntry(int day, int hour, int minute, int caffContent, boolean no)
    {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.caffContent = caffContent;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getCaffContent() {
        return caffContent;
    }

    public void setCaffContent(int caffContent) {
        this.caffContent = caffContent;
    }
}
