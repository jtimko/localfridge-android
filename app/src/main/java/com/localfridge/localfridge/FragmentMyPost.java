package com.localfridge.localfridge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class FragmentMyPost extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_my_post, container, false);

        RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final MyItemAdapter adapter;
        final List<MyItem> itemList = new ArrayList<>();

        adapter = new MyItemAdapter(getActivity(), itemList);
        recyclerView.setAdapter(adapter);


        final int user_id = getActivity().getIntent().getExtras().getInt("id");

        String url = "https://www.localfridge.com/app/phone-grabMealsById.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray itemObj = new JSONArray(response);
                    //Log.i(TAG, response);
                    for (int i = 0; i < itemObj.length(); i++) {
                        JSONObject jsonObj = itemObj.getJSONObject(i);
                        itemList.add(
                                new MyItem(
                                        jsonObj.getInt("m_id"),
                                        jsonObj.getString("mealTitle"),
                                        jsonObj.getString("mealDesc")
                                )
                        );

                    }
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    // null
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // null
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("data", "true");
                params.put("u_id", Integer.toString(user_id));

                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(sr);
        return v;
    }


}
