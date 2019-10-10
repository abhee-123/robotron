package e.user.database2;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class DataBase extends Application
{
    SQLiteDatabase db;
    public Cursor c;
    public void create()
    {
        db = openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
        db.execSQL("create table if not exists SafeIt(service text primary key,user text not null,password text not null);");
    }// end of create

    public void insert(String serveice,String user,String pass)
    {
        try {
           db.execSQL("insert into SafeIt values('" + serveice + "','" + user + "','" + pass + "')");
            Toast.makeText(this," Record Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Main22Activity.class);
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Service Name Already Exists1", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }//end of insert

    public void display(String service,String user,String pass)
    {
        try
        {
            c=db.rawQuery("select * from SafeIt where service='"+service+"';",null);
            c.moveToFirst();
            service=c.getString(0);
            user=c.getString(1);
            pass=c.getString(2);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }//end of display

    public void delete(String service)
    {

       try
       {
           String query="delete from SafeIt where service='"+service+"';";
           SQLiteStatement stmt=db.compileStatement(query);
           int count=stmt.executeUpdateDelete();
           if(count>0)
           Toast.makeText(this,"Record Deleted", Toast.LENGTH_SHORT).show();

       }
       catch (Exception e)
       {
           Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
       }
    }//end of delete

    public void edit(String service,String user,String pass)
    {

        try
        {
         String query="update SafeIt set user='"+user+"',password='"+pass+"' where service='"+service+"';";

            SQLiteStatement stmt=db.compileStatement(query);
            int count=stmt.executeUpdateDelete();
            if(count>0)
                Toast.makeText(this,"Record Updated", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(this,"Cannot Alter Service Name", Toast.LENGTH_SHORT).show();
        }
    }//end of delete

    public Cursor display2()
    {
        try
        {
            c=db.rawQuery("select * from SafeIt ;",null);

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return c;
    }//end of display

    public void create2()
    {
        db = openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
        db.execSQL("create table if not exists Password(u_name text not null,pass text not null,re_pass text not null,status text not null);");
    }// end of create2

    public void insert2(String user,String pass1,String pass2)
    {
        try {
            db.execSQL("insert into Password values('" + user + "','" + pass1 + "','" + pass2 + "','In')");

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }//end of insert2

    public Cursor display3()
    {
        try
        {
            c=db.rawQuery("select * from password ;",null);

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return c;
    }//end of display3

     public void deleteAll()
     {

         try {
             db.execSQL("delete  from SafeIt");
             Toast.makeText(this, "All Records Deleted", Toast.LENGTH_SHORT).show();
         }
         catch(Exception e)
         {
             Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
         }
     }

     public void change(String new1)
     {
         try {
             c = display3();
             c.moveToFirst();
             c.moveToLast();
             String user = c.getString(0).toString();
             String query = "update Password set pass='" + new1 + "',re_pass='" + new1 + "' where u_name='" + user + "';";
             db.execSQL(query);
             Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show();
         }
         catch(Exception e)
         {
             Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
         }
     }//end of change

}//end of class
