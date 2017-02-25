package com.gmonetix.slambook.user_profile;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.ReadSlam;
import com.gmonetix.slambook.SearchFriendsActivity;
import com.gmonetix.slambook.SettingsActivity;
import com.gmonetix.slambook.WriteSlam;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.HomeAdapter;
import com.gmonetix.slambook.helper.HomeModel;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_login.UserLoginActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TextView tvNumberOfSlamsSent, tvNumberOfSlamsReceived,tv1,tv2;
    private ListView toListView, fromListView;

    private List<HomeModel> fromData;
    private HomeAdapter adapter;

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate  (savedInstanceState);
        setContentView(R.layout.activity_user_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserHome.this.setTitle("");
        init();
        fromData = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.get_image_using_username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HomeModel model = new HomeModel();
                                model.setImage(jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE));
                                model.setFromUserName(jsonObject.getString(Const.USER_ACCOUNT_DATA_USER_NAME));
                                fromData.add(model);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            adapter = new HomeAdapter(UserHome.this,fromData);
                            fromListView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(UserHome.this));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UserHome.this);
        requestQueue.add(stringRequest);

    }

    private void init() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        tvNumberOfSlamsReceived = (TextView) findViewById(R.id.tv_home_user_total_slams_received);
        tvNumberOfSlamsSent = (TextView) findViewById(R.id.tv_home_user_total_slams_sent);
        tv1 = (TextView) findViewById(R.id.tv_home_user1);
        tv2 = (TextView) findViewById(R.id.tv_home_user2);
        toListView = (ListView) findViewById(R.id.user_home_to_list_view);
        fromListView = (ListView) findViewById(R.id.user_home_from_list_view);

        utils = new Utils();
        utils.setFont(UserHome.this,tvNumberOfSlamsReceived);
        utils.setFont(UserHome.this,tvNumberOfSlamsSent);
        utils.setFont(UserHome.this,tv1);
        utils.setFont(UserHome.this,tv2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_user_profile_profile:
                startActivity(new Intent(UserHome.this,UserProfile.class));
                break;
            case R.id.nav_user_profile_search_friend:
                startActivity(new Intent(UserHome.this,SearchFriendsActivity.class));
                break;
            case R.id.nav_user_profile_write_slam:
                startActivity(new Intent(UserHome.this,WriteSlam.class));
                break;
            case R.id.nav_user_profile_read_slam:
                startActivity(new Intent(UserHome.this,ReadSlam.class));
                break;
            case R.id.nav_user_profile_settings:
                startActivity(new Intent(UserHome.this,SettingsActivity.class));
                break;
            case R.id.nav_user_profile_logout:
                utils.setLoginStatus(UserHome.this,false);
                startActivity(new Intent(UserHome.this,UserLoginActivity.class));
                finish();
                break;

            case R.id.nav_user_profile_help:
                break;
            case R.id.nav_user_profile_visit_us:
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
