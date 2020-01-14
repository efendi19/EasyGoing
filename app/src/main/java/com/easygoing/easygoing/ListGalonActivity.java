package com.easygoing.easygoing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easygoing.easygoing.adapter.GalonAdapter;
import com.easygoing.easygoing.model.GalonModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListGalonActivity extends AppCompatActivity {

    //declare object
    private Toolbar toolbarListGalon;
    private RecyclerView rvListGalon;
    private ArrayList<GalonModel> listGalon;
    private DatabaseReference databaseReference;
    GalonAdapter adapter;

    public static final String database_path = "ListGalon";

    private String TAG = ListGalonActivity.class.getName();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_galon);

        toolbarListGalon = findViewById(R.id.toolbarListGalon);
        dialogSetup();
        rvListGalon = findViewById(R.id.rvListGalon);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvListGalon.setLayoutManager(gridLayoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(database_path);
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listGalon = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    GalonModel model = ds.getValue(GalonModel.class);
                    listGalon.add(model);
                }
                if (rvListGalon != null) {
                    progressDialog.dismiss();
                } else {
                    progressDialog.show();
                }
                adapter = new GalonAdapter(ListGalonActivity.this, listGalon);
                rvListGalon.setAdapter(adapter);
                Log.d(TAG, "Menerima data list galon dari server, jumlah item: " + listGalon.size());

                adapter.setOnItemClickListener(new GalonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(ListGalonActivity.this, InfoMitraGalonActivity.class);
                        intent.putExtra("nama", listGalon.get(position).getNama());
                        intent.putExtra("alamat", listGalon.get(position).getAlamat());
                        intent.putExtra("harga", listGalon.get(position).getHarga());
                        intent.putExtra("phone", listGalon.get(position).getPhone());
                        startActivity(intent);
                        //Toast.makeText(ListGalonActivity.this, "Card View : " + listGalon.get(position).getNama(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListGalonActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarListGalon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarListGalon.setNavigationIcon(R.drawable.ic_arrow_back);
    }

    private void dialogSetup() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu..");
        progressDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
