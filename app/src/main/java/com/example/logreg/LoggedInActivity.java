package com.example.logreg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoggedInActivity extends AppCompatActivity {

    private Button kijelentkezBtn;
    private TextView teljesNev;
    private DatabaseHelper db;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        String nev = db.getTeljesNev(sharedPref.getString("neved",""));
        //txtUdvozles.setText("Üdvözöllek: "+ nev);
         teljesNev.setText(nev);
         kijelentkezBtn.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                Intent kijelentkez = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(kijelentkez);
                finish();
            }
        });



    }
private void init(){
        kijelentkezBtn= findViewById(R.id.kijelentkez_button);
        teljesNev= findViewById(R.id.teljes_nev_text);
        db= new DatabaseHelper(this);
        sharedPref = getSharedPreferences("id", Context.MODE_PRIVATE);
}
}
