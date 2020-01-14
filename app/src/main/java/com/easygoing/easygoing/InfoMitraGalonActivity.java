package com.easygoing.easygoing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoMitraGalonActivity extends AppCompatActivity {

    private TextView tvNama, tvAlamat, tvHarga;
    private Button btnChat;
    private Toolbar toolbarInfoDepot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mitra_galon);

        toolbarInfoDepot = findViewById(R.id.toolbarInfoDepot);
        tvNama = findViewById(R.id.tv_namaDepot);
        tvAlamat = findViewById(R.id.tv_alamatDepot);
        tvHarga = findViewById(R.id.tv_hargaDepot);
        btnChat = findViewById(R.id.btnChatDepot);

        final String nama, alamat, harga, phone;
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        harga = intent.getStringExtra("harga");
        phone = intent.getStringExtra("phone");

        tvNama.setText(nama);
        tvAlamat.setText(alamat);
        tvHarga.setText(harga);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://wa.me/" + phone;
                Intent i11 = new Intent(Intent.ACTION_VIEW);
                i11.setData(Uri.parse(link));
                startActivity(i11);
            }
        });

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarInfoDepot);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarInfoDepot.setNavigationIcon(R.drawable.ic_arrow_back);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
