package e.user.database2;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;

import java.util.ArrayList;

public class DisplayRecords extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView lv;
    DataBase db;
    Cursor c;
    ArrayList<String> service;
    String key;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);
        try {
            Bundle b = new Bundle();
            b = getIntent().getExtras();
            key = b.getString("key");

            service = new ArrayList<>();
            db = (DataBase) getApplication();
            db.create();
            c = db.display2();
            c.moveToFirst();
            for (int i = 0; c.moveToPosition(i); i++) {
                service.add(c.getString(0));
            }
            if (service.size() > 0)
            {
                lv = (ListView) findViewById(R.id.lv);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.text_center, R.id.text, service);
            lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(this);
            }
            else
            {
                TextView tv=(TextView)findViewById(R.id.tvShow);
                tv.setVisibility(View.VISIBLE);
            }


        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        try {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            MenuItem searchItem = menu.findItem(R.id.search);
            SearchView searchView =(SearchView) MenuItemCompat.getActionView(searchItem);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText)
                {
                    ArrayList<String> tempList = new ArrayList<>();
                    for (String temp : service) {
                        if (temp.toLowerCase().contains(newText.toLowerCase())) {
                            tempList.add(temp);
                        }
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DisplayRecords.this, R.layout.text_center,R.id.text,tempList);
                    lv = (ListView) findViewById(R.id.lv);
                    lv.setAdapter(arrayAdapter);
                    lv.setOnItemClickListener(DisplayRecords.this);
                    return true;
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return super.onCreateOptionsMenu(menu);


    }//end of oncreateOptionMenu*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        try {
            Intent i = new Intent(DisplayRecords.this, EditActivity.class);
            i.putExtra("position", position);
            i.putExtra("key", key);
            startActivity(i);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i = new Intent(this, Main22Activity.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}//end of class