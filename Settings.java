package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;

public class Settings extends AppCompatActivity {
    LinearLayout bannerContainer;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }//end of onCreate

    public void changePassword(View v)
    {
       try
       {
           Intent i=new Intent(this,ChangePasswordActivity.class);
           startActivity(i);
       }
       catch(Exception e)
       {
           Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
       }

    }

    public  void deleteData(View v)
    {
        try {
            Intent i=new Intent(this,DeleteActivity.class);
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
        Intent i=new Intent(this,Main22Activity.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}//end of class
