package com.example.springlogin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText Name,Email,Phone,Password;
    String inputtedName,inpuutedEmail,inputtedPhone,inputtedPassword;
    RequestQueue mQueue;
    String receivedIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");

        Name = (EditText) findViewById(R.id.usernameTextView);
        Email = (EditText) findViewById(R.id.emailTextView);
        Phone = (EditText) findViewById(R.id.phoneTextView);
        Password = (EditText) findViewById(R.id.passwordTextView);
        mQueue = Volley.newRequestQueue(this);

        Intent intent=getIntent();
        receivedIp = intent.getStringExtra("serverIp");



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
           // checkResigteredOrNot();

            postData();

        }


    }



    public void postData(){

        String requestUrl = "http://"+receivedIp+"/users/add";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // now based on the response switching the activity

                if (response.contains("UserAlreadyExist"))
                {
                    Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
                }else if (response.contains("Saved"))
                {
                    Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                    Intent goToLogin = new Intent(register.this,MainActivity.class);
                    startActivity(goToLogin);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server Problem "+error,Toast.LENGTH_SHORT).show();
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("name",inputtedName);
                postMap.put("email",inpuutedEmail);
                postMap.put("phone",inputtedPhone);
                postMap.put("password",inputtedPassword);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
//make the request to your server as indicated in your request url
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    public void login(View view) {

        Intent goToLogin = new Intent(this,MainActivity.class);
        startActivity(goToLogin);
    }
}
