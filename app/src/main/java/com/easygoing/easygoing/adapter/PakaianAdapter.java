package com.easygoing.easygoing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easygoing.easygoing.R;
import com.easygoing.easygoing.model.PakaianModel;

import java.util.ArrayList;

public class PakaianAdapter extends RecyclerView.Adapter<PakaianAdapter.VHolderPakaian> {

    Context context;
    ArrayList<PakaianModel> modelList;
    private OnItemClickListener mListener;

    public PakaianAdapter(Context context, ArrayList<PakaianModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public PakaianAdapter.VHolderPakaian onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_laundry, parent, false);
        VHolderPakaian lisVHolderGalon = new VHolderPakaian(view, mListener);
        return lisVHolderGalon;
    }

    @Override
    public void onBindViewHolder(@NonNull PakaianAdapter.VHolderPakaian holder, int position) {

        PakaianModel model = modelList.get(position);

        TextView strNama = holder.tvNama;
        TextView strAlamat = holder.tvAlamat;
        TextView strPhone = holder.tvPhone;
        strPhone.setText(String.valueOf(model.getPhone()));
        strNama.setText(model.getNama());
        strAlamat.setText(model.getAlamat());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class VHolderPakaian extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat, tvPhone;

        public VHolderPakaian(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvListPakaianNama);
            tvAlamat = itemView.findViewById(R.id.tvListPakaianAlamat);
            tvPhone = itemView.findViewById(R.id.tvListPakaianPhone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int posiition = getAdapterPosition();
                        if (posiition != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(posiition);
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
