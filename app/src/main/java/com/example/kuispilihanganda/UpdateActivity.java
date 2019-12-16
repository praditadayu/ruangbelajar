package com.example.kuispilihanganda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kuispilihanganda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    private long receivedSoalId;
    EditText edtKategori,edtSoal,edtPilihan1,edtPilihan2,edtPilihan3,edtPilihan4,edtJawaban;
    Button     button;
    private SQLiteHelper sqLiteHelper = null ;
    public DashboardSoal UpdateSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtKategori =(EditText) findViewById(R.id.edtKategori);
        edtSoal =(EditText) findViewById(R.id.edtSoal);
        edtPilihan1 = (EditText) findViewById(R.id.edtPilihan1);
        edtPilihan2 = (EditText) findViewById(R.id.edtPilihan2);
        edtPilihan3 = (EditText) findViewById(R.id.edtPilihan3);
        edtPilihan4 = (EditText) findViewById(R.id.edtPilihan4);
        edtJawaban = (EditText) findViewById(R.id.edtJawaban);
        button = (Button) findViewById(R.id.button);

        sqLiteHelper = new SQLiteHelper(this);

        try {
            //get intent to get person id
            receivedSoalId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DashboardSoal queriedSoal = sqLiteHelper.getDashboardSoal(receivedSoalId);
        //set field to this user data
        edtKategori.setText(queriedSoal.getKategori());
        edtSoal.setText(queriedSoal.getSoal());
        edtPilihan1.setText(queriedSoal.getPilihan1());
        edtPilihan2.setText(queriedSoal.getPilihan2());
        edtPilihan3.setText(queriedSoal.getPilihan3());
        edtPilihan4.setText(queriedSoal.getPilihan4());
        edtJawaban.setText(queriedSoal.getJawaban());

        //listen to add button click to update
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSoal();
            }
        });
    }

    private void updateSoal(){
        String kategori = edtKategori.getText().toString();
        String soal = edtSoal.getText().toString();
        String pilihan1 = edtPilihan1.getText().toString();
        String pilihan2 = edtPilihan2.getText().toString();
        String pilihan3 = edtPilihan3.getText().toString();
        String pilihan4 = edtPilihan4.getText().toString();
        String jawaban = edtJawaban.getText().toString();

        sqLiteHelper = new SQLiteHelper(this);

        if(kategori.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Kategori Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(soal.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Soal Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(pilihan1.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Tambahkan Pilihan 1", Toast.LENGTH_SHORT).show();
        } else if(pilihan2.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Tambahkan Pilihan 2", Toast.LENGTH_SHORT).show();
        }else if(pilihan3.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Tambahkan Pilihan 3", Toast.LENGTH_SHORT).show();
        }else if(pilihan4.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Tambahkan Pilihan 4", Toast.LENGTH_SHORT).show();
        }else if(jawaban.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Tambahkan Jawaban", Toast.LENGTH_SHORT).show();
        }else {

            UpdateSoal = new DashboardSoal(kategori,soal,pilihan1,pilihan2,pilihan3,pilihan4,jawaban);
            sqLiteHelper.updateSoal(receivedSoalId, this, UpdateSoal);
            goBackHome();
        }


    }

    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }


}
