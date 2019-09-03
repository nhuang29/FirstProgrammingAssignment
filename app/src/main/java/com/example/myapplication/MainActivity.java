package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private int tracker = 0; // to keep track of users
    private String[] localTracker = new String[10];

    private FirebaseDatabase mref = FirebaseDatabase.getInstance("https://myapplication2-26c3e.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // All my own inputted code
        final TextView msgUser = findViewById(R.id.MessageToUser);
        final EditText names = findViewById(R.id.NameOfPerson);


        // Added In Comment to show that GitHub update works

        // This is to enter a person's name into the database
        final Button infoEnter = findViewById(R.id.SubmitButton);
        infoEnter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                String namer = names.getText().toString(); // name of user
                // piece of code to check if the users name is already registered
                for (int x = 0; x <= 9; x++) {
                    String str = localTracker[x];
                    if (!(str == null) && str.equals(namer)) {
                        // this is to stop everything.
                        msgUser.setText("Thank you, but this user is already registered.");
                        return;
                    }
                }
                msgUser.setText("Thank you for submitting your name!");
                localTracker[tracker] = namer;
                DatabaseReference myself = mref.getReference("Name" + tracker);
                myself.setValue(namer); // set to name value
                tracker++;
                if (tracker > 9) {
                    tracker = 0;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
