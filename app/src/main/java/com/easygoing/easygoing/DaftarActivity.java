package com.easygoing.easygoing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DaftarActivity extends AppCompatActivity {

    //declare object
    private Button btndaftar;
    private TextInputLayout til_name_reg, til_email_reg, til_pass_reg, til_passConfirm_reg;

    //Declare Fire base auth
    FirebaseAuth firebaseAuth;

    //declare progress dialog
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        btndaftar = findViewById(R.id.btn_register_now);
        til_name_reg = findViewById(R.id.et_name_reg);
        til_email_reg = findViewById(R.id.et_email_reg);
        til_pass_reg = findViewById(R.id.et_pass_reg);
        til_passConfirm_reg = findViewById(R.id.et_passConfirm_reg);

        //initialize fire base auth
        firebaseAuth = FirebaseAuth.getInstance();

        //initialize progress dialog
        dialog = new ProgressDialog(this);


        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpRegister();
            }
        });
    }

    private void setUpRegister() {
        //setting dialog
        dialog.setMessage("Sedang mendaftarkan akun baru...");
        dialog.show();

        //bikin string buat nampung
        String str_name, str_email, str_pass, str_pass_conf;
        str_name = til_name_reg.getEditText().getText().toString();
        str_email = til_email_reg.getEditText().getText().toString();
        str_pass = til_pass_reg.getEditText().getText().toString();
        str_pass_conf = til_passConfirm_reg.getEditText().getText().toString();

        if (til_email_reg.getEditText().getText().toString().isEmpty()) {
            til_email_reg.setErrorEnabled(true);
            til_email_reg.setHelperTextEnabled(true);
            til_email_reg.setError("Mohon isi email anda!");
            dialog.dismiss();
            return;
        } else if (til_pass_reg.getEditText().getText().toString().isEmpty()) {
            til_pass_reg.setErrorEnabled(true);
            til_pass_reg.setHelperTextEnabled(true);
            til_pass_reg.setError("Mohon isi password anda!");
            dialog.dismiss();
            return;
        } else if (str_email.isEmpty() && str_pass.isEmpty()) {
            Toast.makeText(DaftarActivity.this, "Email dan Password kosong, mohon di isi!", Toast.LENGTH_SHORT).show();
        } else if (str_pass.length() < 8) {
            til_pass_reg.setErrorEnabled(true);
            til_pass_reg.setHelperTextEnabled(true);
            til_pass_reg.setError("Minimal 8 karakter");
            til_pass_reg.setFocusable(true);
            dialog.dismiss();
        } else if (!(str_email.isEmpty() && str_pass.isEmpty())) {
            if (!str_pass.equals(str_pass_conf)) {
                dialog.dismiss();
                Toast.makeText(DaftarActivity.this, "Password tidak sama, mohon periksa kembali", Toast.LENGTH_SHORT).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(str_email, str_pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    //updateUI(user);
                                    dialog.dismiss();
                                    Toast.makeText(DaftarActivity.this, "Selamat datang " + user, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DaftarActivity.this, HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    dialog.dismiss();
                                    Toast.makeText(DaftarActivity.this, "Authentication failed. Mohon periksa kembali!",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        }
    }
}
