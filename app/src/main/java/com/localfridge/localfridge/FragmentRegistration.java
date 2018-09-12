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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class FragmentRegistration extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_fragment_registration, container, false);

        Button btnReg = v.findViewById(R.id.btnReg);
        TextView txtLogin = v.findViewById(R.id.txtLogin);

        txtLogin.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new FragmentLogin();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_main, fragment);
                ft.commit();
            }
        });

        btnReg.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String url = "https://www.localfridge.com/app/phone-reg.php";
                EditText user = v.findViewById(R.id.regUser);
                EditText pass = v.findViewById(R.id.regPass);
                EditText email = v.findViewById(R.id.regEmail);
                EditText zip = v.findViewById(R.id.regZip);

                final String txtUser = user.getText().toString();
                final String txtPass = pass.getText().toString();
                final String txtEmail = email.getText().toString();
                final String txtZip = zip.getText().toString();

                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();

                        if (response.equals("success")) {
                            Toast.makeText(getContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            Fragment fragment = new FragmentLogin();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_main, fragment);
                            ft.commit();
                        } else {
                            TextView regErr = getActivity().findViewById(R.id.regErr);
                            regErr.setText(response);
                            regErr.setVisibility(View.VISIBLE);

                            //TextView regSuccess = getActivity().findViewById(R.id.regSuccess);
                            //regSuccess.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.i(TAG, error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("zCode", txtZip);
                        params.put("eMail", txtEmail);
                        params.put("uName", txtUser);
                        params.put("pWord", txtPass);

                        return params;
                    }
                };
                Volley.newRequestQueue(getActivity()).add(sr);
            }
        });

        return v;
    }
}
