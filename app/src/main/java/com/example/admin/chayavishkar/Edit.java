package com.example.admin.chayavishkar;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Edit extends Fragment {

    EditText changepassword,changeemail,changephnno,oldpassword,oldemail,oldphnno;
    Button btchangepassword,btchangeemail,btchangephnno;

    public Edit() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_edit, container, false);
        changepassword = (EditText)scrollView.findViewById(R.id.changepassword);
        changeemail = (EditText)scrollView.findViewById(R.id.changeemail);
        changephnno = (EditText)scrollView.findViewById(R.id.changephnno);
        oldpassword = (EditText)scrollView.findViewById(R.id.oldpassword);
        oldemail = (EditText)scrollView.findViewById(R.id.oldemail);
        oldphnno = (EditText)scrollView.findViewById(R.id.oldphnno);

        btchangepassword = (Button)scrollView.findViewById(R.id.donebuttonpassword);
        btchangeemail = (Button)scrollView.findViewById(R.id.donebuttonemail);
        btchangephnno = (Button)scrollView.findViewById(R.id.donebuttonphnno);

        btchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oldpass = oldpassword.getText().toString();
                final String newpassword = changepassword.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(getActivity(), "Password has been Successfully changed",Toast.LENGTH_LONG).show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Failed To Change Password")
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
                        Toast.makeText(getActivity(),"Unable to Change Password",Toast.LENGTH_LONG).show();
                    }
                };
                editpasswordRequest editpassword = new editpasswordRequest(oldpass, newpassword, listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(editpassword);
            }
        });

        btchangeemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = oldemail.getText().toString();
                final String newemail = changeemail.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(getActivity(), "Email has been Successfully changed", Toast.LENGTH_LONG).show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Failed to change password")
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
                        Toast.makeText(getActivity(), "Unable to Change Email", Toast.LENGTH_LONG).show();
                    }
                };

                editemailrequest editemail = new editemailrequest(email, newemail, listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(editemail);
            }
        });

        btchangephnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oldphone = oldphnno.getText().toString();
                final String newphnno = changephnno.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                Toast.makeText(getActivity(), "Phone No has been Successfully changed", Toast.LENGTH_LONG).show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Failed to change Phone No")
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
                        Toast.makeText(getActivity(), "Unable to change Phone No", Toast.LENGTH_LONG).show();
                    }
                };

                editphnnorequest editphnno = new editphnnorequest(oldphone, newphnno, listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(editphnno);
            }
        });
        return scrollView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
