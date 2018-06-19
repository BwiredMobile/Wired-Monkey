/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * Model class for Sticker
 *
 * Developed by Riya Varghese
 */
package com.bwired.stickers.wiredmonkey.model;

public class Sticker {

    String name;
    String imageUrl;
    int position;

    public Sticker(String name, String imageUrl, int position) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.position  = position;
    }

    public String getName() {
        return this.name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setURL(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPosition() {return this.position;}

}