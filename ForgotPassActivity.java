package e.user.database2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassActivity extends AppCompatActivity {

    DataBase db;
    Cursor c;
    EditText edEmail,e2;
    LinearLayout bannerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        edEmail =(EditText) findViewById(R.id.edEmail);

    }//onCreate


    public void verify(View v)
    {
        try
        {
            final String email = edEmail.getText().toString();
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassActivity.this, "Reset Password link Sent ...Please Check your email", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String error = task.getException().getMessage();
                                Toast.makeText(ForgotPassActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}
