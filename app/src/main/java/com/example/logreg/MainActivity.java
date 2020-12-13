package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText loginNev, loginJelszo;
    private Button signUpBtn, loginBtn;
    private DatabaseHelper db;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    signUpBtn.setOnClickListener(new View.OnClickListener() {
     @Override
    public void onClick(View v) {
         Intent regisztral = new Intent(MainActivity.this, RegisterActivity.class);
         startActivity(regisztral);
         finish();

    }
});
    loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!loginJelszo.getText().toString().equals("")&&!loginNev.getText().toString().equals("")&&db.checkLogin(loginNev.getText().toString(),loginJelszo.getText().toString())) {
                String nev = loginNev.getText().toString();
                editor.putString("neved", nev);
                editor.commit();
                Intent bejelentkez = new Intent(MainActivity.this, LoggedInActivity.class);
                startActivity(bejelentkez);
                finish();
            }
        }
    });





    }

    private void init(){
    loginNev=findViewById(R.id.login_nev);
    loginJelszo=findViewById(R.id.login_pwd);
    loginBtn=findViewById(R.id.login_btn);
    signUpBtn=findViewById(R.id.signup_btn);
    db= new DatabaseHelper(this);
    sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
    editor = sharedPref.edit();






    }
}
