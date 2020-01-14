package com.easygoing.easygoing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoMitraLaundrySepatuActivity extends AppCompatActivity {

    private TextView tvJenisSepatu, tvHargaSepatu, tvNamaMitaSepatu, tvAlamatMitraSepatu;
    private Button btnChatSepatu;
    private Toolbar toolbarInfoSepatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mitra_laundry_sepatu);

        toolbarInfoSepatu = findViewById(R.id.toolbarInfoSepatu);
        tvJenisSepatu = findViewById(R.id.tvInfoJenisSepatu);
        tvHargaSepatu = findViewById(R.id.tvInfoHargaSepatu);
        btnChatSepatu = findViewById(R.id.btnChatSepatu);
        tvNamaMitaSepatu = findViewById(R.id.tvNamaMitraSepatu);
        tvAlamatMitraSepatu = findViewById(R.id.tvAlamatMitraSepatu);

        final String strHargaSepatu, strJenisSepatu, strNamaMitraSepatu, strAlamatMitraSepatu, nohp;
        Intent intent = getIntent();
        strHargaSepatu = intent.getStringExtra("hargasepatu");
        strJenisSepatu = intent.getStringExtra("jenis");
        strNamaMitraSepatu = intent.getStringExtra("nama");
        strAlamatMitraSepatu = intent.getStringExtra("alamat");
        nohp = intent.getStringExtra("nohp");

        tvHargaSepatu.setText(strHargaSepatu);
        tvJenisSepatu.setText(strJenisSepatu);
        tvNamaMitaSepatu.setText(strNamaMitraSepatu);
        tvAlamatMitraSepatu.setText(strAlamatMitraSepatu);

        btnChatSepatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://wa.me/" + nohp;
                Intent i11 = new Intent(Intent.ACTION_VIEW);
                i11.setData(Uri.parse(link));
                startActivity(i11);
            }
        });

        //setUp toolbar/actionbar
        setSupportActionBar(toolbarInfoSepatu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarInfoSepatu.setNavigationIcon(R.drawable.ic_arrow_back);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
