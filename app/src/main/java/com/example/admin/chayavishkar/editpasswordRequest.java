package com.example.admin.chayavishkar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class editpasswordRequest extends StringRequest {

    private static final String EDIT_PASSWORD_URL = "http://nalinvaidya.16mb.com/editpassword.php";
    private Map<String, String> params;

    public editpasswordRequest(String oldpassword, String newpassword, Response.Listener listener, Response.ErrorListener errorListener){
        super(Method.POST, EDIT_PASSWORD_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("newpassword", newpassword);
        params.put("oldpassword", oldpassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
