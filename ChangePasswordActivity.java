package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.apptracker.android.nativead.template.ATNativeAdView;
import com.apptracker.android.track.AppTracker;
import com.apptracker.android.nativead.ATNativeAd;
import com.apptracker.android.nativead.ATNativeListener;
import com.apptracker.android.nativead.ATNativeAdCollection;

import java.util.Set;

public class ChangePasswordActivity extends AppCompatActivity
{
    public TextView t1,t2,t3;
   DataBase db;
   Cursor c;
   WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        //lead bolt ads
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
        t1=(TextView)findViewById(R.id.eOld);
        t1.requestFocus();
    }



    public void changePassword2(View v)
    {
        db=(DataBase)getApplication();
        t1=(TextView)findViewById(R.id.eOld);
        t2=(TextView)findViewById(R.id.eNew);
        t3=(TextView)findViewById(R.id.eNew1);
        String old=t1.getText().toString();
        String new1=t2.getText().toString();
        String new2=t3.getText().toString();
        c=db.display3();
        c.moveToFirst();
        c.moveToLast();
        String pass=c.getString(1).toString();

        if(old.length()==0)
        {
            Toast.makeText(this, "Please Enter Old Password", Toast.LENGTH_SHORT).show();
        }
        else if(new1.length()==0)
        {
            Toast.makeText(this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else if(new2.length()==0)
        {
            Toast.makeText(this, "Please Re Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(old.equals(pass))
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
            else
                Toast.makeText(this, "Old password is not matching", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

}//end of class
