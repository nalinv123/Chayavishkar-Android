package com.example.admin.chayavishkar;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadPhoto extends Fragment  {

    ImageView ImageToUpload;
    EditText edImageToUploadName;
    Button upload;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private static final String UPLOAD_URL = "http://nalinvaidya.16mb.com/SavePictures.php";
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    public UploadPhoto() {
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
        final ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_upload_photo, container, false);

        ImageToUpload = (ImageView)scrollView.findViewById(R.id.ImageToUpload);

        edImageToUploadName = (EditText)scrollView.findViewById(R.id.edUploadImageName);

        upload = (Button)scrollView.findViewById(R.id.btUploadImage);

        ImageToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null){
            Uri selectimage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectimage);
                ImageToUpload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imagebytes = byteArrayOutputStream.toByteArray();
                    String encodedimage = Base64.encodeToString(imagebytes, Base64.DEFAULT);
                    return encodedimage;
                }

    private void UploadImage(){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Uploading Image..","Please Wait..",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Image Uploaded", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Unable to Upload Image", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);
                String name = edImageToUploadName.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}