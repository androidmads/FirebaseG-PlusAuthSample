package com.androidmads.firebaseuserauthsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button btnSignOut;
    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;


    /**
     * M A Mushtaq - androidmads.blogspot.com
     * Firebase User Authentication Tutorial with email and password registration
     * This screen is used as Dashboard for the Application and as link to multiple options
     * as well as SIGN-OUT option
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);
        btnSignOut = findViewById(R.id.sign_out_button);
        assert user != null;
        textName.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());

        Picasso.get().load(user.getPhotoUrl()).into(imageView);

        // Sign-Out option
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
