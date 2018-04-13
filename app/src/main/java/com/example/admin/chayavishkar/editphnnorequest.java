package com.example.admin.chayavishkar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class editphnnorequest extends StringRequest{

    private static final String EDIT_PHNNO_URL = "http://nalinvaidya.16mb.com/editphnno.php";
    private HashMap<String, String> params;

    public editphnnorequest(String oldphnno, String newphnno, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, EDIT_PHNNO_URL, listener, errorListener);

        params = new HashMap<>();
        params.put("newphnno", newphnno);
        params.put("oldphnno", oldphnno);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
