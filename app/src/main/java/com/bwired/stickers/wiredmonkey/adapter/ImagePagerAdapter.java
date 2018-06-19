/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * This is an adapter class for view pager to load the images
 *
 * Developed by Riya Varghese
 */

package com.bwired.stickers.wiredmonkey.adapter;

import android.content.Context;
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
    private final Sticker[] stickers = new Sticker[12];

    public ImagePagerAdapter(Context context, Sticker[] stickers) {
        mContext = context;
        copyElementsToArray(stickers);
    }


    @Override
    public int getCount() {
        return stickers.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        final Sticker sticker = stickers[position];
        Picasso.with(mContext)
                .load(sticker.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(300, 300)
                .into(imageView);

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    private void copyElementsToArray(Sticker[] stickers) {
        for (int i = 0; i < stickers.length; i++) {
            this.stickers[i] = stickers[i];
        }

    }

}