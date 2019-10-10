package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apptracker.android.track.AppTracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Main2Activity extends AppCompatActivity {
    EditText euser,epass,epass2,eemail,edEmailLogin,edPasswordLogin;
    ProgressBar progressBar;
    DataBase db;
     Cursor c;
     public Button t1;
     public TextView t2;
    WebView wv;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        t1=(Button)findViewById(R.id.btnlogin1);
        try
        {
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if(firebaseUser!=null)
                    {
                       startActivity(new Intent(this,LogInActivity.class));
                       finish();
                    }

//                    db = (DataBase) getApplication();
//                    db.create2();
//                    c = db.display3();
//                    c.moveToFirst();
//                    c.moveToLast();
//                    if(c.getCount()==0)
//                    {
//                            setContentView(R.layout.sign_in);
//                    }
//                   else if (c.getString(3).equals("In"))
//                   {
//                            setContentView(R.layout.activity_log_in);
//                            edPasswordLogin = (EditText) findViewById(R.id.edPasswordLogin);
//                            edPasswordLogin.setText("");
//                            edEmailLogin = (EditText) findViewById(R.id.edEmailLogin);
//                            edEmailLogin.setText("");
//                   }
//                    else
//                            setContentView(R.layout.sign_in);
        }
        catch (Exception e)
        {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }//end of onCreate

   public void verifySignIn(View v)
   {
       epass = (EditText) findViewById(R.id.ePass);
       epass2 = (EditText) findViewById(R.id.ePass2);
       eemail = (EditText) findViewById(R.id.eEmail);
       String email = eemail.getText().toString();
       //String user = euser.getText().toString();
       final String pass1 = epass.getText().toString();
       final String pass2 = epass2.getText().toString();
       progressBar =(ProgressBar) findViewById(R.id.progressBar);

//        if (email.length() == 0)
//       {
//           Toast.makeText(this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
//       }
//       else if (pass1.length() == 0)
//       {
//           Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
//       }
//       else if (user.length() < 5)
//       {
//           Toast.makeText(this, "Username Should Contain Minimum 5 Characters", Toast.LENGTH_SHORT).show();
//       }
//       else if (pass1.length() < 5)
//       {
//           Toast.makeText(this, "Password Should Contain Minimum 5 Characters", Toast.LENGTH_SHORT).show();
//       }
//       else if(pass1.equals(pass2))
//       {
//
//               if((email.contains("@gmail.com")==false || email.contains("@yahoo.com")==false) && email.length()<8)
//               {
//                   Toast.makeText(this, "Please Enter a valid Email Id", Toast.LENGTH_SHORT).show();
//               }
//           else {
//                   db=(DataBase)getApplication();
//                   db.insert2(user,pass1,pass2);
//               Toast.makeText(this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
//               Intent i = new Intent(this, LogInActivity.class);
//               startActivity(i);
//
//           }
//       }
//       else
//           Toast.makeText(this, "Entered passwords are not matching", Toast.LENGTH_SHORT).show();

        //firebaseVerification
       try
       {
           if (email.length() == 0)
             {
                Toast.makeText(this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
            }
            else if (pass1.length() == 0)
            {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }
            else if(pass1.equals(pass2))
           {

               progressBar.setVisibility(View.VISIBLE);
               //create user
               firebaseAuth.createUserWithEmailAndPassword(email, pass1)
                       .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task)
                           {
                               progressBar.setVisibility(View.GONE);
                               if (!task.isSuccessful())
                               {
                                   Toast.makeText(Main2Activity.this, "Authentication failed." + task.getException(),
                                           Toast.LENGTH_SHORT).show();
                                   progressBar.setVisibility(View.INVISIBLE);
                               }
                               else
                               {
                                   Toast.makeText(Main2Activity.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                                   progressBar.setVisibility(View.INVISIBLE);
                                   db=(DataBase)getApplication();
                                   db.insert2("user",pass1,pass2);
                                   firebaseAuth = FirebaseAuth.getInstance();
                                   Intent i = new Intent(Main2Activity.this, LogInActivity.class);
                                   startActivity(i);
                               }
                           }
                       });

           }
           else
           {
               Toast.makeText(Main2Activity.this, "Entered Passwords are not matching", Toast.LENGTH_SHORT).show();
               progressBar.setVisibility(View.INVISIBLE);
           }
       }
       catch (Exception e)
       {
           Toast.makeText(Main2Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
           progressBar.setVisibility(View.INVISIBLE);
       }//end of try catch








   }//end of verifySignIn


//    public void verifyLogin(View v)
//    {
//
//        try {
////            db = (DataBase) getApplication();
////            db.create2();
////            c = db.display3();
////            c.moveToFirst();
////            c.moveToLast();
////            String pass=c.getString(1).toString();
////
//            edEmailLogin = (EditText) findViewById(R.id.edEmailLogin);
//            edPasswordLogin = (EditText) findViewById(R.id.edPasswordLogin);
//            String email = edEmailLogin.getText().toString();
//            String password=edPasswordLogin.getText().toString();
////
////                if (pass2.equals(pass))
////                {
////                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
////                    Intent i = new Intent(this, Main22Activity.class);
////                    startActivity(i);
////
////                } else
////                    {
////                    Toast.makeText(this, "Password does not matching", Toast.LENGTH_SHORT).show();
////                }
//            //firbease login
//            firebaseAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>()
//                    {
//                        try
//                        {
//
//                        }
//                        catch(Exception e)
//                        {
//                            Toast.makeText(g, e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            // If sign in fails, display a message to the user. If sign in succeeds
//                            // the auth state listener will be notified and logic to handle the
//                            // signed in user can be handled in the listener.
//                            progressBar.setVisibility(View.GONE);
//                            if (!task.isSuccessful())
//                            {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(Main2Activity.this, "error occured"+error, Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            {
//                                Intent intent = new Intent(Main2Activity.this, Main22Activity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }
//                    });
//
//
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }//end of verifyLogin

    public void moveToForgetPasswordActivity(View v)
    {
        startActivity(new Intent(this,ForgotPassActivity.class));
    }

    public void forgot(View v)
    {
       try {
            Intent i = new Intent(this,ForgotPassActivity.class);
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
        finishAffinity();
        System.exit(0);
    }




    @Override
    protected void onRestart()
    {
        super.onRestart();

    }



}//end of class