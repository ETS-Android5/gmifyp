package com;

import android.content.pm.ActivityInfo;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gmifyp.R;
import com.example.gmifyp.library;
import org.jetbrains.annotations.Nullable;


public class HomeFragment extends Fragment {
    library web = new library();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.subtitle21);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle1);
        new library();
        return v;
    }

}
