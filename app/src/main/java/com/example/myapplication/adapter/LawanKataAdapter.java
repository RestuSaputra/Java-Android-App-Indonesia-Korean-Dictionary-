package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.LawanKata;

import java.util.List;

public class LawanKataAdapter extends RecyclerView.Adapter<LawanKataAdapter.LawanKataViewHolder> {


    private List<LawanKata> lawanKataList;
    public  LawanKataAdapter (List<LawanKata> lawanKataList){
        this.lawanKataList = lawanKataList;
    }

    @NonNull
    @Override
    public LawanKataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_antonim, parent,false);
        return new LawanKataViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LawanKataViewHolder holder, int position) {
        LawanKata lawanKata = lawanKataList.get(position);

        holder.tvKataDasar.setText(lawanKata.getKataDasar());
        holder.tvTerjemahanDasar.setText(lawanKata.getTerjemahanDasar());
        holder.tvBacaDasar.setText(lawanKata.getBacaDasar());
        holder.tvKataLawan.setText(lawanKata.getKataLawan());
        holder.tvTerjemahanLawan.setText(lawanKata.getTerjemahanLawan());
        holder.tvBacaLawan.setText(lawanKata.getBacaLawan());
    }


    @Override
    public int getItemCount() {
        return lawanKataList.size();
    }

    public void lawanKataUpdate(List <LawanKata> lawanKatas){
        this.lawanKataList = lawanKatas;
        notifyDataSetChanged();
    }

    public static class LawanKataViewHolder extends RecyclerView.ViewHolder{
        TextView tvKataDasar,
                tvTerjemahanDasar,
                tvBacaDasar,
                tvKataLawan,
                tvTerjemahanLawan,
                tvBacaLawan;

        public LawanKataViewHolder(@NonNull View itemView){
            super(itemView);
            tvKataDasar = itemView.findViewById(R.id.text_kata_dasar);
            tvTerjemahanDasar = itemView.findViewById(R.id.text_terjemahan_dasar);
            tvBacaDasar = itemView.findViewById(R.id.text_baca_dasar);
            tvKataLawan = itemView.findViewById(R.id.text_kata_lawan);
            tvTerjemahanLawan = itemView.findViewById(R.id.text_terjemahan_lawan);
            tvBacaLawan = itemView.findViewById(R.id.text_baca_lawan);
        }
    }
}
