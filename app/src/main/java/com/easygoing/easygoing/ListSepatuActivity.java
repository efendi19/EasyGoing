package com.easygoing.easygoing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easygoing.easygoing.adapter.SepatuAdapter;
import com.easygoing.easygoing.model.SepatuModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListSepatuActivity extends AppCompatActivity {

    private RecyclerView rvListSepatu1, rvListSepatu2, rvListSepatu3;
    private ArrayList<SepatuModel> listSepatu;
    SepatuAdapter sepatuAdapter;
    DatabaseReference databaseReference;

    private Toolbar toolbarSepatu;

    private TextView tvNamaMitraSepatu1, tvNamaMitraSepatu2, tvNamaMitraSepatu3, tvAlamatMitraSepatu1, tvAlamatMitraSepatu2, tvAlamatMitraSepatu3, tvnohp1, tvnohp2, tvnohp3;

    public static final String database_path_sepatu1 = "ListSepatu/mitra1";
    public static final String database_path_sepatu2 = "ListSepatu/mitra2";
    public static final String database_path_sepatu3 = "ListSepatu/mitra3";

    private String TAG = ListSepatuActivity.class.getName();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sepatu);

        toolbarSepatu = findViewById(R.id.toolbarSepatu);
        rvListSepatu1 = findViewById(R.id.rvMitraSepatu1);
        rvListSepatu2 = findViewById(R.id.rvMitraSepatu2);
        rvListSepatu3 = findViewById(R.id.rvMitraSepatu3);
        tvNamaMitraSepatu1 = findViewById(R.id.tvMitraSepatu1);
        tvNamaMitraSepatu2 = findViewById(R.id.tvMitraSepatu2);
        tvNamaMitraSepatu3 = findViewById(R.id.tvMitraSepatu3);
        tvAlamatMitraSepatu1 = findViewById(R.id.tvListPakaianAlamat1);
        tvAlamatMitraSepatu2 = findViewById(R.id.tvListPakaianAlamat2);
        tvAlamatMitraSepatu3 = findViewById(R.id.tvListPakaianAlamat3);
        tvnohp1 = findViewById(R.id.noHpSepatu1);
        tvnohp2 = findViewById(R.id.noHpSepatu2);
        tvnohp3 = findViewById(R.id.noHpSepatu3);

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarSepatu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSepatu.setNavigationIcon(R.drawable.ic_arrow_back);

        setUpMitraSepatu1();
        setUpMitraSepatu2();
        setUpMitraSepatu3();

        setUpDialog();
    }

    private void setUpDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu..");
        progressDialog.show();
    }

    private void setUpMitraSepatu1() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_sepatu1);
        databaseReference.keepSynced(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvListSepatu1.setLayoutManager(gridLayoutManager);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSepatu = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SepatuModel model = ds.getValue(SepatuModel.class);
                    listSepatu.add(model);
                }
                if (rvListSepatu1 != null) {
                    progressDialog.dismiss();
                } else {
                    progressDialog.show();
                }

                sepatuAdapter = new SepatuAdapter(ListSepatuActivity.this, listSepatu);
                rvListSepatu1.setAdapter(sepatuAdapter);
                Log.d(TAG, "List Mitra Sepatu 1, jumlah item: " + listSepatu.size());

                sepatuAdapter.setOnItemClickListener(new SepatuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(ListSepatuActivity.this, InfoMitraLaundrySepatuActivity.class);
                        intent.putExtra("hargasepatu", listSepatu.get(position).getHarga());
                        intent.putExtra("jenis", listSepatu.get(position).getJenis());
                        intent.putExtra("nama", tvNamaMitraSepatu1.getText().toString());
                        intent.putExtra("alamat", tvAlamatMitraSepatu1.getText().toString());
                        intent.putExtra("nohp", tvnohp1.getText().toString());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListSepatuActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setUpMitraSepatu2() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_sepatu2);
        databaseReference.keepSynced(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvListSepatu2.setLayoutManager(gridLayoutManager);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSepatu = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SepatuModel model = ds.getValue(SepatuModel.class);
                    listSepatu.add(model);
                }

                sepatuAdapter = new SepatuAdapter(ListSepatuActivity.this, listSepatu);
                rvListSepatu2.setAdapter(sepatuAdapter);
                Log.d(TAG, "List mitra sepatu 2, jumlah item: " + listSepatu.size());

                sepatuAdapter.setOnItemClickListener(new SepatuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(ListSepatuActivity.this, InfoMitraLaundrySepatuActivity.class);
                        intent.putExtra("hargasepatu", listSepatu.get(position).getHarga());
                        intent.putExtra("jenis", listSepatu.get(position).getJenis());
                        intent.putExtra("nama", tvNamaMitraSepatu2.getText().toString());
                        intent.putExtra("alamat", tvAlamatMitraSepatu2.getText().toString());
                        intent.putExtra("nohp", tvnohp2.getText().toString());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListSepatuActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpMitraSepatu3() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_sepatu3);
        databaseReference.keepSynced(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvListSepatu3.setLayoutManager(gridLayoutManager);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSepatu = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SepatuModel model = ds.getValue(SepatuModel.class);
                    listSepatu.add(model);
                }

                sepatuAdapter = new SepatuAdapter(ListSepatuActivity.this, listSepatu);
                rvListSepatu3.setAdapter(sepatuAdapter);
                Log.d(TAG, "List mitra sepatu 3, jumlah item: " + listSepatu.size());

                sepatuAdapter.setOnItemClickListener(new SepatuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(ListSepatuActivity.this, InfoMitraLaundrySepatuActivity.class);
                        intent.putExtra("hargasepatu", listSepatu.get(position).getHarga());
                        intent.putExtra("jenis", listSepatu.get(position).getJenis());
                        intent.putExtra("nama", tvNamaMitraSepatu3.getText().toString());
                        intent.putExtra("alamat", tvAlamatMitraSepatu3.getText().toString());
                        intent.putExtra("nohp", tvnohp3.getText().toString());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListSepatuActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
