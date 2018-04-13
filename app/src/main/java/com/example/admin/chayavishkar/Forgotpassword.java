package com.example.admin.chayavishkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Forgotpassword extends AppCompatActivity {

    EditText edemail;
    Button resetbutton;
    private static final String FORGOTPASSWORD_URL = "http://nalinvaidya.16mb.com/forgotpassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        edemail = (EditText)findViewById(R.id.email);
        resetbutton = (Button)findViewById(R.id.resetbutton);

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, FORGOTPASSWORD_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(Forgotpassword.this,response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Forgotpassword.this, "Network Unavailable", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        final String email = edemail.getText().toString();
                        HashMap<String, String> params = new HashMap<>();
                        params.put("email", email);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Forgotpassword.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}
