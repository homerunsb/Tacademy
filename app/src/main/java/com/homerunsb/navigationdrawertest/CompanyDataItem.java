package com.homerunsb.navigationdrawertest;

import android.graphics.Color;

public class CompanyDataItem {
    private String name;
    private int color;

    public CompanyDataItem(String name, String color) {
        this.name = name;
        this.color = Color.parseColor(color);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
