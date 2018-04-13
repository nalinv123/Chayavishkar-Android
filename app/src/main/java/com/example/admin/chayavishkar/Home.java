package com.example.admin.chayavishkar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerlistener;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private myAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        drawerlistener=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerlistener);

        listView=(ListView)findViewById(R.id.drawerlist);
        myAdapter = new myAdapter(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(this);

        fragmentManager = getSupportFragmentManager();
        loadSelection(0);
    }

    private void loadSelection(int i){
        listView.setItemChecked(i,true);
        switch (i){
            case 0:
                About about = new About();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,about);
                fragmentTransaction.commit();
                break;
            case 1:
                Home1 home1 = new Home1();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,home1);
                fragmentTransaction.commit();
                break;
            case 2:
                Edit edit = new Edit();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,edit);
                fragmentTransaction.commit();
                break;
            case 3:
                UploadPhoto uploadPhoto = new UploadPhoto();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,uploadPhoto);
                fragmentTransaction.commit();
                break;
            case 4:
                Rules rules = new Rules();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent, rules);
                fragmentTransaction.commit();
                break;
            case 5:
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("Are you sure you want to logout");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putBoolean(MainActivity.LOGGIDIN_SHARED_PREF, false);

                        editor.putString(MainActivity.USERNAME_SHARED_PREF, "");

                        editor.commit();

                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        finish();
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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerlistener.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerlistener.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerlistener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(listView);
        setItem(position);
    }

    public void setItem(int position){
        listView.setItemChecked(position, true);
        setTitle(myAdapter.menu[position]);
    }

    public void setTitle(String title){

        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
class myAdapter extends BaseAdapter{

    private Context context;
    String[] menu;
    int[] images = {R.drawable.about,R.drawable.home,R.drawable.editprofile,R.drawable.uploadphoto,R.drawable.rules,R.drawable.logout};

    public myAdapter(Context context){
        this.context = context;
        menu = context.getResources().getStringArray(R.array.menu);
    }
    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public Object getItem(int position) {
        return menu[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row,parent,false);
        }
        else {
            row = convertView;
        }
        TextView titletextView = (TextView)row.findViewById(R.id.textView);
        ImageView titleimageView = (ImageView)row.findViewById(R.id.imageView);
        titletextView.setText(menu[position]);
        titleimageView.setImageResource(images[position]);

        return row;
    }
}