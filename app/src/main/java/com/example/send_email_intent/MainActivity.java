package com.example.send_email_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEtEmail;
    private EditText mEtCC;
    private EditText mEtDATA;
    private Button mBtnSend;
    private String validMail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtEmail=findViewById(R.id.etEmail);
        mEtCC=findViewById(R.id.etCC);
        mEtDATA=findViewById(R.id.etMessage);
        mBtnSend = findViewById(R.id.btnSend);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateEmail()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEtEmail.getText().toString()});
                    emailIntent.putExtra(Intent.EXTRA_CC, new String[]{mEtCC.getText().toString()});
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "" + mEtDATA.getText().toString());
                    // startActivity(emailIntent);

                    if(emailIntent.resolveActivity(getPackageManager())!=null){
                        startActivity(emailIntent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"No Email App found",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
    protected boolean ValidateEmail(){
        if(mEtEmail.getText().toString().trim().length()>=1 && mEtEmail.getText().toString().matches(validMail)){
            return true;
        }
        else{
            mEtEmail.setError("Invalid Email");
            return false;
        }
    }
}