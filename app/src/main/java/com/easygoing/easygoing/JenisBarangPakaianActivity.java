package com.easygoing.easygoing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.easygoing.easygoing.adapter.JenisPakaianAdapter;
import com.easygoing.easygoing.model.JenisBarangModel;
import com.easygoing.easygoing.model.PakaianModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JenisBarangPakaianActivity extends AppCompatActivity {

    private RecyclerView rvListBarang;
    private ArrayList<JenisBarangModel> jenisBarangModels;
    DatabaseReference databaseReference;
    JenisPakaianAdapter jenisPakaianAdapter;

    public static final String database_path = "ListPakaian/jenisbarang";

    private String TAG = JenisBarangPakaianActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_barang_pakaian);

        rvListBarang = findViewById(R.id.rvListBarang);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvListBarang.setLayoutManager(gridLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path);
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jenisBarangModels = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    JenisBarangModel barangModel = ds.getValue(JenisBarangModel.class);
                    jenisBarangModels.add(barangModel);
                }

                jenisPakaianAdapter = new JenisPakaianAdapter(JenisBarangPakaianActivity.this, jenisBarangModels);
                rvListBarang.setAdapter(jenisPakaianAdapter);
                Log.d(TAG, "Menerima data Jenis Barang dari server, jumlah item: " + jenisBarangModels.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
