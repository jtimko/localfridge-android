package com.localfridge.localfridge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FragmentGive extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_fragment_give, container, false);
        Button btnSubmit = v.findViewById(R.id.foodSubmit);
        final int user_id = getActivity().getIntent().getExtras().getInt("id");


        btnSubmit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String url = "https://www.localfridge.com/app/phone-addMeals.php";
                EditText mTitle = v.findViewById(R.id.foodTitle);
                EditText mDesc  = v.findViewById(R.id.foodDesc);
                final String txtTitle = mTitle.getText().toString();
                final String txtDesc  = mDesc.getText().toString();

                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();
                        if (response.equals("success")) {
                            Intent i = new Intent(getActivity(), WelcomeMain.class);
                            i.putExtra("id", user_id);
                            startActivity(i);
                            Toast.makeText(getContext(), "Item Posted!", Toast.LENGTH_SHORT);
                        } else  {
                            Log.i(TAG, "here " + response);
                            TextView postErr = getActivity().findViewById(R.id.postErr);
                            postErr.setText(response);
                            postErr.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("add", "true");
                        params.put("mealTitle", txtTitle);
                        params.put("mealDesc" , txtDesc);
                        params.put("id", Integer.toString(user_id));

                        return params;
                    }
                };
                Volley.newRequestQueue(getActivity()).add(sr);
            }
        });

        return v;
    }
}
