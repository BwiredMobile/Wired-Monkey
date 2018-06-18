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