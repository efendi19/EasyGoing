package com.easygoing.easygoing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoMitraLaundryPakaianActivity extends AppCompatActivity {

    private TextView tvHargaReguler, tvHargaKilat, tvJenisBrg, tvJenisPaket, tvnamaLaundry, tvAlamatLaundry;
    private Button btnWhatsAppLaundry;
    private Toolbar toolbarPakaian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mitra_laundry);

        toolbarPakaian = findViewById(R.id.toolbarInfoPakaian);
        tvHargaReguler = findViewById(R.id.tvHargaReguler);
        tvHargaKilat = findViewById(R.id.tvHargaKilat);
        tvJenisBrg = findViewById(R.id.tvJenisBrg);
        tvJenisPaket = findViewById(R.id.tvJenisPaket);
        tvnamaLaundry = findViewById(R.id.tv_namaLaundry);
        tvAlamatLaundry = findViewById(R.id.tv_alamatLaundry);
        btnWhatsAppLaundry = findViewById(R.id.btnChatLaundry);

        final String strHargaReg, strHargaKil, strJBrg, strJpkt, strNamaLaundry, strAlamatLaundry, phone;
        Intent intent = getIntent();
        strHargaReg = intent.getStringExtra("reguler");
        strHargaKil = intent.getStringExtra("kilat");
        strJBrg = intent.getStringExtra("jenis");
        strJpkt = intent.getStringExtra("paket");
        strNamaLaundry = intent.getStringExtra("nama");
        strAlamatLaundry = intent.getStringExtra("alamat");
        phone = intent.getStringExtra("phone");

        tvHargaReguler.setText(strHargaReg);
        tvHargaKilat.setText(strHargaKil);
        tvJenisBrg.setText(strJBrg);
        tvJenisPaket.setText(strJpkt);
        tvnamaLaundry.setText(strNamaLaundry);
        tvAlamatLaundry.setText(strAlamatLaundry);

        btnWhatsAppLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://wa.me/" + phone;
                Intent i11 = new Intent(Intent.ACTION_VIEW);
                i11.setData(Uri.parse(link));
                startActivity(i11);
            }
        });

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarPakaian);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPakaian.setNavigationIcon(R.drawable.ic_arrow_back);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
