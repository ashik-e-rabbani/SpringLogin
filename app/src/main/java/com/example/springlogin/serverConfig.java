package com.example.springlogin;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class serverConfig extends AppCompatActivity {

    EditText serverIp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ip Configuration");
        setContentView(R.layout.activity_server_config);
        serverIp = (EditText) findViewById(R.id.serverIp);



    }


    public void setServerIp(View view)
    {
        String getIp = serverIp.getText().toString();
        Intent MainPage = new Intent(this,MainActivity.class);
        MainPage.putExtra("serverIp", getIp);
        startActivity(MainPage);
    }
}
