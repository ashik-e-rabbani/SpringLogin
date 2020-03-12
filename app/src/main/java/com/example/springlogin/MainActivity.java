package com.example.springlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Email,Password;
    String inpuutedEmail,inputtedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = (EditText) findViewById(R.id.emailTextView);
        Password = (EditText) findViewById(R.id.passwordTextView);


    }

    public void login(View view) {

        // taking all input values
        inpuutedEmail = Email.getText().toString().trim();
        inputtedPassword = Password.getText().toString().trim();

        //checking if fields are not filled properly then give toast
        if (inpuutedEmail.isEmpty() || inputtedPassword.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Fill All fields",Toast.LENGTH_SHORT).show();
        }else{
            // login data will be sent to the API then access to Dashboard
            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
        }


    }

    public void register(View view) {

        Intent goToRegister = new Intent(this,register.class);
        startActivity(goToRegister);
    }
}
