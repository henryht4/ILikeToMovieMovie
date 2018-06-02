package com.cs122b.iliketomoviemoviemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        //Toast.makeText(this,"Username: " + bundle.get("username"), Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"Password: " + bundle.get("password"), Toast.LENGTH_LONG).show();


        String user = bundle.getString("username");
        String pass = bundle.getString("password");
        if (user != null && !"".equals(user)) {
            ((TextView) findViewById(R.id.userdisplay)).setText(user);
        }

        if (pass != null && !"".equals(pass)) {
            ((TextView) findViewById(R.id.userpass)).setText(pass);
        }
    }
}
