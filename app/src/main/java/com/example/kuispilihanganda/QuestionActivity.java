package com.example.kuispilihanganda;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.kuispilihanganda.ClassCategoriesAdaptr.CATEGORIES_COLOR;
import static com.example.kuispilihanganda.ClassCategoriesAdaptr.CATEGORIES_ID;

public class QuestionActivity extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private QuestionAdapter mAdapter;
        private ArrayList<Question> mQuestionList;
        private QuizDBHelper mDbHelper;

        private ConstraintLayout mParentLayout;
        private TextView mScoreTextView;
        private TextView mRemaningQuestionsTextView;
        private int mTotalQuestions;
        private int mScore;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_question);

            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            Bundle categoriesBundle = null;
            if (getIntent() != null) {
                categoriesBundle = getIntent().getExtras();
            }

            mParentLayout = findViewById(R.id.question_layout);
            if (categoriesBundle != null) {
                String hexColor = String.format("#%06X", (0xFFFFFF & categoriesBundle.getInt(CATEGORIES_COLOR)));
                hexColor = "#44"+hexColor.substring(1);
                mParentLayout.setBackgroundColor(Color.parseColor(hexColor));
            }

            mScoreTextView = findViewById(R.id.score);
            mRemaningQuestionsTextView = findViewById(R.id.remaining_questions);

            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);

            mDbHelper = new QuizDBHelper(this, categoriesBundle);
            if (categoriesBundle != null) {
                mQuestionList = mDbHelper.getAllQuestions(categoriesBundle.getString(CATEGORIES_ID));
                mTotalQuestions = mQuestionList.size();
                mScore = 0;
                displayScore();
            }
            mAdapter = new QuestionAdapter(this, mQuestionList, categoriesBundle.getString(CATEGORIES_ID));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
        }

        public void displayScore() {
            String scoreString = "Score " + mScore + "/" + mTotalQuestions;
            mScoreTextView.setText(scoreString);
            mRemaningQuestionsTextView.setText("Remaining: " + mTotalQuestions--);
        }

        public void updateScore() {
            mScore++;
        }
}
