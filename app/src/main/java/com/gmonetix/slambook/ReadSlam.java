package com.gmonetix.slambook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.gmonetix.slambook.helper.ReadSlamAdapter;
import com.gmonetix.slambook.helper.ReadSlamModel;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_profile.UserHome;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadSlam extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String INTENT_FROM_USERNAME = "from_username";
    private static final String INTENT_IMAGE = "image";

    private ListView listView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private FloatingActionButton home;
    private LinearLayout llDataShow;
    private TextView tv1, tv2, tv3;

    private List<ReadSlamModel> Slams;
    private ReadSlamAdapter adapter;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils();
        utils.setThemeOnApp(ReadSlam.this,utils.getTheme(ReadSlam.this));
        setContentView(R.layout.activity_read_slam);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ReadSlam.this.setTitle("Slams");

        init();
        Slams = new ArrayList<>();
        listView.setOnItemClickListener(this);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadSlam.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(ReadSlam.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.read_slam_model,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ReadSlamModel model = new ReadSlamModel();
                                model.setName(jsonObject.getString(Const.USER_ACCOUNT_DATA_NAME));
                                model.setFromUsername(jsonObject.getString("from_user_name"));
                                model.setImage(jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE));
                                Slams.add(model);
                            }
                            adapter = new ReadSlamAdapter(ReadSlam.this,Slams);
                            listView.setAdapter(adapter);
                            llDataShow.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ReadSlam.this,"No slams found !",Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ReadSlam.this,"Error ! Please try after sometime",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(ReadSlam.this));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        utils.getUilInstance(ReadSlam.this);

        listView = (ListView) findViewById(R.id.read_slam_listView);
        progressBar = (ProgressBar) findViewById(R.id.read_slam_progrss_bar);
        home = (FloatingActionButton) findViewById(R.id.btn_home);

        llDataShow = (LinearLayout) findViewById(R.id.ll_no_received_slams);
        tv1 = (TextView) findViewById(R.id.read_slam_tv1);
        tv2 = (TextView) findViewById(R.id.read_slam_tv2);
        tv3 = (TextView) findViewById(R.id.read_slam_tv3);

        utils.setFont(ReadSlam.this,tv1);
        utils.setFont(ReadSlam.this,tv2);
        utils.setFont(ReadSlam.this,tv3);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ReadSlam.this, Slams.class);
        intent.putExtra(INTENT_FROM_USERNAME,Slams.get(position).getFromUsername());
        intent.putExtra(INTENT_IMAGE,Slams.get(position).getImage());
        startActivity(intent);
    }
}