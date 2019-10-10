package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;


public class Main22Activity extends AppCompatActivity {
   public Intent i;
   public Button b1;
   DataBase db;
   String password="";
   Cursor c;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
//        //lead bolt ads
//        if(savedInstanceState == null) {
//            // Initialize Leadbolt SDK with your api key
//            AppTracker.startSession(getApplicationContext(), "B7lyTxyrboK2YQJ7czxQb8pbaKhNQhPd");
//        }
//        AppTracker.loadNativeAdsWithCaching();
//        wv=(findViewById(R.id.webView));
//        wv.getSettings().setJavaScriptEnabled(true);
//        wv.setBackgroundColor(Color.TRANSPARENT);
//        String html="<html><head><script type=\"text/javascript\" src=\"http://ad.leadbolt.net/show_app_ad.js?section_id=153246702\"></script></head></html>";
//        wv.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        db = (DataBase) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {

            case R.id.a_set:
                      i=new Intent(this,Settings.class);
                       startActivity(i);
                        break;
            case R.id.share:
                      share();
                     break;
        }
        return super.onOptionsItemSelected(item);
    }


public void add(View v)
{
    i=new Intent(this,MainActivity.class);
    startActivity(i);
}

    public void edit(View v)
    {
        i=new Intent(this,DisplayRecords.class);
        i.putExtra("key","Save");
        startActivity(i);
    }

    public void delete(View v)
    {
        i=new Intent(this,DisplayRecords.class);
        i.putExtra("key","Delete");
        startActivity(i);
    }
    public void show(View v)
    {
        i=new Intent(this,DisplayRecords.class);
        i.putExtra("key","Show");
        startActivity(i);
    }



    @Override
    public void onBackPressed()
    {
       super.onBackPressed();
        i=new Intent(this,LogInActivity.class);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    public void share()
    {
        try {
              db.create();
            c = db.display2();
            if(c.moveToFirst()!=false)
            {
                int j = 1;
                for (int i = 0; c.moveToPosition(i); i++) {
                    password += j + ")" + c.getString(0) + "\n";
                    password += c.getString(1) + "\n" + c.getString(2) + "\n";
                    j++;
                }
                i = ShareCompat.IntentBuilder.from(this)
                        .setText(password)
                        .setType("text/file")
                        .getIntent()
                        .setPackage("com.google.android.apps.docs");

                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "Please Add some Records", Toast.LENGTH_SHORT).show();
            }


        }
        catch(Exception e)
        {
            Toast.makeText(this, "Google Drive App Not found", Toast.LENGTH_SHORT).show();
        }
    }

}//end of class
