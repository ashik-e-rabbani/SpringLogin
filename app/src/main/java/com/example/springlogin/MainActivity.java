package com.example.springlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText Email,Password;
    String inpuutedEmail,inputtedPassword;
    RequestQueue mQueue;
    TextView errorText;
    String receivedIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        Email = (EditText) findViewById(R.id.emailTextView);
        Password = (EditText) findViewById(R.id.passwordTextView);
        mQueue = Volley.newRequestQueue(this);
        errorText = (TextView) findViewById(R.id.errorAlert);
        errorText.setVisibility(View.GONE);
        Intent intent=getIntent();
        receivedIp = intent.getStringExtra("serverIp");
    }

    public void login(View view) {

        // taking all input values
        inpuutedEmail = Email.getText().toString().trim();
        inputtedPassword = Password.getText().toString();

        //checking if fields are not filled properly then give toast
        if (inpuutedEmail.isEmpty() || inputtedPassword.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Fill All fields",Toast.LENGTH_SHORT).show();
        }else{
            // login data will be sent to the API then access to Dashboard
            jsonParseArray();
        }


    }


    private void jsonParseArray() {


        final String url = "http://"+receivedIp+"/users/all";



        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject users = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String name = users.getString("name");
                                String email = users.getString("email").trim();
                                String phone = users.getString("phone");
                                String password = users.getString("password");

                                if (email.contains(inpuutedEmail) && password.contains(inputtedPassword)) {

                                    Intent gotoDash = new Intent(MainActivity.this,dashboard.class);
                                    gotoDash.putExtra("LoggedUser",name);
                                    startActivity(gotoDash);
                                    errorText.setVisibility(View.GONE);
                                }
                                else
                                {

                                }


                            }
                            //Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        mQueue.add(jsonArrayRequest);
        errorText.setVisibility(View.VISIBLE);
    }

    private void showErrorAlert() {

    }

    public void register(View view) {

        Intent goToRegister = new Intent(this,register.class);
        goToRegister.putExtra("serverIp",receivedIp);
        startActivity(goToRegister);
    }
}
