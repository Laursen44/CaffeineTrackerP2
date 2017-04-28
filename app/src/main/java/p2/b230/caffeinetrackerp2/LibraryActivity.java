package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    public void makeCostum(View view)
    {

    }

    public void backToLogging(View view)
    {
        Intent intent = new Intent(LibraryActivity.this, MainLoggingActivity.class);
        startActivity(intent);
    }
}
