package com.example.deliciaapp;

import androidx.appcompat.app.AppCompatActivity;

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

import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText user_surnames;
    private EditText user_name;
    private EditText user_dni;
    private EditText user_email1;
    private EditText user_phone;
    private EditText user_pass1;
    private EditText user_pass2;
    private TextView tvStatus;
    private Button btn_back,registerUser;
    private String URL = " http://192.168.1.76/login_delicia/register.php";

    private String fullname, name, dni, email, phone, password1, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_surnames = findViewById(R.id.user_surnames);
        user_name = findViewById(R.id.user_name);
        user_dni = findViewById(R.id.user_dni);
        user_email1 = findViewById(R.id.user_email1);
        user_phone = findViewById(R.id.user_phone);
        user_pass1 = findViewById(R.id.user_pass1);
        user_pass2 = findViewById(R.id.user_pass2);
        btn_back = findViewById(R.id.btn_back);
        registerUser = findViewById(R.id.registerUser);
        tvStatus = findViewById(R.id.tvStatus);
        fullname = name = dni = email = phone = password1 = password2 = "";
    }

    public void save(View view) {
        fullname = user_surnames.getText().toString().trim();
        name = user_name.getText().toString().trim();
        dni = user_dni.getText().toString().trim();
        email = user_email1.getText().toString().trim();
        phone = user_phone.getText().toString().trim();
        password1 = user_pass1.getText().toString().trim();
        password2 = user_pass2.getText().toString().trim();
        if(!password1.equals(password2)){
            Toast.makeText(this, "Password correct", Toast.LENGTH_SHORT).show();

        }
        else if ( fullname.equals("") && !name.equals("") && !dni.equals("") && !email.equals("") && phone.equals("") &&!password1.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        tvStatus.setText("Usuario registrado correctamente");
                        registerUser.setClickable(false);

                    } else if (response.equals("failure")) {
                        tvStatus.setText("Algo salio mal");

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("fullname", fullname);
                    data.put("name", name);
                    data.put("dni", dni);
                    data.put("email", email);
                    data.put("phone", phone);
                    data.put("password", password1);
                    return data;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}