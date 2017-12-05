package com.example.a555l.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private QuizDataSource datasource;

    private List<Quiz> mQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView)findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;

        resultLabel.setText(score + " /5");
        totalScoreLabel.setText("Total Score : " + totalScore);

        //Update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();

        mCrimeRecyclerView = (RecyclerView) findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        datasource = new QuizDataSource(this);
        datasource.open();

        //mColleges = datasource.getAllComments();
        mQuiz = datasource.get_searchCollege();
        mAdapter = new CrimeAdapter(mQuiz);

        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder{

        private LinearLayout list_linear;
        private TextView list_number,list_answer;

        public CrimeHolder(View itemView){
            super(itemView);

            list_number = (TextView) itemView.findViewById(R.id.list_number);
            list_answer = (TextView) itemView.findViewById(R.id.list_answer);

        }
    }



    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Quiz> mColleges;

        public CrimeAdapter(List<Quiz> crimes) {
            mColleges = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ResultActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Quiz quiz = mColleges.get(position);
            Log.d("insert","size:"+ getItemCount());

            String getdata = Long.toString(quiz.getId());

            holder.list_number.setText(getdata);
            holder.list_answer.setText(quiz.getQuestion_answer());

        }
        @Override
        public int getItemCount () {
            return mColleges.size();
        }

    }


    public void returnTop(View view)
    {
        datasource.deleteComment();
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
    }
}
