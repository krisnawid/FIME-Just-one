package com.sdkdroid.fime_just_one.data.Perusahaan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdkdroid.fime_just_one.R;

import java.util.ArrayList;
import java.util.List;

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanAdapter.PerusahaanHolder> {
    private List<Perusahaan> perusahaans = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public PerusahaanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.perusahaan_item, parent, false);
        return new PerusahaanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PerusahaanHolder holder, int position) {
        Perusahaan currentPerusahaan = perusahaans.get(position);
        holder.textViewNamaPerusahaan.setText(currentPerusahaan.getNama_perusahaan());
        holder.textViewPemilikPerusahaan.setText(currentPerusahaan.getPemilik_perusahaan());
        holder.textViewAlamatPerusahaan.setText(currentPerusahaan.getAlamat_perusahaan());
    }

    @Override
    public int getItemCount() {
        return perusahaans.size();
    }

    public void setPerusahaans(List<Perusahaan> perusahaans){
        this.perusahaans = perusahaans;
        notifyDataSetChanged();
    }

    public Perusahaan getPerusahaanAt(int position){
        return perusahaans.get(position);
    }

    class PerusahaanHolder extends RecyclerView.ViewHolder{
        private TextView textViewNamaPerusahaan;
        private TextView textViewPemilikPerusahaan;
        private TextView textViewAlamatPerusahaan;

        public PerusahaanHolder(View itemView){
            super(itemView);
            textViewNamaPerusahaan = itemView.findViewById(R.id.nama_perusahaan);
            textViewPemilikPerusahaan = itemView.findViewById(R.id.pemilik_perusahaan);
            textViewAlamatPerusahaan = itemView.findViewById(R.id.alamat_perusahaan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(perusahaans.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Perusahaan perusahaan);
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
