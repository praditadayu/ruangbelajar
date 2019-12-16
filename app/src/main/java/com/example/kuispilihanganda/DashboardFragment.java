package com.example.kuispilihanganda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.kuispilihanganda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment {

    EditText edtKategori,edtSoal,edtPilihan1,edtPilihan2,edtPilihan3,edtPilihan4,edtJawaban;
    Button     button;
    private SQLiteHelper sqLiteHelper = null ;
    public DashboardSoal dashboardSoal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, null);


        edtKategori =(EditText) view.findViewById(R.id.edtKategori);
        edtSoal =(EditText) view.findViewById(R.id.edtSoal);
        edtPilihan1 = (EditText) view.findViewById(R.id.edtPilihan1);
        edtPilihan2 = (EditText) view.findViewById(R.id.edtPilihan2);
        edtPilihan3 = (EditText) view.findViewById(R.id.edtPilihan3);
        edtPilihan4 = (EditText) view.findViewById(R.id.edtPilihan4);
        edtJawaban = (EditText) view.findViewById(R.id.edtJawaban);
        button = (Button) view.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSoal();
            }
        });

        return view;
    }

    private void saveSoal(){
        String kategori = edtKategori.getText().toString();
        String soal = edtSoal.getText().toString();
        String pilihan1 = edtPilihan1.getText().toString();
        String pilihan2 = edtPilihan2.getText().toString();
        String pilihan3 = edtPilihan3.getText().toString();
        String pilihan4 = edtPilihan4.getText().toString();
        String jawaban = edtJawaban.getText().toString();

        sqLiteHelper = new SQLiteHelper(getActivity());


        if(kategori.isEmpty()){
            //error name is empty
            Toast.makeText(getActivity(), "Mata Pelajaran Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(soal.isEmpty()){
            //error name is empty
            Toast.makeText(getActivity(), "Soal Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(pilihan1.isEmpty()){
            //error name is empty
            Toast.makeText(getActivity(), "Tambahkan Pilihan 1", Toast.LENGTH_SHORT).show();
        } else if(pilihan2.isEmpty()){
            //error name is empty
            Toast.makeText(getActivity(), "Tambahkan Pilihan 2", Toast.LENGTH_SHORT).show();
        }else if(pilihan3.isEmpty()){
            //error name is empty
            Toast.makeText(getActivity(), "Tambahkan Pilihan 3", Toast.LENGTH_SHORT).show();
        }else if(pilihan4.isEmpty()) {
            //error name is empty
            Toast.makeText(getActivity(), "Tambahkan Pilihan 4", Toast.LENGTH_SHORT).show();
        }else if(jawaban.isEmpty()) {
            //error name is empty
            Toast.makeText(getActivity(), "Tambahkan Jawaban", Toast.LENGTH_SHORT).show();
        }else {

            dashboardSoal = new DashboardSoal(kategori, soal, pilihan1, pilihan2, pilihan3,pilihan4, jawaban);
            boolean result = sqLiteHelper.saveNewSoal(dashboardSoal);

            if (result == true) {
                Toast.makeText(getActivity(), "Tersimpan", Toast.LENGTH_SHORT).show();
                edtKategori.setText("");
                edtSoal.setText("");
                edtPilihan1.setText("");
                edtPilihan2.setText("");
                edtPilihan3.setText("");
                edtPilihan4.setText("");
                edtJawaban.setText("");

            } else {
                Toast.makeText(getActivity(), "Tidak Dapat Disimpan", Toast.LENGTH_SHORT).show();
            }
        }
    }
}