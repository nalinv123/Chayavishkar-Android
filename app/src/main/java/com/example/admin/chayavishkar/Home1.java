package com.example.admin.chayavishkar;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

public class Home1 extends Fragment {

    EditText edname,edusername,eddate,edemail,edphnno;

    public Home1() {
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

        final ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_home1, container, false);

        edname = (EditText)scrollView.findViewById(R.id.edname);
        edusername = (EditText)scrollView.findViewById(R.id.edusername);
        eddate = (EditText)scrollView.findViewById(R.id.eddate);
        edemail = (EditText)scrollView.findViewById(R.id.edemail);
        edphnno = (EditText)scrollView.findViewById(R.id.edphnno);

        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String date = intent.getStringExtra("date");
        String email = intent.getStringExtra("email");
        String phnno = intent.getStringExtra("phnno");

        edname.setText(name);
        edusername.setText(username);
        eddate.setText(date);
        edemail.setText(email);
        edphnno.setText(phnno);
        return  scrollView;
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