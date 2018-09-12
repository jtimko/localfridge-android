package com.localfridge.localfridge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class FoodItemActivity extends AppCompatActivity {
    int userId;
    String email;
    String foodTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        Bundle extras = getIntent().getExtras();

        this.foodTitle = extras.getString("foodTitle");
        String foodAuthor = extras.getString("foodAuthor");
        String foodLoc = extras.getString("foodLoc");
        String foodDesc = extras.getString("foodDesc");
        this.userId = extras.getInt("getId");
        this.email = extras.getString("getEmail");

        TextView fTitle = findViewById(R.id.foodTitle);
        TextView fAuthor = findViewById(R.id.foodAuthor);
        TextView fLoc = findViewById(R.id.foodLoc);
        TextView fDesc = findViewById(R.id.foodDesc);
        Button btnEmail = findViewById(R.id.btnEmail);

        fTitle.setText(foodTitle);
        fAuthor.setText("Posted by: " + foodAuthor);
        fLoc.setText(foodLoc);
        fDesc.setText(foodDesc);


        btnEmail.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String uriText = "mailto:" + email + "?subject=" + Uri.encode("LocalFridge: " + foodTitle);
                Uri uri = Uri.parse(uriText);

                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(uri);
                startActivity(Intent.createChooser(i, "Send Email"));

            }
        });
    }
}
