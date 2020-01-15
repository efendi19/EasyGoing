package com.easygoing.easygoing;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    //declare Fire base Auth
    private FirebaseAuth firebaseAuth;
    //FirebaseUser currrentUser;

    //declare object
    private Toolbar toolbarHome;
    private NavigationView navigationViewHome;
    private DrawerLayout drawerLayoutHome;
    private TextView username, usernameGoogle;
    private CardView cvGalon, cvSepatu, cvPakaian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize Fire base Auth
        firebaseAuth = FirebaseAuth.getInstance();

        /*currrentUser = firebaseAuth.getCurrentUser();
        if (currrentUser == null ) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }*/


        //Initialize object
        toolbarHome = findViewById(R.id.id_toolbarInclude);
        navigationViewHome = findViewById(R.id.navigationViewHome);
        drawerLayoutHome = findViewById(R.id.drawer_home);
        username = findViewById(R.id.tvUsername);
        usernameGoogle = findViewById(R.id.tvUsernameGoogle);
        cvGalon = findViewById(R.id.cv_galon);
        cvSepatu = findViewById(R.id.cv_sepatu);
        cvPakaian = findViewById(R.id.cv_pakaian);

        //set toolbar
        setSupportActionBar(toolbarHome);
        toolbarHome.bringToFront();

        //Access user Information with email default
        getInfoUserWithEmailDefault();
        //Access user Information with google account
        getInfoUserWithGoogleAccount();

        cvGalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ListGalonActivity.class));
            }
        });
        cvSepatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ListSepatuActivity.class));
            }
        });
        cvPakaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ListPakaianActivity.class));
            }
        });

        navigationViewHome.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_aboutApp:
                        Toast.makeText(HomeActivity.this, "About App di klik", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_logout:
                        setUpLogout();
                        return true;
                    default:
                        Toast.makeText(HomeActivity.this, "Ada kesalahan", Toast.LENGTH_LONG).show();
                        return true;
                }
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutHome, toolbarHome, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayoutHome.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_sort_black);
    }


    private void getInfoUserWithEmailDefault() {
        View headerView = navigationViewHome.getHeaderView(0);
        TextView usernameHeader, emailHeader;
        usernameHeader = headerView.findViewById(R.id.tv_username_drawer);
        emailHeader = headerView.findViewById(R.id.tv_email_drawer);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String nama = user.getDisplayName();
            String email = user.getEmail();
            emailHeader.setText(email);
            usernameHeader.setText("Username");
            //username.setText(email);
        } else {
            //..
            usernameHeader.setText("Username");
        }
    }

    private void getInfoUserWithGoogleAccount() {
        View headerView = navigationViewHome.getHeaderView(0);
        TextView usernameHeader, emailHeader;
        ImageView imageUserHeader;
        usernameHeader = headerView.findViewById(R.id.tv_username_drawer);
        emailHeader = headerView.findViewById(R.id.tv_email_drawer);
        imageUserHeader = headerView.findViewById(R.id.userimage);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            String name = user.getDisplayName();
            Uri photo = user.getPhotoUrl();
            emailHeader.setText(email);
            usernameHeader.setText(name);
            usernameGoogle.setText(name);
            Picasso.get().load(photo).into(imageUserHeader);

        } else {
            //..
            usernameHeader.setText("Username");
        }
    }

    private void setUpLogout() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi!");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        firebaseAuth.signOut();
                        Intent in = new Intent(HomeActivity.this, LoginActivity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(in);
                        Toast.makeText(HomeActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //..
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
