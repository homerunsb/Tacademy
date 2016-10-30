package com.homerunsb.navigationdrawertest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MaterialListAdapter extends RecyclerView.Adapter {

    private List<MaterialDataItem> dataItems;

    // Adapter constructor
    public MaterialListAdapter(List<MaterialDataItem> dataItems) {
        this.dataItems = dataItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View layoutView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_material_layout, null);
        return new MyViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        MaterialDataItem dataItem = dataItems.get(position);
        // Casting the viewHolder to MyViewHolder so I could interact with the views
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.colorBlock.setBackgroundColor(dataItem.getColor());
        myViewHolder.companyName.setText(dataItem.getCompanyName());
        myViewHolder.materialName.setText(dataItem.getMaterialName());
        myViewHolder.unitPrice.setText(String.valueOf(dataItem.getUnitPrice()));
    }

    @Override
    public int getItemCount() {

        return dataItems.size();
    }

    /** This is our ViewHolder class */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView companyName, materialName, unitPrice;
        public ImageView colorBlock;

        public MyViewHolder(View itemView) {
            super(itemView); // Must call super() first
            companyName = (TextView) itemView.findViewById(R.id.companyName);
            colorBlock = (ImageView) itemView.findViewById(R.id.colorBlock);
            materialName = (TextView) itemView.findViewById(R.id.materialName);
            unitPrice = (TextView) itemView.findViewById(R.id.unitPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("MyViewHolder", "Item clicked: " + companyName.getText().toString());
        }

    }
}