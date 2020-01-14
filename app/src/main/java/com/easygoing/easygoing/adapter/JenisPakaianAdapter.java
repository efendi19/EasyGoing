package com.easygoing.easygoing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easygoing.easygoing.R;
import com.easygoing.easygoing.model.JenisBarangModel;
import com.easygoing.easygoing.model.PakaianModel;

import java.util.ArrayList;

public class JenisPakaianAdapter extends RecyclerView.Adapter<JenisPakaianAdapter.VHolderJenisPakaian> {

    Context context;
    ArrayList<JenisBarangModel> arrayList;
    private OnItemClickListener mListener;

    public JenisPakaianAdapter(Context context, ArrayList<JenisBarangModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public JenisPakaianAdapter.VHolderJenisPakaian onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_pakaian, parent, false);
        VHolderJenisPakaian vHolderJenisPakaian = new VHolderJenisPakaian(view, mListener);
        return vHolderJenisPakaian;
    }

    @Override
    public void onBindViewHolder(@NonNull JenisPakaianAdapter.VHolderJenisPakaian holder, int position) {

        JenisBarangModel model = arrayList.get(position);
        TextView strPakaianReguler = holder.tvReguler;
        TextView strPakaianKilat = holder.tvKilat;
        TextView strTitle = holder.tivTitle;

        strPakaianReguler.setText(model.getReguler());
        strPakaianKilat.setText(model.getKilat());
        strTitle.setText(model.getJenis());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemButtonReguler(int position);

        void onItemButtonKilat(int position);
    }

    public class VHolderJenisPakaian extends RecyclerView.ViewHolder {

        Button btn_PakaianReguler, btn_pakaianKilat;
        TextView tivTitle, tvReguler, tvKilat;

        public VHolderJenisPakaian(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            btn_PakaianReguler = itemView.findViewById(R.id.btnPakaianReguler);
            btn_pakaianKilat = itemView.findViewById(R.id.btnPakaianKilat);
            tivTitle = itemView.findViewById(R.id.tvTitileJenisPakaian);
            tvReguler = itemView.findViewById(R.id.tvPakaianReguler);
            tvKilat = itemView.findViewById(R.id.tvPakaianKilat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            btn_PakaianReguler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemButtonReguler(position);
                        }
                    }
                }
            });

            btn_pakaianKilat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemButtonKilat(position);
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
