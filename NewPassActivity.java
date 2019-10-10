package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;

public class NewPassActivity extends AppCompatActivity {
  DataBase db;
  Cursor c;
  EditText t1,t2;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        //leadbolt ads
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
        db=(DataBase)getApplication();
        t1=(EditText) findViewById(R.id.eNew);
        t1.requestFocus();
    }

    public void newPassword(View v)
    {
        db=(DataBase)getApplication();
        t1=(EditText) findViewById(R.id.eNew);
        t2=(EditText) findViewById(R.id.eNew1);

        String new1=t1.getText().toString();
        String new2=t2.getText().toString();


        if(new1.length()==0)
        {
            Toast.makeText(this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else if(new2.length()==0)
        {
            Toast.makeText(this, "Please Re-Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else
        {

                if(new1.equals(new2))
                {
                    db.change(new1);
                    Intent i=new Intent(this,Main22Activity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "New Passwords are not matching", Toast.LENGTH_SHORT).show();
                }

        }

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}//end of class
