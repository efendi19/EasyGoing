package com.easygoing.easygoing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easygoing.easygoing.R;
import com.easygoing.easygoing.model.SepatuModel;

import java.util.ArrayList;


public class SepatuAdapter extends RecyclerView.Adapter<SepatuAdapter.VHolderSepatu> {

    Context context;
    ArrayList<SepatuModel> modelList;
    private OnItemClickListener mListener;

    public SepatuAdapter(Context context, ArrayList<SepatuModel> models) {
        this.context = context;
        this.modelList = models;
    }

    @NonNull
    @Override
    public SepatuAdapter.VHolderSepatu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_jenis_sepatu, parent, false);
        VHolderSepatu vHolderSepatu = new VHolderSepatu(view, mListener);
        return vHolderSepatu;
    }

    @Override
    public void onBindViewHolder(@NonNull SepatuAdapter.VHolderSepatu holder, int position) {

        SepatuModel sepatuModel = modelList.get(position);

        TextView strJenisSepatu = holder.tvJenisSepatu;
        TextView strHargaSepatu = holder.tvHarga;

        strJenisSepatu.setText(sepatuModel.getJenis());
        strHargaSepatu.setText(String.valueOf(sepatuModel.getHarga()));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class VHolderSepatu extends RecyclerView.ViewHolder {

        TextView tvJenisSepatu, tvHarga;

        public VHolderSepatu(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);

            tvJenisSepatu = itemView.findViewById(R.id.tvJenisSepatu);
            tvHarga = itemView.findViewById(R.id.tvHargaSepatu);

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
