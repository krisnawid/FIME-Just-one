package com.sdkdroid.fime_just_one.data.Laporan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdkdroid.fime_just_one.R;

import java.util.ArrayList;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanHolder> {

    private List<Laporan> laporans = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public LaporanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.input_inout_item, parent, false);
        return new LaporanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanHolder holder, int position) {
        Laporan currentLaporan = laporans.get(position);
        holder.textViewKeteranganLaporan.setText(currentLaporan.getKeterangan());
        holder.textViewTanggalLaporan.setText(currentLaporan.getTanggal());
        holder.textViewPemasukanLaporan.setText(String.valueOf(currentLaporan.getPemasukan()));
        holder.textViewPengeluaranLaporan.setText(String.valueOf(currentLaporan.getPengeluaran()));
    }

    @Override
    public int getItemCount() {
        return laporans.size();
    }

    public void setLaporans(List<Laporan> laporans){
        this.laporans = laporans;
        notifyDataSetChanged();
    }

    public Laporan getLaporanAt(int position){
        return laporans.get(position);
    }

    class LaporanHolder extends RecyclerView.ViewHolder{
        private TextView textViewKeteranganLaporan;
        private TextView textViewTanggalLaporan;
        private TextView textViewPemasukanLaporan;
        private TextView textViewPengeluaranLaporan;

        public LaporanHolder(View itemView){
            super(itemView);
            textViewKeteranganLaporan = itemView.findViewById(R.id.text_keterangan_input);
            textViewTanggalLaporan = itemView.findViewById(R.id.text_tanggal_input);
            textViewPemasukanLaporan = itemView.findViewById(R.id.text_pemasukan_input);
            textViewPengeluaranLaporan = itemView.findViewById(R.id.text_pengeluaran_input);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(laporans.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Laporan laporan);
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}