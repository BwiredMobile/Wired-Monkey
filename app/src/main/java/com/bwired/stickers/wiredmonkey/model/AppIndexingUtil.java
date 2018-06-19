/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * This class is a firebase app indexing util to load stickers from firbase account
 *
 * Developed by Riya Varghese
 */

package com.bwired.stickers.wiredmonkey.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppIndexingUtil {
    private static final String STICKER_URL_PATTERN = "mystickers://sticker/%s";
    private static final String STICKER_PACK_URL_PATTERN = "mystickers://sticker/pack/%s";
    private static final String CONTENT_PROVIDER_STICKER_PACK_NAME = "Firebase Storage Content Pack";
    private static final String TAG = "AppIndexingUtil";
    public static final String FAILED_TO_INSTALL_STICKERS = "Failed to install stickers";

    public static void setStickers(final Context context, FirebaseAppIndex firebaseAppIndex) {
        try {
            List<Indexable> stickers = getIndexableStickers();
            Indexable stickerPack = getIndexableStickerPack(stickers);

            List<Indexable> indexables = new ArrayList<>(stickers);
            indexables.add(stickerPack);

            Task<Void> task = firebaseAppIndex.update(
                    indexables.toArray(new Indexable[indexables.size()]));

            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "Successfully added stickers", Toast.LENGTH_SHORT)
                            .show();
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, FAILED_TO_INSTALL_STICKERS, e);
                    Toast.makeText(context, FAILED_TO_INSTALL_STICKERS, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        } catch (IOException | FirebaseAppIndexingInvalidArgumentException e) {
            Log.e(TAG, "Unable to set stickers", e);
        }
    }

    private static Indexable getIndexableStickerPack(List<Indexable> stickers)
            throws IOException, FirebaseAppIndexingInvalidArgumentException {
        Indexable.Builder indexableBuilder = getIndexableBuilder(StickersDataFactory.iconUrl, STICKER_PACK_URL_PATTERN, stickers.size());
        indexableBuilder.put("hasSticker", stickers.toArray(new Indexable[stickers.size()]));
        return indexableBuilder.build();
    }

    private static List<Indexable> getIndexableStickers() throws IOException,
            FirebaseAppIndexingInvalidArgumentException {
        List<Indexable> indexableStickers = new ArrayList<>();

        for (int i = 0; i < StickersDataFactory.getAllStickerReference().size(); i++) {
            Indexable.Builder indexableStickerBuilder = getIndexableBuilder(StickersDataFactory.getAllStickerReference().get(i).getImageUrl(), STICKER_URL_PATTERN, i);
            indexableStickerBuilder.put("keywords", StickersDataFactory.getAllStickerReference().get(i).getName())
                    // StickerPack object that the sticker is part of.
                    .put("ispartOf", new Indexable.Builder("StickerPack")
                            .setName(CONTENT_PROVIDER_STICKER_PACK_NAME)
                            .build());

            indexableStickers.add(indexableStickerBuilder.build());
        }

        return indexableStickers;
    }

    private static Indexable.Builder getIndexableBuilder(String stickerURL, String urlPattern, int index)
            throws IOException {
        String url = String.format(urlPattern, index);

        Indexable.Builder indexableBuilder = new Indexable.Builder("StickerPack")
                // name of the sticker pack
                .setName(CONTENT_PROVIDER_STICKER_PACK_NAME)
                // Firebase App Indexing unique key that must match an intent-filter
                // (e.g. mystickers://stickers/pack/0)
                .setUrl(url)
                // (Optional) - Defaults to the first sticker in "hasSticker"
                // displayed as a category image to select between sticker packs that should
                // be representative of the sticker pack
                //.setImage(contentUri.toString())
                .setImage(stickerURL)
                // (Optional) - Defaults to a generic phrase
                // content description of the image that is used for accessibility
                // (e.g. TalkBack)
                .setDescription("Wired Monkey");

        return indexableBuilder;
    }
}