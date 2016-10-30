package com.homerunsb.navigationdrawertest;

import android.graphics.Color;

public class MaterialDataItem {
    private String companyName, materialName;
    private int color, unitPrice;

    public MaterialDataItem(String companyName, String color, String materialName, int unitPrice) {
        this.companyName = companyName;
        this.color = Color.parseColor(color);
        this.materialName = materialName;
        this.unitPrice = unitPrice;
    }

    public int getColor() {
        return color;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
