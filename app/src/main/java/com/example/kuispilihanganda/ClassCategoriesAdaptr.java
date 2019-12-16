package com.example.kuispilihanganda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ClassCategoriesAdaptr extends ArrayAdapter<ClassCategoriesItem> {

    static final String CATEGORIES_COLOR = "CategoriesColor";
    static final String CATEGORIES_ID = "CategoriesID";
    private Context mContext;
    private ArrayList<ClassCategoriesItem> mClassCategoriesItems;

    ClassCategoriesAdaptr(@NonNull Context context, int resource, @NonNull ArrayList<ClassCategoriesItem> classCategoriesItems) {
        super(context, resource, classCategoriesItems);
        this.mContext = context;
        this.mClassCategoriesItems = classCategoriesItems;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CategoriesViewHolder categoriesViewHolder;
        final ClassCategoriesItem classCategoriesItem = mClassCategoriesItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.grind_view_item, parent, false);
            categoriesViewHolder = new CategoriesViewHolder(convertView);
            convertView.setTag(categoriesViewHolder);
        }
        categoriesViewHolder = (CategoriesViewHolder) convertView.getTag();
        categoriesViewHolder.categoriesImage.setBackgroundColor(classCategoriesItem.getmBgColor());
        categoriesViewHolder.categoriesTitle.setText(classCategoriesItem.getmCategoriesTitle());
        categoriesViewHolder.categoriesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(mContext, QuestionActivity.class);
                questionIntent.putExtra(CATEGORIES_ID, classCategoriesItem.getmCategoriesID());
                questionIntent.putExtra(CATEGORIES_COLOR, classCategoriesItem.getmBgColor());
                mContext.startActivity(questionIntent);
            }
        });
        return convertView;
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView categoriesImage;
        TextView categoriesTitle;

        CategoriesViewHolder (View itemView) {
            super(itemView);
            categoriesImage = itemView.findViewById(R.id.categories_image);
            categoriesTitle = itemView.findViewById(R.id.categories_title);
        }
    }
}
