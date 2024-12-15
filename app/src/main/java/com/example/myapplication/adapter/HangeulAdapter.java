package com.example.myapplication.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Hangeul;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.HangeulService;

import java.util.List;

public class HangeulAdapter extends RecyclerView.Adapter<HangeulAdapter.HangeulViewHolder> {

    private final Context context;
    private final List<Hangeul> hangeulList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Hangeul hangeul);
    }

    public HangeulAdapter(Context context, List<Hangeul> hangeulList, OnItemClickListener listener){
        this.context = context;
        this.hangeulList = hangeulList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HangeulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hangeul, parent, false);
        return new HangeulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangeulViewHolder holder, int position) {

        Hangeul hangeul = hangeulList.get(position);
//        Log.d(TAG, "Binding item: " + hangeul.getAksara());
        holder.bind(hangeul, listener);
    }

    @Override
    public int getItemCount() {
        return hangeulList.size();
    }

    public static class HangeulViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvAksara;

//, tvAksaraBaca, tvAksaraKet, tvAksaraPenSatu, tvAksaraPenDua
        public HangeulViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAksara = itemView.findViewById(R.id.item_aksara);
//            tvAksaraBaca = itemView.findViewById(R.id.item_carabaca);
//            tvAksaraKet = itemView.findViewById(R.id.item_keterangan);
//            tvAksaraPenSatu = itemView.findViewById(R.id.item_penjelasansatu);
//            tvAksaraPenDua = itemView.findViewById(R.id.item_penjelasandua);
        }

        public void bind(final Hangeul hangeul, final OnItemClickListener listener){
            tvAksara.setText(hangeul.getAksara());
//            tvAksaraBaca.setText(hangeul.getCaraBaca());
//            tvAksaraKet.setText(hangeul.getKeterangan());
//            tvAksaraPenSatu.setText(hangeul.getPenjelasanSatu());
//            tvAksaraPenDua.setText(hangeul.getPenjelasanDua());
//
            itemView.setOnClickListener(v ->listener.onItemClick(hangeul));
        }



    }
}
