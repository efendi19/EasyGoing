package com.easygoing.easygoing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    //declare object
    private TextView tvDaftar, tvLoginWithGoogle;
    private Button btnLogin;
    private TextInputLayout til_email_login, til_pass_login;

    //declare fire base Auth
    FirebaseAuth firebaseAuth;

    //declare progressDialog
    ProgressDialog dialog;

    //declare sign in with google account
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvDaftar = findViewById(R.id.tv_daftar);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginWithGoogle = findViewById(R.id.tv_loginWithGoogle);
        til_email_login = findViewById(R.id.et_email_login);
        til_pass_login = findViewById(R.id.et_pass_login);

        //initialize fire base auth
        firebaseAuth = FirebaseAuth.getInstance();

        //initialize
        dialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = til_email_login.getEditText().getText().toString().trim();
                String pass = til_pass_login.getEditText().getText().toString().trim();
                setUpLogin(email, pass);
            }
        });

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //configure google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        tvLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinGoogle = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signinGoogle, RC_SIGN_IN);
            }
        });
    }

    private void setUpLogin(String email, String pass) {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
        String str_email, str_pass;
        str_email = til_email_login.getEditText().getText().toString();
        str_pass = til_pass_login.getEditText().getText().toString();

        if (til_email_login.getEditText().getText().toString().isEmpty()) {
            dialog.dismiss();
            Toast.makeText(LoginActivity.this, "Filed kosong, mohon di isi!", Toast.LENGTH_SHORT).show();
            til_email_login.setCounterMaxLength(10);
            til_email_login.setHelperTextEnabled(true);
            til_email_login.setHelperText("Field kosong, mohon isi dulu!");
            til_email_login.setFocusable(true);
            return;
        } else if (til_pass_login.getEditText().getText().toString().isEmpty()) {
            dialog.dismiss();
            Toast.makeText(LoginActivity.this, "Field kosong, mohon di isi!", Toast.LENGTH_SHORT).show();
            til_pass_login.setCounterMaxLength(10);
            til_pass_login.setHelperTextEnabled(true);
            til_pass_login.setHelperText("Field kosong, mohon isi dulu!");
            til_pass_login.setFocusable(true);
            return;
        } else if (str_email.isEmpty() && str_pass.isEmpty()) {
            dialog.dismiss();
            Toast.makeText(LoginActivity.this, "Field kosong, mohon di isi!", Toast.LENGTH_SHORT).show();
            til_email_login.setHelperTextEnabled(true);
            til_pass_login.setHelperTextEnabled(true);
            til_email_login.setHelperText("Field kosong, mohon di isi!");
            til_pass_login.setHelperText("Field kosong, mohon di isi!");
            til_email_login.setFocusable(true);
        } else if (str_pass.length() < 8) {
            til_pass_login.setHelperTextEnabled(true);
            til_pass_login.setErrorEnabled(true);
            til_pass_login.setError("Password terlalu pendek, minimal 8 karakter");
            dialog.dismiss();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information;
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                Toast.makeText(LoginActivity.this, "Selamat datang : " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                dialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                //Log.w(TAG, "Google sign in failed", e);
                // ...
                Toast.makeText(LoginActivity.this, "Ada yang tidak beres!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Selamat datang : " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.layout_login), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
