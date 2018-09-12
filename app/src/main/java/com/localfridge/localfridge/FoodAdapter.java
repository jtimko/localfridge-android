package com.localfridge.localfridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context mCtx;
    private List<Food> foodList;

    public FoodAdapter(Context mCtx, List<Food> foodList) {
        this.mCtx = mCtx;
        this.foodList = foodList;
    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item, null);

        FoodViewHolder foodView = new FoodViewHolder(view, mCtx, foodList);

        return foodView;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.txtFoodTitle.setText(food.getFoodTitle());
        holder.txtFoodDesc.setText(food.getFoodDesc());
        holder.txtFoodLoc.setText(food.getFoodLoc());
        holder.txtFoodAuthor.setText(food.getFoodAuthor());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(food.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView txtFoodTitle, txtFoodDesc, txtFoodLoc, txtFoodAuthor, txtFoodEmail;
        List<Food> foods;
        Context ctx;

        public FoodViewHolder(View itemView, Context ctx, List<Food> foods) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.ctx = ctx;
            this.foods = foods;
            imageView = itemView.findViewById(R.id.foodImg);
            txtFoodTitle = itemView.findViewById(R.id.foodTitle);
            txtFoodDesc = itemView.findViewById(R.id.foodDesc);
            txtFoodLoc = itemView.findViewById(R.id.foodLoc);
            txtFoodAuthor = itemView.findViewById(R.id.foodAuthor);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Food food = this.foods.get(position);

            Intent i = new Intent(this.ctx, FoodItemActivity.class);
            i.putExtra("foodTitle", food.getFoodTitle());
            i.putExtra("foodAuthor", food.getFoodAuthor());
            i.putExtra("foodLoc", food.getFoodLoc());
            i.putExtra("foodDesc", food.getFoodDesc());
            i.putExtra("getId", food.getId());
            i.putExtra("getEmail", food.getFoodEmail());

            this.ctx.startActivity(i);

        }
    }
}
