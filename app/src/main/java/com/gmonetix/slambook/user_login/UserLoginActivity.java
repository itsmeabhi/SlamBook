package com.gmonetix.slambook.user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.SearchFriendsActivity;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Font;
import com.gmonetix.slambook.user_registration.UserRegistrationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    private EditText loginUserName, loginPassword;
    private String username, password;
    Button signIn;
    private TextView toolBarTextView;
    private Font font;
    private TextView signUp, Search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initialize();

        toolBarTextView.setText("Login");
        font.setFont(getApplicationContext(),toolBarTextView);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this, UserRegistrationActivity.class));
                finish();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this, SearchFriendsActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = loginUserName.getText().toString();
                password = loginPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.login_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    Toast.makeText(getApplicationContext(),s + code,Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(UserLoginActivity.this, volleyError.getMessage()+ "Error", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user_name",username);
                        params.put("password",password);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }

    private void initialize() {

        toolBarTextView = (TextView) findViewById(R.id.toolbar_textView);
        signIn = (Button) findViewById(R.id.signin);
        loginPassword = (EditText) findViewById(R.id.password_login);
        loginUserName = (EditText) findViewById(R.id.username_login);
        signUp = (TextView) findViewById(R.id.register_from_login_screen);
        Search = (TextView) findViewById(R.id.search_friends_tv);
        font = new Font();

    }
}