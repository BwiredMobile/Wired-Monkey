/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * Sticker Data Factory class where all stickers link in firebase are added.
 *
 * Developed by Riya Varghese
 */

package com.bwired.stickers.wiredmonkey.model;

import java.util.ArrayList;
import java.util.List;

public class StickersDataFactory {

    public static final String iconUrl = "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/cool.png?alt=media&token=c66e3115-dba8-4b6b-bd44-47ad4459a0ba";

    public static List<Sticker> getAllStickerReference() {

        Sticker[] stickers = {

                new Sticker("lol", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/lol.png?alt=media&token=6bd58c28-eb8e-4167-8efa-5ae8fee4df45", 0),
                new Sticker("cool", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/cool.png?alt=media&token=c66e3115-dba8-4b6b-bd44-47ad4459a0ba", 1),
                new Sticker("umm", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/umm.png?alt=media&token=106ad95d-47ac-4927-be21-295b20e6d4f6", 2),
                new Sticker("sorry", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/sorry.png?alt=media&token=1f33d45a-a599-4cf4-83b3-fdff14743eca", 3),
                new Sticker("he-he-he", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/he-he-he.png?alt=media&token=101515d5-6cd3-433e-a0f7-602d9e887025", 4),
                new Sticker("really", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/really.png?alt=media&token=470d4b73-5b77-4e21-81ab-b7e6258013a1", 5),
                new Sticker("not-impressed", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/not-impressed.png?alt=media&token=fd012bd5-2333-4e53-9d3e-1d6bf5463fd5", 6),
                new Sticker("oh-no", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/oh-no.png?alt=media&token=4bf5d373-4658-458e-a2fa-bf454fcfb161", 7),
                new Sticker("please", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/please.png?alt=media&token=1d89dc2e-f3b6-4c4b-b6d1-173170c4dac8", 8),
                new Sticker("sneaky", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/sneaky.png?alt=media&token=a10071ba-4ccf-496a-ba6e-f81ff7aa2887", 9),
                new Sticker("wtf", "https://firebasestorage.googleapis.com/v0/b/wired-monkey.appspot.com/o/wtf.png?alt=media&token=fb1c2ce9-69ab-4c3e-91d6-465a468137f7", 10),

        };
        List<Sticker> stickerList = new ArrayList<>();
        for (int i = 0; i < stickers.length; i++) {
            stickerList.add(new Sticker(stickers[i].getName(), stickers[i].getImageUrl(), stickers[i].getPosition()));
        }
        return stickerList;
    }
}