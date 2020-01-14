package com.easygoing.easygoing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.easygoing.easygoing.adapter.JenisPakaianAdapter;
import com.easygoing.easygoing.adapter.PakaianAdapter;
import com.easygoing.easygoing.model.JenisBarangModel;
import com.easygoing.easygoing.model.PakaianModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListPakaianActivity extends AppCompatActivity {

    private RecyclerView rvListPakaian, rvlistPakaian2, rvListPakaian3;
    private ArrayList<JenisBarangModel> modelArrayList;
    DatabaseReference databaseReference;
    JenisPakaianAdapter pakaianAdapter;

    private Toolbar toolbarPakaian;

    private TextView phone1, phone2, phone3, tvlistPakaianNama1, tvlistPakaianNama2, tvlistPakaianNama3, tvlistPakaianAlamat1, tvlistPakaianAlamat2, tvlistPakaianAlamat3;

    public static final String database_path_mitra1 = "ListPakaian/mitra1";
    public static final String database_path_mitra2 = "ListPakaian/mitra2";
    public static final String database_path_mitra3 = "ListPakaian/mitra3";

    private String TAG = ListPakaianActivity.class.getName();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pakaian);

        toolbarPakaian = findViewById(R.id.toolbarPakaian);
        rvListPakaian = findViewById(R.id.rvListPakaian);
        rvlistPakaian2 = findViewById(R.id.rvListPakaian2);
        rvListPakaian3 = findViewById(R.id.rvListPakaian3);
        phone1 = findViewById(R.id.tvPhone1);
        phone2 = findViewById(R.id.tvPhone2);
        phone3 = findViewById(R.id.tvPhone3);
        tvlistPakaianNama1 = findViewById(R.id.tvListPakaianNama1);
        tvlistPakaianNama2 = findViewById(R.id.tvListPakaianNama2);
        tvlistPakaianNama3 = findViewById(R.id.tvListPakaianNama3);
        tvlistPakaianAlamat1 = findViewById(R.id.tvListPakaianAlamat1);
        tvlistPakaianAlamat2 = findViewById(R.id.tvListPakaianAlamat2);
        tvlistPakaianAlamat3 = findViewById(R.id.tvListPakaianAlamat3);

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarPakaian);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPakaian.setNavigationIcon(R.drawable.ic_arrow_back);

        setUpMitra1();
        setUpMitra2();
        setUpMitra3();

        setUpDialog();
    }

    private void setUpDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu..");
        progressDialog.show();
    }

    private void setUpMitra3() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvListPakaian3.setLayoutManager(gridLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_mitra3);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelArrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    JenisBarangModel model = ds.getValue(JenisBarangModel.class);
                    modelArrayList.add(model);
                }
                pakaianAdapter = new JenisPakaianAdapter(ListPakaianActivity.this, modelArrayList);
                rvListPakaian3.setAdapter(pakaianAdapter);
                Log.d(TAG, "LAUNDRY 3, Jumlah : " + modelArrayList.size());

                pakaianAdapter.setOnItemClickListener(new JenisPakaianAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onItemButtonReguler(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON REGULER 3", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("reguler", modelArrayList.get(position).getReguler());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama3.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat3.getText().toString());
                        intent.putExtra("phone", phone3.getText().toString());
                        intent.putExtra("paket", "Reguler");
                        startActivity(intent);

                    }

                    @Override
                    public void onItemButtonKilat(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON KILAT 3", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("kilat", modelArrayList.get(position).getKilat());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama3.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat3.getText().toString());
                        intent.putExtra("phone", phone3.getText().toString());
                        intent.putExtra("paket", "Kilat");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPakaianActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpMitra2() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvlistPakaian2.setLayoutManager(gridLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_mitra2);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelArrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    JenisBarangModel model = ds.getValue(JenisBarangModel.class);
                    modelArrayList.add(model);
                }
                pakaianAdapter = new JenisPakaianAdapter(ListPakaianActivity.this, modelArrayList);
                rvlistPakaian2.setAdapter(pakaianAdapter);
                Log.d(TAG, "LAUNDRY 2, jumlah : " + modelArrayList.size());

                pakaianAdapter.setOnItemClickListener(new JenisPakaianAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onItemButtonReguler(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON REGULER 2", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("reguler", modelArrayList.get(position).getReguler());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama2.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat2.getText().toString());
                        intent.putExtra("phone", phone2.getText().toString());
                        intent.putExtra("paket", "Reguler");
                        startActivity(intent);
                    }

                    @Override
                    public void onItemButtonKilat(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON KILAT 2", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("kilat", modelArrayList.get(position).getKilat());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama2.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat2.getText().toString());
                        intent.putExtra("phone", phone2.getText().toString());
                        intent.putExtra("paket", "Kilat");
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPakaianActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpMitra1() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvListPakaian.setLayoutManager(gridLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path_mitra1);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelArrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    JenisBarangModel model = ds.getValue(JenisBarangModel.class);
                    modelArrayList.add(model);
                }
                if (rvListPakaian != null) {
                    progressDialog.dismiss();
                } else {
                    progressDialog.show();
                }
                pakaianAdapter = new JenisPakaianAdapter(ListPakaianActivity.this, modelArrayList);
                rvListPakaian.setAdapter(pakaianAdapter);
                Log.d(TAG, "Menerima data jenis barang dari server, jumlah item: " + modelArrayList.size());

                pakaianAdapter.setOnItemClickListener(new JenisPakaianAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onItemButtonReguler(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON REGULER 1", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("reguler", modelArrayList.get(position).getReguler());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama1.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat1.getText().toString());
                        intent.putExtra("phone", phone1.getText().toString());
                        intent.putExtra("paket", "Reguler");
                        startActivity(intent);
                    }

                    @Override
                    public void onItemButtonKilat(int position) {
                        Toast.makeText(ListPakaianActivity.this, "BUTTON KILAT 1", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPakaianActivity.this, InfoMitraLaundryPakaianActivity.class);
                        intent.putExtra("kilat", modelArrayList.get(position).getKilat());
                        intent.putExtra("jenis", modelArrayList.get(position).getJenis());
                        intent.putExtra("nama", tvlistPakaianNama1.getText().toString());
                        intent.putExtra("alamat", tvlistPakaianAlamat1.getText().toString());
                        intent.putExtra("phone", phone1.getText().toString());
                        intent.putExtra("paket", "Kilat");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPakaianActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
