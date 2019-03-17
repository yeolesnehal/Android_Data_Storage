package edu.sjsu.android.androiddatastorage;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            InputStream in=openFileInput(SetPreferencesActivity.STORE_PREFERENCES);
            if(in!=null)
            {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while((str=reader.readLine())!=null)
                {
                    buf.append(str +"\n");
                }
                in.close();
                TextView savedPref=(TextView)findViewById(R.id.output);
                savedPref.setText(buf.toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openPreference(View view){
        Intent intent = new Intent(this, SetPreferencesActivity.class);
        startActivity(intent);
    }

    public void saveInDatabase(View view){
        Intent intent = new Intent(this, SQLiteActivity.class);
        startActivity(intent);
    }

    public void exitApp(View view){
        finish();
        System.exit(0);
    }

}
