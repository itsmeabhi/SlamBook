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
import com.gmonetix.slambook.SentSlamsActivty;
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

import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private TextView tvNumberOfSlamsSent, tvNumberOfSlamsReceived,tv1,tv2,tv11,tv12,tv13,tv14,tv15,tv16;
    private ListView toListView, fromListView;
    private LinearLayout llSent, llReceived;

    private List<HomeModel> fromData;
    private List<HomeModel> toData;
    private HomeAdapter adapter1;
    private HomeAdapter adapter2;

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
        toData = new ArrayList<>();

        StringRequest stringRequestReceived = new StringRequest(Request.Method.POST, Const.get_image_using_username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s1) {
                        try {
                            JSONArray jsonArray = new JSONArray(s1);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HomeModel model = new HomeModel();
                                model.setImage(jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE));
                                model.setFromUserName(jsonObject.getString(Const.USER_ACCOUNT_DATA_USER_NAME));
                                fromData.add(model);
                                utils.setSlamsReceived(UserHome.this,(i+1));
                                tvNumberOfSlamsReceived.setText("Slams received : "+ String.valueOf(utils.getSlamsReceived(UserHome.this)));
                            }
                            adapter1 = new HomeAdapter(UserHome.this,fromData);
                            fromListView.setAdapter(adapter1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            tvNumberOfSlamsReceived.setText("Slams received : "+ String.valueOf(utils.getSlamsReceived(UserHome.this)));
                        } finally {
                            if (utils.getSlamsReceived(UserHome.this)==0) {
                                llReceived.setVisibility(View.VISIBLE);
                            } else if(utils.getSlamsReceived(UserHome.this)>0){
                                llReceived.setVisibility(View.GONE);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                tvNumberOfSlamsReceived.setText("Slams received : "+ String.valueOf(utils.getSlamsReceived(UserHome.this)));
                if (utils.getSlamsReceived(UserHome.this)==0) {
                    llReceived.setVisibility(View.VISIBLE);
                } else if(utils.getSlamsReceived(UserHome.this)>0){
                    llReceived.setVisibility(View.GONE);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(UserHome.this));
                return params;
            }
        };

        StringRequest stringRequestSent = new StringRequest(Request.Method.POST, Const.slams_sent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s2) {
                        try {
                            JSONArray jsonArray = new JSONArray(s2);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HomeModel model = new HomeModel();
                                model.setImage(jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE));
                                model.setFromUserName(jsonObject.getString("to_user_name"));
                                toData.add(model);
                                utils.setSlamsSent(UserHome.this,(i+1));
                                tvNumberOfSlamsSent.setText("Slams sent : "+ String.valueOf(utils.getSlamsSent(UserHome.this)));
                            }
                            adapter2 = new HomeAdapter(UserHome.this,toData);
                            toListView.setAdapter(adapter2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            tvNumberOfSlamsSent.setText("Slams sent : "+ String.valueOf(utils.getSlamsSent(UserHome.this)));
                        } finally {
                            if (utils.getSlamsSent(UserHome.this)==0) {
                                llSent.setVisibility(View.VISIBLE);
                            } else if(utils.getSlamsSent(UserHome.this)>0){
                                llSent.setVisibility(View.GONE);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                tvNumberOfSlamsSent.setText("Slams sent : "+ String.valueOf(utils.getSlamsSent(UserHome.this)));
                if (utils.getSlamsSent(UserHome.this)==0) {
                    llSent.setVisibility(View.VISIBLE);
                } else if(utils.getSlamsSent(UserHome.this)>0){
                    llSent.setVisibility(View.GONE);
                }
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
        requestQueue.add(stringRequestReceived);
        requestQueue.add(stringRequestSent);

    }

    private void init() {
        utils = new Utils();
        utils.getUilInstance(UserHome.this);

        tvNumberOfSlamsReceived = (TextView) findViewById(R.id.tv_home_user_total_slams_received);
        tvNumberOfSlamsSent = (TextView) findViewById(R.id.tv_home_user_total_slams_sent);
        tv1 = (TextView) findViewById(R.id.tv_home_user1);
        tv2 = (TextView) findViewById(R.id.tv_home_user2);
        toListView = (ListView) findViewById(R.id.user_home_to_list_view);
        fromListView = (ListView) findViewById(R.id.user_home_from_list_view);

        llSent = (LinearLayout) findViewById(R.id.ll_no_sent_data);
        llReceived = (LinearLayout) findViewById(R.id.ll_no_received_data);

        tv11 = (TextView) findViewById(R.id.user_home_tv1);
        tv12 = (TextView) findViewById(R.id.user_home_tv2);
        tv13 = (TextView) findViewById(R.id.user_home_tv3);
        tv14 = (TextView) findViewById(R.id.user_home_tv4);
        tv15 = (TextView) findViewById(R.id.user_home_tv5);
        tv16 = (TextView) findViewById(R.id.user_home_tv6);

        utils.setFont(UserHome.this,tvNumberOfSlamsReceived);
        utils.setFont(UserHome.this,tvNumberOfSlamsSent);
        utils.setFont(UserHome.this,tv1);
        utils.setFont(UserHome.this,tv2);
        utils.setFont(UserHome.this,tv11);
        utils.setFont(UserHome.this,tv12);
        utils.setFont(UserHome.this,tv13);
        utils.setFont(UserHome.this,tv14);
        utils.setFont(UserHome.this,tv15);
        utils.setFont(UserHome.this,tv16);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_profile_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_user_profile_help:
                startActivity(Utils.openHelp());
                break;
            case R.id.toolbar_user_profile_visit_us:
                startActivity(Utils.openWebsite());
                break;
            case R.id.toolbar_user_profile_fb:
                startActivity(Utils.openFacebook(UserHome.this));
                break;
            case R.id.toolbar_user_profile_youtube:
                startActivity(Utils.openYoutube(UserHome.this));
                break;
            case R.id.toolbar_user_profile_exit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
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
            case R.id.nav_user_profile_read_slam:
                startActivity(new Intent(UserHome.this,ReadSlam.class));
                break;
            case R.id.nav_user_profile_sent_slams:
                startActivity(new Intent(UserHome.this, SentSlamsActivty.class));
                break;
            case R.id.nav_user_profile_settings:
                startActivity(new Intent(UserHome.this,SettingsActivity.class));
                break;
            case R.id.nav_user_profile_logout:
                utils.setLoginStatus(UserHome.this,false);
                utils.setSlamsSent(UserHome.this,0);
                utils.setSlamsReceived(UserHome.this,0);
                Intent intent = new Intent(UserHome.this,UserLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.nav_user_profile_exit:
                finishAffinity();
                break;

            case R.id.nav_user_profile_help:
                startActivity(Utils.openHelp());
                break;
            case R.id.nav_user_profile_visit_us:
                startActivity(Utils.openWebsite());
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
