package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



public class EditActivity extends AppCompatActivity {
  Button b1,b2;

   public String key;
   public int position;
    DataBase db;
    Cursor c;
    LinearLayout bannerContainer;

    public EditText eser,euser,epass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        try {
          Bundle b = new Bundle();
          b = getIntent().getExtras();
          key = b.getString("key");
          position = b.getInt("position");
          b1 = (Button) findViewById(R.id.savebtn1);
          b2 = (Button) findViewById(R.id.cancelbtn1);
          eser = (EditText) findViewById(R.id.esName);
          euser = (EditText) findViewById(R.id.euName);
          epass = (EditText) findViewById(R.id.epWord);
          b1.setText(key);
           if(key.equals("Show"))
           {
             b1.setVisibility(View.INVISIBLE);
               epass.setFocusableInTouchMode(true);
               eser.setFocusableInTouchMode(false);
               euser.setFocusableInTouchMode(false);
           }
        else if (key.equals("Save"))
          {
              euser.requestFocus();
          }
          else
              {
                  epass.setFocusableInTouchMode(false);
                  eser.setFocusableInTouchMode(false);
                  euser.setFocusableInTouchMode(false);
                  epass.setFocusable(false);
                  eser.setFocusable(false);
                  euser.setFocusable(false);

          }
          db = (DataBase) getApplication();
          c = db.display2();
          c.moveToPosition(position);


          eser.setText(c.getString(0).toString());
          euser.setText(c.getString(1).toString());
          epass.setText(c.getString(2).toString());

      }
      catch(Exception e)
      {
          Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
      }
    }

    public void save2(View v)
    {
        try {
            db = (DataBase) getApplication();
            String service, user, pass;
            eser = (EditText) findViewById(R.id.esName);
            euser = (EditText) findViewById(R.id.euName);
            epass = (EditText) findViewById(R.id.epWord);
            service = eser.getText().toString();
            user = euser.getText().toString();
            pass = epass.getText().toString();
            if (key.equals("Save")) {
                db.edit(service, user, pass);
                euser.requestFocus();
            }
            else {
                db.delete(service);

            }
            Intent i = new Intent(this, Main22Activity.class);
            startActivity(i);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void back2(View v)
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
}
