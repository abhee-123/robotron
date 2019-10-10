package e.user.database2;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.security.auth.login.LoginException;


public class LogInActivity extends AppCompatActivity {
       DataBase db;
       Cursor c;
       EditText edEmailLogin,edPasswordLogin;
       ProgressBar progressBarLogin;
       FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        firebaseAuth = FirebaseAuth.getInstance();
    }//en dof onCreate


    public void verifyLogin(View v)
    {

        try {
//            db = (DataBase) getApplication();
//            db.create2();
//            c = db.display3();
//            c.moveToFirst();
//            c.moveToLast();
//            String pass=c.getString(1).toString();
//
            edEmailLogin = (EditText) findViewById(R.id.edEmailLogin);
            edPasswordLogin = (EditText) findViewById(R.id.edPasswordLogin);
            progressBarLogin = (ProgressBar) findViewById(R.id.progressBarLogin);
            String email = edEmailLogin.getText().toString();
            String password=edPasswordLogin.getText().toString();
//
//                if (pass2.equals(pass))
//                {
//                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(this, Main22Activity.class);
//                    startActivity(i);
//
//                } else
//                    {
//                    Toast.makeText(this, "Password does not matching", Toast.LENGTH_SHORT).show();
//                }
            //firbease login
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            progressBarLogin.setVisibility(View.VISIBLE);
                            if (!task.isSuccessful())
                            {
                                String error = task.getException().getMessage();
                                Toast.makeText(LogInActivity.this, "error occured"+error, Toast.LENGTH_SHORT).show();
                                progressBarLogin.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                Toast.makeText(LogInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                progressBarLogin.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(LogInActivity.this, Main22Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });


        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }//end of verifyLogin


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
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }


}
