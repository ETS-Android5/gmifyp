package com.example.gmifyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.UiModeManager;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.HomeFragment;
import com.OthersFragment;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Register extends AppCompatActivity {
    private static final String TAG = Register.class.getSimpleName();
    public  String email,password,ic,matrix;
    private MaterialButton btnRegister, btnLinkToLogin;
    public TextInputEditText inputName,inputName2, inputEmail, inputPassword;
    public static final String FILE_NAME = "registerdata.txt";
    public static final String IC = "ic";
    public static final String MATRIX = "mat";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "pass";
    public FirebaseDatabase db;
    public DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        inputName = findViewById(R.id.textInputEditText4);
        inputName2 = findViewById(R.id.textInputEditText5);
        inputEmail = findViewById(R.id.textInputEditText2);
        inputPassword = findViewById(R.id.textInputEditText3);
        btnRegister = findViewById(R.id.button_register);
        //////////////////////////////////////////FIREBASE

        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setTitle(R.string.subtitl);
        getSupportActionBar().setSubtitle(R.string.subtitle123);
        init();
        loadData();
        updateViews();
    }

    private void init() {
        // Login button Click Event

            // Hide Keyboard
        btnRegister.setOnClickListener(view -> {
            ic = inputName.getText().toString();
            matrix = inputName2.getText().toString();
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();

            if (!ic.isEmpty() && !matrix.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                Users Users = new Users(ic,matrix,email,password);
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Users");
                reference.child(matrix).setValue(Users);
                Toast.makeText(Register.this,"Successfuly Updated to FIREBASE"+ic,Toast.LENGTH_SHORT).show();
            }
            FileOutputStream fos = null;

            saveData();
            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fos.write(ic.getBytes());
                fos.write(matrix.getBytes());
                fos.write(email.getBytes());
                fos.write(password.getBytes());

                //mEditText.getText().clear();
                Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                        Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }check();


        });

            // Check for empty data in the form


/*
        String fileNameString = sharedPreferencesBinding.fileNameEditView.getText().toString();
        SharedPreferences sharedPreferences;
        if(fileNameString.isEmpty()) {
            sharedPreferences = getPreferences(MODE_PRIVATE);
        }
        else {
            sharedPreferences = getSharedPreferences(fileNameString, MODE_PRIVATE);
        }
*/

    }
    private void check() {
        if (!ic.isEmpty() && !matrix.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);

        } else {
            Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
    }
    String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        OthersFragment OF = new OthersFragment();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)|| OF.ofs ==5) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
            OthersFragment.ofs =0;
        } else {
            moveToSecondary();
        }
    }



    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("application", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IC, inputName.getText().toString());
        editor.putString(MATRIX, inputName2.getText().toString());
        editor.putString(EMAIL, inputEmail.getText().toString());
        editor.putString(PASSWORD, inputPassword.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("application", MODE_PRIVATE);
        ic = sharedPreferences.getString(IC, "");
        matrix = sharedPreferences.getString(MATRIX, "");
        email = sharedPreferences.getString(EMAIL, "");
        password = sharedPreferences.getString(PASSWORD, "");
    }

    public void updateViews() {

        inputName.setText(ic);
        inputName2.setText(matrix);
        inputEmail.setText(email);
       inputPassword.setText(password);
    }
    @Override
    protected void onStart() {
        super.onStart();
        this.inputName.setText(ic);
        this.inputName2.setText(matrix);
        this.inputEmail.setText(email);
        this.inputPassword.setText(password);
    }


}

