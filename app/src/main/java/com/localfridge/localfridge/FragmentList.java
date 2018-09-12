package com.localfridge.localfridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class FragmentList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_list, container, false);
        RecyclerView recyclerView;
        final FoodAdapter adapter;

        final List<Food> foodList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new FoodAdapter(getActivity(), foodList);
        recyclerView.setAdapter(adapter);

        String url = "https://www.localfridge.com/app/phone-grabMeals.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray foodResp = new JSONArray(response);
                    for (int i = 0; i < foodResp.length(); i++) {
                        JSONObject foodObj = foodResp.getJSONObject(i);
                        foodList.add(
                                new Food(
                                        foodObj.getInt("u_id"),
                                        R.drawable.ic_launcher_background,
                                        foodObj.getString("mealTitle"),
                                        foodObj.getString("mealDesc"),
                                        foodObj.getString("cityName"),
                                        foodObj.getString("userName"),
                                        foodObj.getString("email")
                        ));
                    }

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    //Log.i(TAG, "Error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i(TAG, "err: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("data", "value");

                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(sr);

        return v;
    }


}
