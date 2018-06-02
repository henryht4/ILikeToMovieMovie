package com.cs122b.iliketomoviemoviemobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view){
        String user = ((EditText) findViewById(R.id.username)).getText().toString();
        String pass = ((EditText) findViewById(R.id.password)).getText().toString();

        if(user.equals("anteater") && pass.equals("123456")) {
            Intent goToIntent = new Intent(this, HomeActivity.class);
            goToIntent.putExtra("username", user);
            goToIntent.putExtra("password", pass);
            startActivity(goToIntent);
        }
        else{
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setTitle("Login Error");
            dlgAlert.setMessage("Wrong Username or Password Entered");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

        }

    }


    public void login(View view){
// Post request form data
        final Map<String, String> params = new HashMap<String, String>();
        params.put("username", "anteater");
        params.put("password", "123456");

        // no user is logged in, so we must connect to the server

        // Use the same network queue across our application
        final RequestQueue queue = NetworkManager.sharedManager(this).queue;

        // 10.0.2.2 is the host machine when running the android emulator
        final StringRequest afterLoginRequest = new StringRequest(Request.Method.GET, "https://192.168.0.9:8443/project4-login-example/api/username",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("response2", response);
                        ((TextView) findViewById(R.id.http_response)).setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("security.error", error.toString());
                    }
                }
        );


        final StringRequest loginRequest = new StringRequest(Request.Method.POST, "https://192.168.0.9:8443/project4-login-example/api/android-login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("response", response);
                        ((TextView) findViewById(R.id.http_response)).setText(response);
                        // Add the request to the RequestQueue.
                        queue.add(afterLoginRequest);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("security.error", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }  // HTTP POST Form Data
        };

        SafetyNet.getClient(this).verifyWithRecaptcha("6Ldtn1wUAAAAABF6JJGJh8D9scTg-Fb7U5RqCNGZ")
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        if (!response.getTokenResult().isEmpty()) {
                            // Add the request to the RequestQueue.
                            params.put("g-recaptcha-response", response.getTokenResult());
                            queue.add(loginRequest);
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d("Login", "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.d("Login", "Unknown type of error: " + e.getMessage());
                        }
                    }
                });


    }
}
