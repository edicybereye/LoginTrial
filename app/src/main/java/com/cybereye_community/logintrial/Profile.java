package com.cybereye_community.logintrial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity implements View.OnClickListener,
                                                    GoogleApiClient.OnConnectionFailedListener{

    TextView Nama, Email;
    Button Logout;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Nama = (TextView)findViewById(R.id.nama);
        Email = (TextView)findViewById(R.id.email);
        Logout = (Button)findViewById(R.id.logout);

        String txtNama = getIntent().getStringExtra("nama");
        String txtEmail = getIntent().getStringExtra("email");
        Nama.setText(txtNama);
        Email.setText(txtEmail);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.logout:
                signOut();
                break;

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent i = new Intent(Profile.this, Login.class);
                        startActivity(i);
                        Toast.makeText(Profile.this, "Terima Kasih", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
