package com.example.readnwrite;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText = null;
    TextView textView = null;
    ScrollView scrollView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button writeButton = (Button)findViewById(R.id.writebtn);
        editText =(EditText)findViewById(R.id.editText);
        Button readButton = (Button)findViewById(R.id.readbtn);
        textView =(TextView)findViewById(R.id.notes_tv);
        scrollView = (ScrollView)findViewById(R.id.scrollView1);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(v);
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

    }

    public void writeFile (View view){
        try{
            FileOutputStream fileOutputStream = openFileOutput("test", Context.MODE_APPEND);
            BufferedWriter bufferedWriter = new BufferedWriter(new
                    OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(editText.getText().toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileOutputStream.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void readFile(){
        String notes="";
        try{
            FileInputStream fileInputStream= openFileInput("test");
            if (fileInputStream!=null){
                BufferedReader bufferedReader= new BufferedReader(new
                        InputStreamReader(fileInputStream));
                String note=null;
                while ((note =bufferedReader.readLine()) != null){
                    notes+=note + "\n";
                }
                fileInputStream.close();
            }
        }catch (IOException io){
            io.printStackTrace();
        }
        textView.setText(notes);
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
