package com.localfridge.localfridge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyItemViewHolder> {
    private Context mCtx;
    private List<MyItem> itemList;

    public MyItemAdapter(Context mCtx, List<MyItem> itemList) {
        this.mCtx = mCtx;
        this.itemList = itemList;
    }

    @Override
    public MyItemAdapter.MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.post_item, null);

        MyItemAdapter.MyItemViewHolder itemView = new MyItemAdapter.MyItemViewHolder(view, mCtx, itemList);

        return itemView;
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.txtFoodTitle.setText(item.getFoodTitle());
        //holder.txtFoodDesc.setText(item.getFoodDesc());
        //holder.txtFoodLoc.setText(food.getFoodLoc());
        //holder.txtFoodAuthor.setText(food.getFoodAuthor());
        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(food.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ImageView imageView;
        TextView txtFoodTitle;//, txtFoodDesc, txtFoodLoc, txtFoodAuthor, txtFoodEmail;
        List<MyItem> items;
        Context ctx;

        public MyItemViewHolder(View itemView, Context ctx, List<MyItem> items) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.ctx = ctx;
            this.items = items;
            //imageView = itemView.findViewById(R.id.foodImg);
            txtFoodTitle = itemView.findViewById(R.id.foodTitle);
            //txtFoodDesc = itemView.findViewById(R.id.foodDesc);
            //txtFoodLoc = itemView.findViewById(R.id.foodLoc);
            //txtFoodAuthor = itemView.findViewById(R.id.foodAuthor);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            MyItem item = this.items.get(position);

            Intent i = new Intent(this.ctx, DelMealActivity.class);
            i.putExtra("foodTitle", item.getFoodTitle());
            i.putExtra("foodDesc", item.getFoodDesc());
            i.putExtra("m_Id", item.getId());

            this.ctx.startActivity(i);

        }
    }
}
