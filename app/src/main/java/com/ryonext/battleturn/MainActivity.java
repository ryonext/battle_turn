package com.ryonext.battleturn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    int turn = 1;
    int warn_per = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn++;
                TextView text = (TextView)findViewById(R.id.label);
                text.setText(String.valueOf(turn));

                if(warn_per > 0 && turn % warn_per == 0) {
                    text.setTextColor(Color.RED);
                } else {
                    text.setTextColor(Color.BLACK);
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
        switch (id) {
            case R.id.action_settings:
                createInputDialog();
                return true;
            case R.id.reset:
                turn = 1;
                TextView text = (TextView)findViewById(R.id.label);
                text.setText(String.valueOf(turn));

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createInputDialog(){
        final EditText editView = new EditText(MainActivity.this);
        editView.setText(String.valueOf(warn_per));
        
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("危険ターン")
                .setView(editView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                          String val = editView.getText().toString();
                          warn_per = Integer.parseInt(val);
                        } catch (Exception e){

                        }
                    }
                }).show();
    }
}