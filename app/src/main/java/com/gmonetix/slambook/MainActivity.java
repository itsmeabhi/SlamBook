package com.gmonetix.slambook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button register;
    EditText etName, etEmail, etPassword, etUsername;
    String name, email, password, username;
    AlertDialog.Builder builder;
    String url_register = "http://192.168.215.2/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intitialize();
        builder = new AlertDialog.Builder(MainActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                username = etUsername.getText().toString();
                if (name.equals("") || email.equals("") || password.equals("") || username.equals("")){
                    builder.setTitle("Error!");
                    builder.setMessage("Fill everything");
                    displayAlert("Input_Error");
                } else{

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url_register,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {

                                    try {
                                        JSONArray jsonArray = new JSONArray(s);
                                        JSONObject objct = jsonArray.getJSONObject(0);
                                        String code = objct.getString("code");
                                        String message = objct.getString("message");
                                        builder.setTitle("server response");
                                        builder.setMessage(message);
                                        displayAlert(code);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("name",name);
                            params.put("email",email);
                            params.put("user_name",username);
                            params.put("password",password);

                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });

    }

    private void displayAlert(final String s) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(s.equals("Input_Error")){
                    etPassword.setText("");
                }
                else if (s.equals("reg_success")){
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }
                else if (s.equals("reg_failed")){
                    etName.setText("");
                    etUsername.setText("");
                    etUsername.setText("");
                    etPassword.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void intitialize() {
        register = (Button) findViewById(R.id.btn_register);
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etUsername = (EditText) findViewById(R.id.et_username);
    }
}
