package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

   DataBase db;
     public EditText eser,euser,epass;
    LinearLayout bannerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            db=(DataBase)getApplication();
            db.create();
            eser=(EditText)findViewById(R.id.sName);
            eser.requestFocus();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

   public void save(View v)
   {
       String service,user,pass;
       eser=(EditText)findViewById(R.id.sName);
       euser=(EditText)findViewById(R.id.uName);
       epass=(EditText)findViewById(R.id.pWord);
       service=eser.getText().toString();
       user=euser.getText().toString();
       pass=epass.getText().toString();
       if(eser.length()==0)
           Toast.makeText(this, "Enter Service Name", Toast.LENGTH_SHORT).show();
       else if(euser.length()==0)
           Toast.makeText(this, "Enter User Name", Toast.LENGTH_SHORT).show();
       else if(epass.length()==0)
           Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
       else {
           try {
               db.insert(service, user, pass);

           } catch (Exception e) {
               Toast.makeText(this, "Service Name Already Exists2", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(this, MainActivity.class);
               startActivity(i);
           }
       }

   }


    public void back(View v)
    {
        Intent i=new Intent(this,Main22Activity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        Intent i=new Intent(this,Main22Activity.class);
        startActivity(i);
    }
}//end of class
