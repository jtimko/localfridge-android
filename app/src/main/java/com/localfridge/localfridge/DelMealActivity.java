package com.localfridge.localfridge;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class DelMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_meal);

        Bundle extra = getIntent().getExtras();

        String foodTitle = extra.getString("foodTitle");
        String foodDesc = extra.getString("foodDesc");
        final int m_id = extra.getInt("m_Id");
        final int user_id = this.getIntent().getExtras().getInt("id");

        TextView viewFoodTitle = findViewById(R.id.foodTitle);
        TextView viewFoodDesc = findViewById(R.id.foodDesc);
        Button btnDel = findViewById(R.id.btnDel);

        viewFoodTitle.setText(foodTitle);
        viewFoodDesc.setText(foodDesc);

        btnDel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String url = "https://www.localfridge.com/app/phone-delMeal.php";
                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();

                        if (response.equals("deleted")) {
                            Toast.makeText(DelMealActivity.this, "Post was deleted!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(DelMealActivity.this, WelcomeMain.class);
                            i.putExtra("id", user_id);
                            startActivity(i);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("data", "value");
                        params.put("m_Id", Integer.toString(m_id));

                        return params;
                    }
                };
                Volley.newRequestQueue(view.getContext()).add(sr);
            }
        });

    }
}
