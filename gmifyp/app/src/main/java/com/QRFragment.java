package com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.UiModeManager;
import com.example.gmifyp.Qr;
import com.example.gmifyp.R;

import org.jetbrains.annotations.Nullable;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.System.currentTimeMillis;


public class QRFragment extends Fragment {
    public static final String DATETIME = "Time";
    public String icA;
    public TextView scanstate;
    public Date currentTime = Calendar.getInstance().getTime();
    public int  time1,teste;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_qr, container, false);
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.subtitle3);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
        Button button  = v.findViewById(R.id.button1);
        TextView scanstate  = v.findViewById(R.id.scanstate);
        Calendar calendar = Calendar.getInstance();
        long time= currentTimeMillis();// can do kira kira mathematical operation
        Qr qr= new Qr();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intention =  new Intent(getActivity(), Qr.class);
                startActivity(intention);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("application", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(DATETIME, scanstate.getText().toString());
                editor.apply();
                icA = sharedPreferences.getString(DATETIME, "");
                scanstate.setText(icA);
            }
            //long test =Qr.duration;
            //if(test==5 ){
            //time=time1;
            //}

        });
        return v;
    }
}
