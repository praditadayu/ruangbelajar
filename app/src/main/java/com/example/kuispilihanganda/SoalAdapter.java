package com.example.kuispilihanganda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kuispilihanganda.R;

import java.util.List;

public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.ViewHolder> {
    private List<DashboardSoal> mSoalList;
    private Context mContext;
    private RecyclerView mRecyclerV;



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView soalTxtV;
        public TextView kategoriTxtV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            soalTxtV = (TextView) v.findViewById(R.id.textViewSoal);
            kategoriTxtV = (TextView) v.findViewById(R.id.textViewKategori);

        }
    }

    public void add(int position, DashboardSoal dashboardSoal) {
        mSoalList.add(position, dashboardSoal);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mSoalList.remove(position);
        notifyItemRemoved(position);
    }


    public SoalAdapter(List<DashboardSoal> myDataset, Context context, RecyclerView recyclerView) {
        mSoalList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public SoalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final DashboardSoal dashboardSoal = mSoalList.get(position);
        holder.soalTxtV.setText("Soal : " + dashboardSoal.getSoal());
        holder.kategoriTxtV.setText("Kategori : " + dashboardSoal.getKategori());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Pilih Opsi Dibawah ini");
                builder.setMessage("Edit atau Hapus Object?");
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(dashboardSoal.getId());

                    }
                });
                builder.setNeutralButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteHelper dbHelper = new SQLiteHelper(mContext);
                        dbHelper.deleteSoal(dashboardSoal.getId(), mContext);

                        mSoalList.remove(position);
                        // mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mSoalList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, UpdateActivity.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return mSoalList.size();
    }

}
