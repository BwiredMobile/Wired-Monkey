package com.bwired.stickers.wiredmonkey.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwired.stickers.wiredmonkey.R;
import com.bwired.stickers.wiredmonkey.model.Sticker;
import com.squareup.picasso.Picasso;

public  class ImagePagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final Sticker[] stickers;

    public ImagePagerAdapter(Context context, Sticker[] stickers) {
        this.mContext = context;
        this.stickers = stickers;
    }


    @Override
    public int getCount() {
        return stickers.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = mContext;
        ImageView imageView = new ImageView(context);
        final Sticker sticker = stickers[position];
        Picasso.with(mContext)
                .load(sticker.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(300, 300)
                .into(imageView);

        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}