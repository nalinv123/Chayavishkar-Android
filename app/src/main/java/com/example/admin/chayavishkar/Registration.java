package com.example.admin.chayavishkar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity{

    EditText edname,edusername,edpassword,eddate,edemail,edphnno;
    Button submit;
    TextView alreadyregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edname=(EditText)findViewById(R.id.edname);
        edusername=(EditText)findViewById(R.id.edusername);
        edpassword=(EditText)findViewById(R.id.edpassword);
        eddate=(EditText)findViewById(R.id.eddate);
        edphnno=(EditText)findViewById(R.id.edphnno);
        edemail=(EditText)findViewById(R.id.edemail);

        alreadyregister = (TextView)findViewById(R.id.alredayregister);

        /*edname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edname.getText().length() <= 0){
                    edname.setError("Enter Name");
                    edname.requestFocus();
                }
            }
        });

        edusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edusername.getText().length() <= 0){
                    edusername.setError("Enter Username");
                    edusername.requestFocus();
                }
            }
        });

        edpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edpassword.getText().length() <= 0 ){
                    edpassword.setError("Enter Password");
                    edpassword.requestFocus();
                }
            }
        });

        eddate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (eddate.getText().length() <= 0){
                    eddate.setError("Enter Date");
                    eddate.requestFocus();
                }
            }
        });

        edemail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edemail.getText().length()<= 0){
                    edemail.setError("Enter Email");
                    edemail.requestFocus();
                }
            }
        });

        edphnno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edphnno.getText().length() <= 0 ){
                    edphnno.setError("Enter Valid Phone no");
                    edphnno.requestFocus();
                }
            }
        });*/

        submit=(Button)findViewById(R.id.submitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = edname.getText().toString();
                final String username = edusername.getText().toString();
                final String password = edpassword.getText().toString();
                final String date = eddate.getText().toString();
                final String email = edemail.getText().toString();
                final String phnno = edphnno.getText().toString();

                final ProgressDialog progressDialog = ProgressDialog.show(Registration.this,"Registration in process..","Please wait..",false,false);

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){
                                progressDialog.dismiss();
                                Toast.makeText(Registration.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Registration.this, MainActivity.class);
                                Registration.this.startActivity(intent);
                                finish();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this, "Unable to Register",Toast.LENGTH_LONG).show();
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,username,password,date,email,phnno,responseListener,errorListener);
                RequestQueue queue = Volley.newRequestQueue(Registration.this);
                queue.add(registerRequest);
            }
        });

        alreadyregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,MainActivity.class);
                Registration.this.startActivity(intent);
                Registration.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
