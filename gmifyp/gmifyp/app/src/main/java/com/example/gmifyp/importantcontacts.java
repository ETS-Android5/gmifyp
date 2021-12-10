package com.example.gmifyp;

import android.os.Bundle;
import android.app.UiModeManager;
import androidx.appcompat.app.AppCompatActivity;

public class importantcontacts extends AppCompatActivity {
    private UiModeManager uiModeManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        getSupportActionBar().show();
        getSupportActionBar().setTitle(R.string.subtitl);
        getSupportActionBar().setSubtitle("Important Contacts");
        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);

    }
}