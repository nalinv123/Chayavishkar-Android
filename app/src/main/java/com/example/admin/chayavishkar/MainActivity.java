package com.example.admin.chayavishkar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
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

public class MainActivity extends AppCompatActivity {

    Button loginbutton;
    EditText edusername,edpassword;
    TextView forgotpassword,tvregister;

    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store username of current logged in user
    public static final String USERNAME_SHARED_PREF = "username";

    //we will used this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGIDIN_SHARED_PREF = "loggedin";
    private boolean loggedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edusername =(EditText)findViewById(R.id.edusername);
        edpassword =(EditText)findViewById(R.id.edpassword);

        forgotpassword=(TextView)findViewById(R.id.forgotpassword);

        tvregister=(TextView)findViewById(R.id.tvregister);

        loginbutton =(Button) findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edusername.getText().toString();
                final String password = edpassword.getText().toString();

                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,"Login in process..","Please wait..",false,false);

                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean(LOGGIDIN_SHARED_PREF, true);
                                editor.putString(USERNAME_SHARED_PREF,  username);

                                editor.commit();

                                String name = jsonResponse.getString("name");
                                String username = jsonResponse.getString("username");
                                String date = jsonResponse.getString("date");
                                String email = jsonResponse.getString("email");
                                String phnno = jsonResponse.getString("phnno");

                                Intent intent = new Intent(MainActivity.this, Home.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("date", date);
                                intent.putExtra("email", email);
                                intent.putExtra("phnno",phnno);

                                startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed")
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
                        Toast.makeText(MainActivity.this,"Unable to Connect",Toast.LENGTH_LONG).show();
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responselistener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                MainActivity.this.startActivity(intent);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgotpassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedin = sharedPreferences.getBoolean(LOGGIDIN_SHARED_PREF,false);

        if (loggedin){
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You want exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}