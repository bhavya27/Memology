package com.example.memology.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.memology.R;

public class EntryPage extends AppCompatActivity {
    LinearLayout memeButton,sendFilesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        memeButton = (LinearLayout) findViewById(R.id.MemeButton);
        sendFilesButton = (LinearLayout) findViewById(R.id.SendFilesButton);
        memeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryPage.this,HomePage.class));
            }
        });
        sendFilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryPage.this,SendFiles.class));
            }
        });
    }
}