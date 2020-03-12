package com.example.springlogin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText Name,Email,Phone,Password;
    String inputtedName,inpuutedEmail,inputtedPhone,inputtedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        Name = (EditText) findViewById(R.id.usernameTextView);
        Email = (EditText) findViewById(R.id.emailTextView);
        Phone = (EditText) findViewById(R.id.phoneTextView);
        Password = (EditText) findViewById(R.id.passwordTextView);



    }

    public void register(View view) {

        // taking all input values
        inputtedName = Name.getText().toString().trim();
        inpuutedEmail = Email.getText().toString().trim();
        inputtedPhone = Phone.getText().toString().trim();
        inputtedPassword = Password.getText().toString().trim();

        if (inpuutedEmail.isEmpty() || inputtedPassword.isEmpty()||
                inputtedName.isEmpty() || inputtedPhone.isEmpty()
        )
        {
            Toast.makeText(getApplicationContext(),"Fill All fields",Toast.LENGTH_SHORT).show();
        }else{
            // login data will be sent to the API then access to Dashboard
            Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
        }


    }

    public void login(View view) {

        Intent goToLogin = new Intent(this,MainActivity.class);
        startActivity(goToLogin);
    }
}
