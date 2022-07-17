package com.example.memology.models;

import android.net.Uri;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class Memes {
    private int id;
    private String title;
    private Uri uri;
    private Blob bitmap;

    public Memes(String title,Uri uri,@Nullable Blob file){
        this.title = title;
        this.uri = uri;
        this.bitmap = file;
    }

    public Memes(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public Blob getBitmap() {
        return bitmap;
    }

    public void setBitmap(Blob bitmap) {
        this.bitmap = bitmap;
    }
}
