package com.sdkdroid.fime_just_one.data.Laporan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdkdroid.fime_just_one.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        holder.textViewJudulLaporan.setText(currentLaporan.getJudul());
        holder.textViewKeteranganLaporan.setText("Keterangan :\n"+currentLaporan.getKeterangan());

        String currentDate = String.valueOf(currentLaporan.getTanggal());
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z YYYY");
        try {
            Date newDate = format.parse(currentDate);
            format = new SimpleDateFormat("d-M-YYYY");
            String tanggal = format.format(currentLaporan.getTanggal());
            holder.textViewTanggalLaporan.setText("Tanggal : "+tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        double pemasukan = currentLaporan.getPemasukan();
        double pengeluaran = currentLaporan.getPengeluaran();
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        holder.textViewPemasukanLaporan.setText(kursIndonesia.format(pemasukan));
        holder.textViewPengeluaranLaporan.setText(kursIndonesia.format(pengeluaran));
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
        private TextView textViewJudulLaporan;

        public LaporanHolder(View itemView){
            super(itemView);
            textViewJudulLaporan = itemView.findViewById(R.id.text_judul_input);
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