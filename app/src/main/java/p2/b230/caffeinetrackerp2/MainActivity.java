package p2.b230.caffeinetrackerp2;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String message = "thisMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void sendMessage(View view)
    {
        EditText editText =(EditText) findViewById(R.id.main_message);

        String message = editText.getText().toString();

        TextView textView = (TextView) findViewById(R.id.main_textview);

        textView.setTextSize(40);

        textView.setText(message);
    }


    public void changeActivity(View view) {
        Intent intent = new Intent(this, LoggingActivity.class);

        startActivity(intent);
        setContentView(R.layout.activity_logging);
    }
}
