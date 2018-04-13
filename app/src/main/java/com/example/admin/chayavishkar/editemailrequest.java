package com.example.admin.chayavishkar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class editemailrequest extends StringRequest {

    private static final String EDIT_EMAIL_URL = "http://nalinvaidya.16mb.com/editemail.php";
    private HashMap<String, String> params;

    public editemailrequest(String oldemail, String newemail, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, EDIT_EMAIL_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("newemail", newemail);
        params.put("oldemail", oldemail);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
