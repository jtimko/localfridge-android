package com.localfridge.localfridge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class FragmentLogin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        Button btnLogin = v.findViewById(R.id.btnLogin);
        TextView regText = v.findViewById(R.id.regText);

        regText.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new FragmentRegistration();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_main, fragment);
                ft.commit();
            }
        });
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String url = "https://www.localfridge.com/app/phone-login.php";
                EditText txtUser = v.findViewById(R.id.loginUser);
                EditText txtPass = v.findViewById(R.id.loginPass);
                final String strUser = txtUser.getText().toString();
                final String strPass = txtPass.getText().toString();

                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("TAG", response);
                        if (response != "false") {
                            try {
                                JSONObject obj = new JSONObject(response);

                                Intent i = new Intent(getActivity(), WelcomeMain.class);
                                i.putExtra("user", obj.getString("userName"));
                                i.putExtra("id", obj.getInt("u_id"));
                                startActivity(i);
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "Incorrect username/password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //Log.i(TAG, "make an account!");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Must be connected online.", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user", strUser);
                        params.put("pass", strPass);
                        return params;
                    }
                };
                Volley.newRequestQueue(getActivity()).add(sr);
            }
        });
        return v;

    }

}
