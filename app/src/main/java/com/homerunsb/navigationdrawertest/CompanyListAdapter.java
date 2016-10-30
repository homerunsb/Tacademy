package com.homerunsb.navigationdrawertest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter {

    private List<CompanyDataItem> dataItems;

    // Adapter constructor
    public CompanyListAdapter(List<CompanyDataItem> dataItems) {

        this.dataItems = dataItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View layoutView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_company_layout, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CompanyDataItem dataItem = dataItems.get(position);
        // Casting the viewHolder to MyViewHolder so I could interact with the views
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.colorBlock.setBackgroundColor(dataItem.getColor());
        myViewHolder.colorName.setText(dataItem.getName());
    }

    @Override
    public int getItemCount() {

        return dataItems.size();
    }

    /** This is our ViewHolder class */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView colorName;
        public View colorBlock;

        public MyViewHolder(View itemView) {
            super(itemView); // Must call super() first
            colorName = (TextView) itemView.findViewById(R.id.colorName);
            colorBlock = (View) itemView.findViewById(R.id.colorBlock);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("MyViewHolder", "Item clicked: " + colorName.getText().toString());
        }

    }
}