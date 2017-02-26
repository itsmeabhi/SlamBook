package com.gmonetix.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.SearchAdpater;
import com.gmonetix.slambook.helper.SearchFriendsModel;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_profile.FriendProfile;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFriendsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private EditText etName;
    private LinearLayout Search;
    private TextView tv1, tv_friend_name_search;
    private ListView searchedListView;
    private Toolbar toolbar;

    public List<SearchFriendsModel> searchedFriends ;
    private Utils utils;
    private String name;
    private SearchAdpater adapter;
    private boolean firstRun = true;

    private static final String NAME_INTENT = "name";
    private static final String EMAIL_INTENT = "email";
    private static final String PHOTO_INTENT = "photo";
    private static final String USERNAME_INTENT = "username";
    private static final String DESCRIPTION_INTENT = "description";
    private static final String DOB_INTENT = "dob";
    private static final String GENDER_INTENT = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SearchFriendsActivity.this.setTitle("Search");

        init();

        searchedFriends = new ArrayList<>();
        searchedListView.setOnItemClickListener(this);
        Search.setOnClickListener(this);
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

        searchedListView = (ListView) findViewById(R.id.search_list_view);
        etName = (EditText) findViewById(R.id.name_search);
        Search = (LinearLayout) findViewById(R.id.ll_btn_search);
        tv1 = (TextView) findViewById(R.id.tv_search);
        tv_friend_name_search = (TextView) findViewById(R.id.tv_friend_name_search);

        utils = new Utils();
        utils.setFont(SearchFriendsActivity.this,etName);
        utils.setFont(SearchFriendsActivity.this,tv1);
        utils.setFont(SearchFriendsActivity.this,tv_friend_name_search);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (searchedFriends !=null)
            searchedFriends.clear();
        SearchFriendsActivity.this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SearchFriendsActivity.this, FriendProfile.class);
        intent.putExtra(NAME_INTENT,searchedFriends.get(position).getName());
        intent.putExtra(EMAIL_INTENT,searchedFriends.get(position).getEmail());
        intent.putExtra(USERNAME_INTENT,searchedFriends.get(position).getUsername());
        intent.putExtra(DESCRIPTION_INTENT,searchedFriends.get(position).getDescription());
        intent.putExtra(PHOTO_INTENT,searchedFriends.get(position).getImageUrl());
        intent.putExtra(DOB_INTENT,searchedFriends.get(position).getDob());
        intent.putExtra(GENDER_INTENT,searchedFriends.get(position).getGender());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_btn_search:
                name = etName.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(SearchFriendsActivity.this,"Enter friend's name to search !",Toast.LENGTH_SHORT).show();
                } else {

                    if (firstRun) {
                        firstRun = false;
                    } else {
                        adapter = null;
                        searchedFriends.clear();
                        searchedListView.setAdapter(null);
                    }

                    RequestQueue requestQueue = Volley.newRequestQueue(SearchFriendsActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.url_search,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(s);
                                        for(int i=0;i<jsonArray.length();i++)
                                        {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            SearchFriendsModel model = new SearchFriendsModel();
                                            model.setCode(jsonObject.getString(Const.PHP_CODE));
                                            model.setName(jsonObject.getString(Const.USER_ACCOUNT_DATA_NAME));
                                            model.setEmail(jsonObject.getString(Const.USER_ACCOUNT_DATA_EMAIL));
                                            model.setImageUrl(jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE));
                                            model.setDescription(jsonObject.getString(Const.USER_ACCOUNT_DATA_DESCRIPTION));
                                            model.setUsername(jsonObject.getString(Const.USER_ACCOUNT_DATA_USER_NAME));
                                            model.setDob(jsonObject.getString(Const.USER_ACCOUNT_DATA_DOB));
                                            model.setGender(jsonObject.getString(Const.USER_ACCOUNT_DATA_GENDER));
                                            searchedFriends.add(model);

                                            adapter = new SearchAdpater(SearchFriendsActivity.this,searchedFriends);
                                            searchedListView.setAdapter(adapter);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(SearchFriendsActivity.this,"No friends found with given name !",Toast.LENGTH_LONG).show();
                                    }
                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(SearchFriendsActivity.this,"Error ! Please try after sometime",Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put(Const.USER_ACCOUNT_DATA_NAME,name);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                break;
        }
    }

}