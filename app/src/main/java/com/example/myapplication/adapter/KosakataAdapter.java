package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Kosakata;

import java.util.List;

public class KosakataAdapter extends RecyclerView.Adapter<KosakataAdapter.KosakataViewHolder> {

    private List<Kosakata> kosakataList;

    public KosakataAdapter(List<Kosakata> kosakataList) {
        this.kosakataList = kosakataList;
    }

    @NonNull
    @Override
    public KosakataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kosakata, parent, false);
        return new KosakataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KosakataViewHolder holder, int position) {

        Kosakata kosakata = kosakataList.get(position);
        holder.tvKata.setText(kosakata.getKata());
        holder.tvTerjemahan.setText(kosakata.getTerjemahan());
        holder.tvCarabaca.setText(kosakata.getCarabaca());
        holder.tvKeterangan.setText(kosakata.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return kosakataList.size();
    }

    public void updateKosakataList(List<Kosakata> kosakatas) {
        this.kosakataList = kosakatas;
        notifyDataSetChanged();
    }

    public static class KosakataViewHolder extends RecyclerView.ViewHolder {
        TextView tvKata, tvTerjemahan, tvCarabaca, tvKeterangan;

        public KosakataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKata = itemView.findViewById(R.id.tv_kata);
            tvTerjemahan = itemView.findViewById(R.id.tv_terjemahan);
            tvCarabaca = itemView.findViewById(R.id.tv_carabaca);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
        }
    }
}
