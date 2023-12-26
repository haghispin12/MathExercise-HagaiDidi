package com.example.mathexercise_hagaididi;

import android.graphics.drawable.Drawable;

public class Fruit {
    private String name;
    private Drawable drawable;

    public Fruit(String name, Drawable drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
