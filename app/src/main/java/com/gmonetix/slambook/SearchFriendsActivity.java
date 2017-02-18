package com.gmonetix.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SearchFriendsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText Name;
    private LinearLayout Search;
    private TextView tv1;
    private Utils utils;
    private String name;
    String url_search = "http://192.168.215.2/test/search.php";
    private static final String NAME_INTENT = "name";
    private static final String EMAIL_INTENT = "email";
    private static final String PHOTO_INTENT = "photo";
    private static final String USERNAME_INTENT = "username";
    private static final String DESCRIPTION_INTENT = "description";
    private static final String DOB_INTENT = "dob";
    public List<SearchFriendsModel> searchedFriends;
    private ListView searchedListView;
    SearchAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        searchedListView = (ListView) findViewById(R.id.search_list_view);

        Name = (EditText) findViewById(R.id.name_search);
        Search = (LinearLayout) findViewById(R.id.ll_btn_search);
        tv1 = (TextView) findViewById(R.id.tv_search);

        utils = new Utils();
        utils.setFont(SearchFriendsActivity.this,Name);
        utils.setFont(SearchFriendsActivity.this,tv1);
        searchedFriends = new ArrayList<>();

        searchedListView.setOnItemClickListener(this);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Name.getText().toString().trim();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url_search,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                                    JSONArray jsonArray = new JSONArray(s);
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        SearchFriendsModel model = new SearchFriendsModel();
                                        model.setCode(jsonObject.getString("code"));
                                        model.setName(jsonObject.getString("name"));
                                        model.setEmail(jsonObject.getString("email"));
                                        model.setImageUrl(jsonObject.getString("image"));
                                        model.setDescription(jsonObject.getString("description"));
                                        model.setUsername(jsonObject.getString("user_name"));
                                        model.setDob(jsonObject.getString("dob"));
                                        searchedFriends.add(model);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                finally {
                                    adapter = new SearchAdpater(SearchFriendsActivity.this,searchedFriends);
                                    searchedListView.setAdapter(adapter);
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                //Showing toast
                                Toast.makeText(SearchFriendsActivity.this, "Error", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name",name);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (searchedFriends !=null)
            searchedFriends = null;
        finish();
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
        startActivity(intent);

    }
}
