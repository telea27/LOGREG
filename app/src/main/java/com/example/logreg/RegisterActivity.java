package com.example.logreg;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regPwd,regEmail,regUserName;
    private Button regButton, backButton;
    private DatabaseHelper db;
    //private Toast toast;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!regName.getText().toString().equals("")&&!regPwd.getText().toString().equals("")&&!regEmail.getText().toString().equals("")&&!regUserName.getText().toString().equals("")){
                    if (db.checkUser(regEmail.getText().toString(),regUserName.getText().toString())) {

                        db.insertItem(regEmail.getText().toString(),regName.getText().toString(), regPwd.getText().toString(), regUserName.getText().toString());
                        Toast.makeText(RegisterActivity.this, "Sikeres regisztráció",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "A felhasználónév vagy email cím már foglalt",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Töltse ki a mezőket",Toast.LENGTH_LONG).show();

                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });


    }

    private void init(){
        regName=findViewById(R.id.register_name);
        regPwd=findViewById(R.id.register_pwd);
        regEmail=findViewById(R.id.register_email);
        regUserName=findViewById(R.id.register_user_name);
        regButton=findViewById(R.id.reg_btn);
        backButton=findViewById(R.id.back_btn);
        db= new DatabaseHelper(this);
        //toast=Toast.makeText(RegisterActivity.this,"A felhasználónév vagy email cím már foglalt",Toast.LENGTH_LONG);

    }
}


