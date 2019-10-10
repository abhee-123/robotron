package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;


public class DeleteActivity extends AppCompatActivity {
      DataBase db;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

//        //leadbolt ads
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
        db.create();
    }

    public void deleteAll(View v)
    {
        try {
            db = (DataBase) getApplication();
            db.deleteAll();
            Intent i = new Intent(this, Main22Activity.class);
            startActivity(i);
           }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View v)
    {
        try {
            Intent i = new Intent(this, Main22Activity.class);
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}
