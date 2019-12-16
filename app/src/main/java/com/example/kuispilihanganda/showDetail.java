package com.example.kuispilihanganda;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kuispilihanganda.R;

public class showDetail extends AppCompatActivity {

    private long receivedSoalId;
    TextView edtKategori,edtSoal, edtPilihan1, edtPilihan2, edtPilihan3, edtPilihan4, edtJawaban;
    private SQLiteHelper sqLiteHelper = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        //add button back navigation
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtKategori =(TextView) findViewById(R.id.tKategori);
        edtSoal =(TextView) findViewById(R.id.tSoal);
        edtPilihan1 = (TextView) findViewById(R.id.tPilihan1);
        edtPilihan2 = (TextView) findViewById(R.id.tPilihan2);
        edtPilihan3 = (TextView) findViewById(R.id.tPilihan3);
        edtPilihan4 = (TextView) findViewById(R.id.tPilihan4);
        edtJawaban = (TextView) findViewById(R.id.tJawaban);

        sqLiteHelper = new SQLiteHelper(this);

        try {
            //get intent to get person id
            receivedSoalId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DashboardSoal queriedSoal = sqLiteHelper.getDashboardSoal(receivedSoalId);

        edtKategori.setText(queriedSoal.getKategori());
        edtSoal.setText(queriedSoal.getSoal());
        edtPilihan1.setText(queriedSoal.getPilihan1()+",00");
        edtPilihan2.setText(queriedSoal.getPilihan2());
        edtPilihan3.setText(queriedSoal.getPilihan3());
        edtPilihan4.setText(queriedSoal.getPilihan4());
        edtJawaban.setText(queriedSoal.getJawaban());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
