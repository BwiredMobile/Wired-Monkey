/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * Activity class to load the stickers, share and go to the website
 *
 * Developed by Riya Varghese
 */

package com.bwired.stickers.wiredmonkey.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwired.stickers.wiredmonkey.BuildConfig;
import com.bwired.stickers.wiredmonkey.R;
import com.bwired.stickers.wiredmonkey.adapter.ImagePagerAdapter;
import com.bwired.stickers.wiredmonkey.adapter.InfinitePagerAdapter;
import com.bwired.stickers.wiredmonkey.model.Sticker;
import com.bwired.stickers.wiredmonkey.model.StickersDataFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MonkeyActivity extends Activity {
    ViewPager viewPager;
    ProgressDialog _dialog;
    FileOutputStream out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        viewPager = findViewById(R.id.view_pager);
        Object[] objectList = StickersDataFactory.getAllStickerReference().toArray();
        final Sticker[] stickerArray = Arrays.copyOf(objectList, objectList.length, Sticker[].class);
        final PagerAdapter stickerAdapter = new InfinitePagerAdapter(new ImagePagerAdapter(this, stickerArray));
        viewPager.setOffscreenPageLimit(12);
        viewPager.setAdapter(stickerAdapter);


        ImageView left_btn = findViewById(R.id.left);
        ImageView right_btn = findViewById(R.id.right);
        ImageView share_btn = findViewById(R.id.share);
        ImageView link_btn = findViewById(R.id.link);

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));
            }
        });

        link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WebView mWebview = new WebView(MonkeyActivity.this);
                mWebview.getSettings().setJavaScriptEnabled(true);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bwired.ca/"));
                startActivity(intent);
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(stickerArray[viewPager.getCurrentItem()].getImageUrl()).into(new com.squareup.picasso.Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        prepareShareIntent(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });

            }
        });


    }

    private void prepareShareIntent(Bitmap bmp) {
        Uri bmpUri = getLocalBitmapUri(bmp); // see previous remote images section
        // Construct share intent as described above based on bitmap

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Sticker"));

    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            System.out.println("Exception.." + e);
        } finally {
            // TODO some more try catching here so that if output closing
            // fails you can still try to close second one and log the errors!
            try {
                out.flush();
                out.close();
                out = null;
                System.gc();
            } catch (Exception e) {
                System.out.println("Exception.." + e);
            }
        }
        return bmpUri;
    }


}
