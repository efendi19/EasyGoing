package com.easygoing.easygoing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easygoing.easygoing.R;
import com.easygoing.easygoing.model.GalonModel;

import java.util.ArrayList;
import java.util.List;

public class GalonAdapter extends RecyclerView.Adapter<GalonAdapter.VHolderGalon> {

    Context context;
    ArrayList<GalonModel> modelList;
    private OnItemClickListener mListener;

    public GalonAdapter(Context context, ArrayList<GalonModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public GalonAdapter.VHolderGalon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_galon, parent, false);
        VHolderGalon lisVHolderGalon = new VHolderGalon(view, mListener);
        return lisVHolderGalon;
    }

    @Override
    public void onBindViewHolder(@NonNull GalonAdapter.VHolderGalon holder, int position) {

        GalonModel model = modelList.get(position);

        TextView strNama = holder.tvNama;
        TextView strAlamat = holder.tvAlamat;
        TextView strHarga = holder.tvHarga;
        TextView strPhone = holder.tvPhone;

        strNama.setText(model.getNama());
        strAlamat.setText(model.getAlamat());
        strHarga.setText(String.valueOf(model.getHarga()));
        strPhone.setText(String.valueOf(model.getPhone()));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class VHolderGalon extends RecyclerView.ViewHolder {

        TextView tvNama, tvAlamat, tvHarga, tvPhone;

        public VHolderGalon(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvListGalonNama);
            tvAlamat = itemView.findViewById(R.id.tvListGalonAlamat);
            tvHarga = itemView.findViewById(R.id.tvListGalonHarga);
            tvPhone = itemView.findViewById(R.id.noHpDepot);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }
}
