package com.example.deliciaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText email_txt;
    private EditText pass_txt;
    private String email, password;
    private String URL = " http://192.168.1.76/login_delicia/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email ="";
        password = "";
        email_txt = findViewById(R.id.email_txt);
        pass_txt = findViewById(R.id.pass_txt);

    }

    public void login(View view) {
        email = email_txt.getText().toString().trim();
        password = pass_txt.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(LoginActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){  
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    return data;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(this, "Error opss", Toast.LENGTH_SHORT).show();

        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}