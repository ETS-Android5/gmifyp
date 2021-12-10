package com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.gmifyp.R;
import com.example.gmifyp.Register;
import com.example.gmifyp.library;
import com.example.gmifyp.importantcontacts;
import com.example.gmifyp.schedule;

import org.jetbrains.annotations.Nullable;


public class OthersFragment extends Fragment {
public static int ofs;
    private Register reg = new Register();
    public static final String FILE_NAME = "registerdata.txt";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";
    public String ic1,email1,matrix1,password1;
    public  TextView email,matrix;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.others, container, false);
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
       // ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle5);
        CardView cardView1  = v.findViewById(R.id.card1);
        CardView cardView2  = v.findViewById(R.id.card2);
        CardView cardView3  = v.findViewById(R.id.card3);
        CardView cardView4  = v.findViewById(R.id.card4);
        CardView cardView5  = v.findViewById(R.id.card5);
        CardView cardView6  = v.findViewById(R.id.card6);
         email = v.findViewById(R.id.user_name);
         matrix = v.findViewById(R.id.user_id);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("application", 0);
        email.setText(reg.email);
        matrix.setText(reg.matrix);
        loadData();
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), Register.class);
                startActivity(intention);
                ofs=5;

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), schedule.class);
                startActivity(intention);

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), importantcontacts.class);
                startActivity(intention);

            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), library.class);
                startActivity(intention);

            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), schedule.class);
                startActivity(intention);

            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), schedule.class);
                startActivity(intention);

            }
        });
        return v;

    }


    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("application", 0);
        ic1 = sharedPreferences.getString(reg.IC, "");
        matrix1 = sharedPreferences.getString(reg.MATRIX, "");
        email1 = sharedPreferences.getString(reg.EMAIL, "");
        password1 = sharedPreferences.getString(reg.PASSWORD, "");
        email.setText(email1);
        matrix.setText(matrix1);

    }




}
