package com.example.kuispilihanganda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import android.support.design.widget.FloatingActionButton
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class DashboardActivity extends AppCompatActivity {

        private GridView mGridView;
        private ClassCategoriesAdaptr mClassCategoriesAdaptr;
        private ArrayList<ClassCategoriesItem> mClassCategoriesItems;
        private int[] mColors;
        private String[] mCategoriesTitles;
        private String[] mCategoriesID;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);

            setUpCategoriesItems();

            mGridView = findViewById(R.id.categories_list);
            mClassCategoriesAdaptr = new ClassCategoriesAdaptr(this, R.layout.grind_view_item, mClassCategoriesItems);
            mGridView.setAdapter(mClassCategoriesAdaptr);

            FloatingActionButton fabShare = findViewById(R.id.fab_share);
            fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                }
            });

        }

        private void setUpCategoriesItems() {
            mClassCategoriesItems = new ArrayList<>();
            mCategoriesTitles = getResources().getStringArray(R.array.categories_title);
            mCategoriesID = getResources().getStringArray(R.array.categories_ID);

            mColors = getResources().getIntArray(R.array.colors);

            for (int i = 0; i < mCategoriesTitles.length; i++) {
                mClassCategoriesItems.add(new ClassCategoriesItem(mColors[i], mCategoriesTitles[i], mCategoriesID[i]));
                Log.d("TAG", "Title\t" + mCategoriesTitles[i] + "\tID\t" + mCategoriesID[i]);
            }
        }
 }
