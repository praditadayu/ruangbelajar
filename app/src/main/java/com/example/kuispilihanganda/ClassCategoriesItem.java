package com.example.kuispilihanganda;

public class ClassCategoriesItem { private int mBgColor;
    private String mCategoriesTitle;
    private String mCategoriesID;

    ClassCategoriesItem(int imageId, String CategoriesTitle, String CategoriesID) {
        this.mBgColor = imageId;
        this.mCategoriesTitle = CategoriesTitle;
        this.mCategoriesID = CategoriesID;
    }

    public int getmBgColor() {
        return mBgColor;
    }

    public String getmCategoriesTitle() {
        return mCategoriesTitle;
    }

    public String getmCategoriesID() {
        return mCategoriesID;
    }
}
