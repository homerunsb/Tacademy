package com.homerunsb.navigationdrawertest;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by homer on 2016-10-26.
 */

public class ImageViewPagerAdapter extends PagerAdapter {
    private int[] image_resources = {R.drawable.sample_01, R.drawable.sample_02, R.drawable.sample_03,
            R.drawable.sample_04, R.drawable.sample_05};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public ImageViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.app_bar_image_viewpager, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
//        TextView textView = (TextView) view.findViewById(R.id.image_count);
//        textView.setText("image : " + position);
        imageView.setImageResource(image_resources[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
