/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * This class acts as a splash screen till the time the animation time works out.
 *
 * Developed by Riya Varghese
 */
package com.bwired.stickers.wiredmonkey.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bwired.stickers.wiredmonkey.R;
import com.bwired.stickers.wiredmonkey.service.AppIndexingUpdateService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppIndexingUpdateService.enqueueWork(MainActivity.this);

        ImageView imageView = (ImageView) findViewById(R.id.anim_monkey);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        imageView.startAnimation(fadeInAnimation);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this, MonkeyActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 3000);

    }
}
