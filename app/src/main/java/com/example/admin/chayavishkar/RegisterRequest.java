package com.example.admin.chayavishkar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://nalinvaidya.16mb.com/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, String date, String email, String phnno, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, REGISTER_REQUEST_URL ,listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("date", date);
        params.put("email", email);
        params.put("phnno", phnno);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
