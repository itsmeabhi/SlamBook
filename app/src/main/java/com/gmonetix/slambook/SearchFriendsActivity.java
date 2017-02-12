package com.gmonetix.slambook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.gmonetix.slambook.helper.Font;
import com.gmonetix.slambook.helper.SearchAdpater;
import com.gmonetix.slambook.helper.SearchFriendsModel;
import com.gmonetix.slambook.user_login.UserLoginActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFriendsActivity extends AppCompatActivity {

    private EditText Name;
    private Button Search;
    String name;
    String url_search = "http://www.gmonetix.com/slambook/search.php";

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
        Search = (Button) findViewById(R.id.btn_search);

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
                                    //Gson gson = new Gson();
                                    SearchFriendsModel model = new SearchFriendsModel();
                                    JSONArray jsonArray = new JSONArray(s);
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        model.setCode(jsonObject.getString("code"));
                                        model.setName(jsonObject.getString("name"));
                                        model.setEmail(jsonObject.getString("email"));
                                        model.setImageUrl(jsonObject.getString("image"));
                                        model.setDescription(jsonObject.getString("description"));
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
}
