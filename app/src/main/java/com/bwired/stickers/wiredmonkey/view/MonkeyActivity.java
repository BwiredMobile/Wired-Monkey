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
    ProgressDialog _dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


//        TextView tx = (TextView)findViewById(R.id.txtView);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/slackey_regular.ttf");
//        tx.setTypeface(custom_font);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Object[] objectList = StickersDataFactory.getAllStickerReference().toArray();
        final Sticker[] stickerArray = Arrays.copyOf(objectList, objectList.length, Sticker[].class);
        final PagerAdapter stickerAdapter = new InfinitePagerAdapter(new ImagePagerAdapter(this, stickerArray));
        viewPager.setOffscreenPageLimit(12);
        viewPager.setAdapter(stickerAdapter);




        ImageView left_btn = (ImageView) findViewById(R.id.left);
        ImageView right_btn = (ImageView) findViewById(R.id.right);
        ImageView share_btn = (ImageView) findViewById(R.id.share);
        ImageView link_btn = (ImageView) findViewById(R.id.link);

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Child Count Left..."+viewPager.getCurrentItem());
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));

//                System.out.println("Child Count If..."+(viewPager.getCurrentItem()+1));
               /* if(viewPager.getCurrentItem() >= viewPager.getAdapter().getCount()) {
                    viewPager.getAdapter().notifyDataSetChanged();
                }*/
            }
        });

        link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WebView mWebview  = new WebView(MonkeyActivity.this);
                mWebview.getSettings().setJavaScriptEnabled(true);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bwired.ca/"));
                startActivity(intent);


//                setContentView(mWebview );
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(stickerArray[viewPager.getCurrentItem()].getImageUrl()).into(new com.squareup.picasso.Target() {
                    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        prepareShareIntent(bitmap);
                    }
                    @Override public void onBitmapFailed(Drawable errorDrawable) { }
                    @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
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
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }



}
