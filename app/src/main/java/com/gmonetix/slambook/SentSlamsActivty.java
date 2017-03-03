package com.gmonetix.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
import com.gmonetix.slambook.helper.SentSlamAdapter;
import com.gmonetix.slambook.helper.SentSlamModel;
import com.gmonetix.slambook.helper.Utils;
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

public class SentSlamsActivty extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView;

    private Utils utils;
    private List<SentSlamModel> sentSlams;
    private SentSlamAdapter adapter;

    private final static String INTENT_USERNAME = "username";
    private final static String INTENT_NAME = "name";
    private final static String INTENT_IMAGE = "image";
    private final static String INTENT_GENDER = "gender";
    private final static String INTENT_SENT_ON = "sent_on";
    private final static String INTENT_UPDATED_ON = "updated_on";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_slams_activty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SentSlamsActivty.this.setTitle("Slams sent");

        init();
        sentSlams = new ArrayList<>();
        listView.setOnItemClickListener(this);

        StringRequest rqst = new StringRequest(Request.Method.POST, Const.slams_sent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String data) {
                        try {
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                SentSlamModel model = new SentSlamModel();
                                if (jsonObject.getString("code").equals("success")) {
                                    model.setUsername(jsonObject.getString("to_user_name"));
                                    model.setName(jsonObject.getString("name"));
                                    model.setImage(jsonObject.getString("image"));
                                    model.setGender(jsonObject.getString("gender"));
                                    model.setSentOn(jsonObject.getString("sent_on"));
                                    model.setUpdatedOn(jsonObject.getString("updated_on"));
                                    sentSlams.add(model);
                                } else if (jsonObject.getString("code").equals("failed")){
                                    Toast.makeText(SentSlamsActivty.this,"Error occurred ! Please try again later !",Toast.LENGTH_SHORT).show();
                                }
                                adapter = new SentSlamAdapter(SentSlamsActivty.this,sentSlams);
                                listView.setAdapter(adapter);
                                TextView textView = (TextView) findViewById(R.id.sent_slams_tv_show);
                                textView.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SentSlamsActivty.this,e+"\n"+"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                Toast.makeText(SentSlamsActivty.this,volleyError+"\n"+"Error",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(SentSlamsActivty.this));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(SentSlamsActivty.this);
        queue.add(rqst);


    }

    private void init() {

        listView = (ListView) findViewById(R.id.sent_slams_list_view);

        utils = new Utils();

        utils.getUilInstance(SentSlamsActivty.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SentSlamsActivty.this,SlamsSent.class);
        intent.putExtra(INTENT_USERNAME,sentSlams.get(position).getUsername());
        intent.putExtra(INTENT_NAME,sentSlams.get(position).getName());
        intent.putExtra(INTENT_IMAGE,sentSlams.get(position).getImage());
        intent.putExtra(INTENT_GENDER,sentSlams.get(position).getGender());
        intent.putExtra(INTENT_SENT_ON,sentSlams.get(position).getSentOn());
        intent.putExtra(INTENT_UPDATED_ON,sentSlams.get(position).getUpdatedOn());
        startActivity(intent);
    }
}
